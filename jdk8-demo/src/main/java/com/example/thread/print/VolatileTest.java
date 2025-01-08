package com.example.thread.print;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjw54
 */
public class VolatileTest {

    private boolean flag = true;

    private volatile boolean vFlag = true;

    @Test
    public void testVolatile() throws InterruptedException {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            flag = false;
            vFlag = false;
            System.out.println(Thread.currentThread().getName() + " 执行结束");
        }, "线程一").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            while (flag) {
                // do nothing
            }
            System.out.println("检测到flag被置成false，" + Thread.currentThread().getName() + " 执行结束");
        }, "线程二").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            while (vFlag) {
                // do nothing
            }
            System.out.println("检测到flag被置成false，" + Thread.currentThread().getName() + " 执行结束");
        }, "线程三").start();

        Thread.currentThread().join();
    }

    private Boolean synFlag = false;
    @Test
    // synchronized 和 ReentrantLock 也可以保证可见性
    // https://blog.csdn.net/weixin_45298122/article/details/142427573?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-142427573-blog-136314510.235^v43^control&spm=1001.2101.3001.4242.2&utm_relevant_index=4%E4%BF%9D%E8%AF%81%E5%8F%AF%E8%A7%81%E6%80%A7%E7%9A%84%E5%8E%9F%E7%90%86
    // 当一个线程修改了一个synchronized方法或代码块中的共享变量，其他线程在进入相同的synchronized块之前，将会看到这个变量的最新值。
    // 这是因为synchronized提供了内存屏障的特性，确保在进入同步块时，从主内存中读取最新的共享变量值，并在退出同步块时将修改后的值写入主内存。
    public void syn() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synFlag = true;
        }).start();
        while (true) {
            // synchronized (VolatileTest.class) {
            // lock.lock();
                if (synFlag) {
                    System.out.println("线程修改了flag，主线程感知到了");
                    break;
                }
            // lock.unlock();
            // }
        }
    }

    // https://blog.csdn.net/weixin_73077810/article/details/132804522
    // 这个例子中主线程不需要加锁，子线程加锁，子线程可以感知到主线程对共享变量的修改
    // 当一个线程获取ReentrantLock锁时，它会将自己工作内存中的数据刷新到主内存中，这样其他线程就能够看到最新的值。
    // 而当一个线程释放ReentrantLock锁时，它会将主内存中的数据刷新到自己的工作内存中，这样其他线程就能够读取到最新的值。
    private ReentrantLock lock = new ReentrantLock();
    private boolean run = true;
    @Test
    public void reentrantLockSync() throws InterruptedException {
        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (!run) {
                        System.out.println(Thread.currentThread().getName() + " 执行结束");
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }, "线程一").start();
        Thread.sleep(5000);
        // lock.lock();
        try {
            run = false;
        } finally {
            // lock.unlock();
        }
        Thread.currentThread().join();
    }

    public static class SharedResource {
        private int counter = 0;

        public synchronized void increment() {
            counter++;
        }

        public synchronized int getCounter() {
            return counter;
        }
    }

    @Test
    public void testWithSynchronized() throws InterruptedException {
        // 当一个线程修改了一个synchronized方法或代码块中的共享变量，其他线程在进入相同的synchronized块之前，将会看到这个变量的最新值。
        // 这是因为synchronized提供了内存屏障的特性，确保在进入同步块时，从主内存中读取最新的共享变量值，并在退出同步块时将修改后的值写入主内存。
        SharedResource sharedResource = new SharedResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedResource.increment();
            }
            System.out.println(Thread.currentThread().getName() + " 执行结束");
        }, "线程一").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + sharedResource.getCounter());
        }, "线程二").start();

        Thread.currentThread().join();
    }
}
