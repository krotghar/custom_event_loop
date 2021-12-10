package com.hedgehog;

public class Message {
    Runnable action;
    private long when;
    Message next;

    public Message(Runnable action, Long when) {
        this.action = action;
        this.when = when;
    }

    public long getWhen() {
        return when;
    }
}
