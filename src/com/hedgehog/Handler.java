package com.hedgehog;

public class Handler {
    private Looper looper;

    public Handler (Looper looper) {
        this.looper = looper;
    }

    public void post(Runnable runnable) {
        looper.messageQueue.enqueueMessage(new Message(runnable, 0L));
    }

    public void postDelayed(Runnable runnable, Long when) {
        looper.messageQueue.enqueueMessage(new Message(runnable, System.currentTimeMillis() + when));
    }
}
