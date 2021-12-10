package com.hedgehog;

public class Looper {
    private static Looper instance;
    final MessageQueue messageQueue;
    private static boolean isAlive = true;

    private Looper() {
        messageQueue = new MessageQueue();
    }

    public static void loop() {
        Looper currentInstance = instance;
        new Handler(instance).post(() -> {
            Application application = new Application();
            application.onCreate();
        });
        while (isAlive) {
            Message nextMessage = currentInstance.messageQueue.next();
            nextMessage.action.run();
        }
    }

    public static void shutdown() {
        isAlive = false;
    }

    public static void prepare() {
        instance = new Looper();
    }


    public static Looper getInstance() {
        return instance;
    }
}
