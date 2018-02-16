package com.aliyun.learn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangwei
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        ThreadPoolExecutor service = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        int i = 0;
        while(i < 1000000) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("current queue size is : " + service.getQueue().size());
                    System.out.println("stat info- completed count:" + service.getCompletedTaskCount());
                    System.out.println("stat info- active count:" + service.getActiveCount());
                    System.out.println("stat info- core pool size: " + service.getCorePoolSize());
                    System.out.println("stat info- largest pool count:" + service.getLargestPoolSize());
                    System.out.println("stat info- maximum pool count:" + service.getMaximumPoolSize());
                }
            });
            i++;
        }
        service.shutdown();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!service.isTerminated()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                long costTime = System.currentTimeMillis() - time1;
                System.out.println("totally cost time: " + costTime + " milli seconds.");
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}