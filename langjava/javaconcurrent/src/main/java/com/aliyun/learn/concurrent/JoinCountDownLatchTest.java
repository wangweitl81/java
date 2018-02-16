package com.aliyun.learn.concurrent;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author wangwei
 */
public class JoinCountDownLatchTest {

    public static List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);

    public static void main(String[] args) throws InterruptedException {
        list.forEach(System.out::println);
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("hello");
            }
        });

        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finished");
            }
        });
        t.start();
        parser2.start();
        t.join();
        parser2.join();
        System.out.println("all finished.");
    }
}
