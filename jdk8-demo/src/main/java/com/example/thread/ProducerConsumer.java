package com.example.thread;

import java.util.concurrent.SynchronousQueue;

/**
 * @author Javen
 * @date 2022/2/24
 */
public class ProducerConsumer {

    private static int num = 0;
    private static final Object LOCK = new Object();
    private static final SynchronousQueue<Object> queue = new SynchronousQueue<>();

    public static void main(String[] args) {
//        new Thread(() -> {
//            while (num <= 100) {
//                try {
//                    System.out.println("等了5秒");
//                    queue.put(1);
////                    System.out.println("Put完了！！！！！");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (num % 2 == 0) {
//                    System.out.println(Thread.currentThread().getName() + " : " + num);
//                    num++;
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (num <= 100) {
//                try {
////                    System.out.println("等了3秒");
//                    queue.take();
////                    System.out.println("Take完了！！！！！");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (num % 2 == 1) {
//                    System.out.println(Thread.currentThread().getName() + " : " + num);
//                    num++;
//                }
//            }
//        }).start();

        new Thread(() -> {
            synchronized (LOCK) {
                while (num <= 100) {
                    if (num % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + num);
                        num++;
                        LOCK.notifyAll();
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (LOCK) {
                while (num <= 100) {
                    if (num % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + num);
                        num++;
                        LOCK.notifyAll();
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
