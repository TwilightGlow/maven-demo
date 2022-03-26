package com.example.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestCondition {

    private int flag = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();

    public static void main(String[] args) {
        TestCondition testCondition = new TestCondition();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                testCondition.print5(i);
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                testCondition.print10(i);
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                testCondition.print15(i);
            }
        });
    }

    private void print5(int loop) {
        lock.lock();
        try {
            while (flag != 1) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + "轮数：" + loop);
                flag = 2;
                c2.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    private void print10(int loop) {
        lock.lock();
        try {
            while (flag != 2) {
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + "轮数：" + loop);
                flag = 3;
                c3.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    private void print15(int loop) {
        lock.lock();
        try {
            while (flag != 3) {
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + "轮数：" + loop);
                flag = 1;
                c1.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
