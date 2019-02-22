package com.xiebiao.java.lang.thread;

/**
 * How to make two threads execute in sequence?
 *
 * @author bjxieb
 * @date 2019-02-18
 **/
public class Demo1 {

    static Thread A = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("A is run");
        }
    });
    static Thread B = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                A.join();
                System.out.println("B is run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) {

        B.start();
        A.start();
    }
}
