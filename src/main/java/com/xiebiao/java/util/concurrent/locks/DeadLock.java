package com.xiebiao.java.util.concurrent.locks;

/**
 * 死锁的4个必要条件:
 *
 * <pre>
 *     1.互斥:  一个资源每次只能被一个进程使用。
 *     2.请求与保持条件：一个进程因请求资源而阻塞时，对已获取资源保持不放。
 *     3.不剥夺条件：进程已获得资源，在未完成使用之前，不能被剥夺。
 *     4.循环等待条件:  若干进程之间形成一种头尾相接的循环等待资源关系。
 * </pre>
 *
 * <pre>
 *  1.Mutual exclusion：only one process at a time can use a resource.
 *
 * 2.Hold and wait: a process holding at least one resource is waiting to acquire additional
 * resources held by other processes.
 *
 * 3.No preemption: a resource can be released only voluntarily by the process holding it, after
 * that process has completed its task.
 *
 * 4. Circular wait: there exists a set {P0, P1, …, P0} of waiting processes such that P0 is
 * waiting for a resource that is held by P1, P1 is waiting for a resource that is held by P2, …,
 * Pn–1 is waiting for a resource that is held by Pn, and P0 is waiting for a resource that is held
 * by P0.
 * </pre>
 *
 * @author xiebiao
 * @date 03/12/2017
 */
public class DeadLock {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (resourceA) {
                //TODO
                System.out.println("locking resource A");
                //在获取到资源同时未释放的情况下，获取另外的资源
                synchronized (resourceB) {
                    //TODO
                    System.out.println("locking resource B");
                }//释放resourceB
                System.out.println("release resource B");
            }//释放resourceA
            System.out.println("release resource A");
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (resourceB) {
                //TODO
                System.out.println("locking resource B");
                //在获取到资源同时未释放的情况下，获取另外的资源
                synchronized (resourceA) {
                    //TODO
                    System.out.println("locking resource A");
                }
                System.out.println("release resource A");
            }
            System.out.println("release resource B");
        }
    }

    public static void main(String[] args) {
        int count = 1000;
        while (count-- > 0) {
            System.out.println(count);
            new Thread(new ThreadA()).start();
            new Thread(new ThreadB()).start();
        }
    }
}
