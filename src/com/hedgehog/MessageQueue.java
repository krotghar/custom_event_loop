package com.hedgehog;

public class MessageQueue {
    Message messages;

    Message next() {
        synchronized (this) {
            int nextWaitTime = -1;
            while (true) {
                try {
                    if (nextWaitTime >= 0) {
                        wait(nextWaitTime);
                    }
                } catch (InterruptedException e) {
                }

                Message current = messages;
                if (current != null) {
                    final long now = System.currentTimeMillis();
                    if (now < current.getWhen()) {
                        nextWaitTime = (int) (current.getWhen() - now);
                    } else {
                        messages = current.next;
                        return current;
                    }
                } else {
                    nextWaitTime = 0;
                }
            }
        }
    }

    boolean enqueueMessage(Message newMessage) {
        if (newMessage == null) {
            return false;
        }
        synchronized (this) {
            Message current = messages;
            if (current == null) {
                messages = newMessage;
            } else {
                Message previous;
                do {
                    previous = current;
                    current = current.next;
                } while (current != null && newMessage.getWhen() >= current.getWhen());
                newMessage.next = previous.next;
                previous.next = newMessage;
            }
            notify();
        }
        return true;
    }
}
