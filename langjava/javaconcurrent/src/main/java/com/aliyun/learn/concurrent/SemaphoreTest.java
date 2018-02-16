package com.aliyun.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 3000;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(20);

    private static Semaphore s = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i< THREAD_COUNT; i++) {
            final int j = i;
            threadPool.execute(new Runnable() {
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("save data" + j);
                        Thread.sleep(8);
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();

        Runnable inspector = new Runnable() {
            public void run() {
                while (true) {
                    System.out.println(" queue length: " + s.getQueueLength());
                    if ( !s.hasQueuedThreads() ) {
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t = new Thread(inspector);
        t.start();
    }
}
