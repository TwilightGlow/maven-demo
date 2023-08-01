package com.example.queue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Javen
 * @date 2022/2/19
 */
public class ThreadTest {

    // 在notify()方法后，当前线程不会马上释放该对象锁，要等到执行notify()方法的线程将程序执行完，也就是退出同步代码块之后才会释放对象锁
    public static void main(String[] args) {
        Object lock = new Object();
        AtomicInteger integer = new AtomicInteger();

        new Thread(() -> {
            while (integer.intValue() < 100) {
                synchronized (lock) {
                    lock.notifyAll();
                    integer.incrementAndGet();
//                    if (integer.intValue() % 2 == 1) {
                        System.out.println(Thread.currentThread().getName()  + " : " + integer);
//                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }).start();

        new Thread(() -> {
            while (integer.intValue() < 100) {
                synchronized (lock) {
                    lock.notifyAll();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    integer.incrementAndGet();
//                    if (integer.intValue() % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + integer);
//                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Test
    public void test() {
        Semaphore semaphore = new Semaphore(1);
        Semaphore semaphore1 = new Semaphore(0);
        AtomicInteger integer = new AtomicInteger();

        new Thread(() -> {
            while (integer.intValue() < 99) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                integer.incrementAndGet();
                if (integer.intValue() % 2 == 1) {
                    System.out.println(Thread.currentThread().getName()  + " : " + integer);
                }
                semaphore1.release();
            }
        }).start();

        new Thread(() -> {
            while (integer.intValue() < 100) {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                integer.incrementAndGet();
                if (integer.intValue() % 2 == 0) {
                    System.out.println(Thread.currentThread().getName()  + " : " + integer);
                }
                semaphore.release();
            }
        }).start();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
