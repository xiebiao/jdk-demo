package com.xiebiao.java.lang.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author bjxieb
 * @date 2019-02-22
 **/
public class Demo3 {

    private final static int            total     = 3;
    private static       CountDownLatch downLatch = new CountDownLatch(total);
    private static       Thread         last      = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                downLatch.await();
                System.out.println("Last one running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    public static void main(String[] args) {
        //线程启动有顺序依赖
        last.start();
        for (int i = 0; i < total; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " running...");
                    downLatch.countDown();
                }
            });
            thread.start();
        }
    }

}
