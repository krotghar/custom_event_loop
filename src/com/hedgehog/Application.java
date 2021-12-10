package com.hedgehog;

public class Application {


    public void onCreate() {
        Thread thread = new Thread(() -> new Handler(Looper.getInstance()).postDelayed(() -> System.out.println("from other thread"), 10000L));
        thread.start();
        new Handler(Looper.getInstance()).post(() -> System.out.println("created"));
        new Handler(Looper.getInstance()).postDelayed(() -> System.out.println("created delay 2000"), 2000L);
    }
}
