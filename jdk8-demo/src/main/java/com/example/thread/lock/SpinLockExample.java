package com.example.thread.lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhangjw54
 */
public class SpinLockExample {

    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true)) {
            // 自旋等待
        }
    }

    public void unlock() {
        lock.set(false);
    }

    public static void main(String[] args) {
        SpinLockExample spinLock = new SpinLockExample();

        // 模拟两个线程竞争锁
        new Thread(() -> {
            spinLock.lock();
            try {
                System.out.println("Thread 1 acquired the lock.");
                Thread.sleep(2000); // 模拟业务处理时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }).start();

        new Thread(() -> {
            spinLock.lock();
            try {
                System.out.println("Thread 2 acquired the lock.");
            } finally {
                spinLock.unlock();
            }
        }).start();
    }
}
