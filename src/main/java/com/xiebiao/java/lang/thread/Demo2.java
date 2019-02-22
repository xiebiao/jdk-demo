package com.xiebiao.java.lang.thread;

/**
 * How To Make Two Threads Intersect Orderly In a Specified Manner?
 *
 * @author bjxieb
 * @date 2019-02-22
 **/
public class Demo2 {


    private static Object lock = new Object();
    static         Thread A    = new Thread(() -> {
        //试图拿到lock锁
        synchronized (lock) {
            System.out.println('1');
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
            System.out.println("3");
        }
    });
    static         Thread B    = new Thread(() -> {
        //试图拿到lock锁
        synchronized (lock) {
            System.out.println('a');
            System.out.println("b");
            System.out.println("c");
            lock.notify();
        }
    });

    public static void main(String[] args) {
        //线程启动有顺序依赖
        A.start();
        B.start();

    }

}
