package com.xiebiao.java.lang.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author bjxieb
 * @date 2019-02-22
 **/
public class Demo4 {

    private final static int           total         = 3;
    private static       CyclicBarrier cyclicBarrier = new CyclicBarrier(total);
    static final         Random        random        = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < total; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long prepareTime = random.nextInt(10000) + 100;
                        Thread.sleep(prepareTime);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out
                        .println(System.currentTimeMillis() + " :" + Thread.currentThread().getName() + " running...");
                }
            });
            thread.start();
        }
    }
}
