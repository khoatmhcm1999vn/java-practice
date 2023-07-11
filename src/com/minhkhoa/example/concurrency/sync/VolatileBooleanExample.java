package com.minhkhoa.example.concurrency.sync;

public class VolatileBooleanExample {

    private volatile boolean active;

    private static void sleep(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileBooleanExample example = new VolatileBooleanExample();
        example.prepare();
        example.start();
        sleep(10);
    }

    public void prepare() throws InterruptedException {
        new Thread(() -> {
            System.out.println("application preparing ...");
            sleep(3);
            active = true;
        })
                .start();
    }

    public void start() throws Exception {
        new Thread(() -> {
            while (!active) ;
            System.out.println("application started");
        })
                .start();
    }
}
