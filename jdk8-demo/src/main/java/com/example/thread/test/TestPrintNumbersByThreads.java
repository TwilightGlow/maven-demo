package com.example.thread.test;


import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjw54
 */
public class TestPrintNumbersByThreads {

    final AtomicInteger count = new AtomicInteger(1);
    final Object lock = new Object();
    final Lock reentrantLock = new ReentrantLock();

    @Test
    public void waitNotifyPrint() throws InterruptedException {
        new Thread(() -> testWaitNotify(2), "线程2").start();
        new Thread(() -> testWaitNotify(1), "线程1").start();
        new Thread(() -> testWaitNotify(0), "线程0").start();
        Thread.currentThread().join();
    }

    @Test
    public void lockConditionPrint() throws InterruptedException {
        final Condition condition1 = reentrantLock.newCondition();
        final Condition condition2 = reentrantLock.newCondition();
        final Condition condition3 = reentrantLock.newCondition();

        new Thread(() -> testCondition(condition1, condition2)).start();
        new Thread(() -> testCondition(condition2, condition3)).start();
        new Thread(() -> testCondition(condition3, condition1)).start();
        Thread.currentThread().join();
    }

    @Test
    public void semaphorePrint() throws InterruptedException {
        final Semaphore semaphore1 = new Semaphore(1);
        final Semaphore semaphore2 = new Semaphore(0);
        final Semaphore semaphore3 = new Semaphore(0);

        new Thread(() -> testSemaphore(semaphore1, semaphore2)).start();
        new Thread(() -> testSemaphore(semaphore2, semaphore3)).start();
        new Thread(() -> testSemaphore(semaphore3, semaphore1)).start();
        Thread.currentThread().join();
    }

    private void testWaitNotify(int threadId) {
        // 这里synchronized(同步监视器)和while谁写在上面都行实现的效果是一样的，说明synchronized对于编译器不是真正的代码行，而是会给执行的代码行用到wait()或notify()的地方生效
        // 因此synchronized代码块的作用在于：
        //   1. 相当于屏障，多个线程如果共用一个lock，同时只能有一个线程进入同步监视器的代码块
        //   2. wait方法必须在synchronized中，如果执行到程序会在这行停止，并允许其它就绪态线程进入，wait后会进入等待态必须被notify才会进入就绪态
        //   3. notify也必须在synchronized中，在notify执行后synchronized的作用域结束再释放锁！！！！
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ": 这行只会跑一次，并且不是按照0/1/2的顺序执行！！！！");
            try {
                Thread.sleep(5000);
                while (count.get() <= 100) {
//                    System.out.println(Thread.currentThread().getName() + ": " + lock);
                    if ((count.get() - 1) % 3 == threadId) {
                        System.out.println(Thread.currentThread().getName() + ": " + count.getAndIncrement());
                        // notify/notifyAll()执行后，并不立即释放锁，而是要等到执行完临界区中代码后，再释放
                        lock.notifyAll();
                        Thread.sleep(1000);
                    } else {
                        // wait()执行后，会立即释放锁，程序在这行暂停，然后被notify/notifyAll()唤醒后，再竞争锁，成功后才能进入临界区
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + ": " + "被唤醒后首先执行" + lock);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void testCondition(Condition currentCondition, Condition nextCondition) {
        try {
            reentrantLock.lock();
            while (count.get() <= 100) {
                System.out.println("Thread " + Thread.currentThread().getName() + ": " + count.getAndIncrement());
                nextCondition.signal();
                Thread.sleep(2000);
                currentCondition.await();
            }
            reentrantLock.unlock();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void testSemaphore(Semaphore semaphore1, Semaphore semaphore2) {
        try {
            while (count.get() <= 100) {
                semaphore1.acquire();
                if (count.get() > 100) return;
                System.out.println("Thread " + Thread.currentThread().getName() + ": " + count.getAndIncrement());
                semaphore2.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
