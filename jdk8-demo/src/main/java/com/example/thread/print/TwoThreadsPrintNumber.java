package com.example.thread.print;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjw54
 */
public class TwoThreadsPrintNumber {

    private final Object lock = new Object();
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();
    private int count = 1;

    private final AtomicInteger turn = new AtomicInteger(1);
    private final AtomicInteger atomicCount = new AtomicInteger(1);

    private final Semaphore semaphore1 = new Semaphore(1);
    private final Semaphore semaphore2 = new Semaphore(0);
    private final Semaphore semaphore3 = new Semaphore(0);

    @Test
    public void semaphore() {
        new Thread(() -> semaphorePrint(semaphore1, semaphore2), "thread-1").start();
        new Thread(() -> semaphorePrint(semaphore2, semaphore3), "thread-2").start();
        new Thread(() -> semaphorePrint(semaphore3, semaphore1), "thread-3").start();
    }

    private void semaphorePrint(Semaphore semaphore1, Semaphore semaphore2) {
        try {
            while (count <= 100) {
                semaphore1.acquire();
                if (count > 100) return;
                System.out.println("Thread " + Thread.currentThread().getName() + ": " + count++);
                semaphore2.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void spinPrint() {
        new Thread(() -> {
            while (atomicCount.get() <= 100) {
                if (turn.get() == 1) {
                    // 如果使用普通的int变量，那么对count的读写操作（如递增操作）将不是原子的。多个线程同时对count进行操作时，可能会出现竞态条件（Race Condition），导致结果不正确
                    System.out.println(Thread.currentThread().getName() + ":" + atomicCount.getAndIncrement());
                    turn.set(2);
                } else {
                    while (turn.get() != 1) {
                        Thread.yield();
                    }
                }
            }
        }, "thread-1").start();

        new Thread(() -> {
            while (atomicCount.get() <= 100) {
                if (turn.get() == 2) {
                    System.out.println(Thread.currentThread().getName() + ":" + atomicCount.getAndIncrement());
                    turn.set(1);
                } else {
                    while (turn.get() != 2) {
                        Thread.yield();
                    }
                }
            }
        }, "thread-2").start();
    }

    @Test
    public void lockConditionPrint() {
        new Thread(() -> {
            try {
                reentrantLock.lock();
                while (count <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    condition.signal();
                    if (count <= 100) {
                        condition.await();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                reentrantLock.unlock();
            }
        }, "thread-1").start();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                while (count <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    condition.signal();
                    if (count <= 100) {
                        condition.await();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                reentrantLock.unlock();
            }
        }, "thread-2").start();
    }

    @Test
    public void waitNotifyPrint() {
        new Thread(() -> {
            synchronized (lock) {
                while (count <= 100) {
                    // count不需要加volatile关键字，synchronized块不仅提供了互斥访问，还确保了内存可见性。当一个线程进入 synchronized块时，它会从主内存中读取最新的变量值。
                    // volatile一般配合无锁使用
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    // 这里有必要判断count是否小于等于100，否则最后一次判断的的时候，线程会进入等待状态
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }, "thread-1").start();

        new Thread(() -> {
            synchronized (lock) {
                while (count <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }, "thread-2").start();

    }

    @Test
    public void lockSupportPrint() {
        final Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            while (count <= 100) {
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                LockSupport.unpark(threads[1]);
                if (count <= 100) {
                    LockSupport.park();
                }
            }
        }, "thread-1");
        threads[1] = new Thread(() -> {
            while (count <= 100) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                LockSupport.unpark(threads[0]);
            }
        }, "thread-2");
        for (Thread thread : threads) {
            thread.start();
        }

        // 唤醒第一个线程
        // 确实，如果线程 thread-1 先执行，然后通过 LockSupport.unpark(threads[1]) 唤醒 thread-2，理论上可以实现同样的效果。然而，不显式唤醒第一个线程 thread-1 可能会导致两个线程都陷入等待状态，尤其是在某些 JVM 实现或调度机制下，线程调度的顺序和时机不是完全可控的。
        // 因为 LockSupport.unpark(threads[1]) 可能会在 threads[0].start() 之前执行，导致线程 2 先执行，然后通过 LockSupport.unpark(threads[0]) 唤醒线程 1，这样就会导致两个线程都陷入等待状态，无法正常执行。
        // 最好是在程序内部进行逻辑控制，基于某种条件下就会 LockSupport.park，例如通过 % 取余判断是否轮到自己，参考 ThreeThreadsPrintNumber.java

        LockSupport.unpark(threads[0]);
    }


    AtomicInteger num = new AtomicInteger(0);
    Thread thread1, thread2;
    @Test
    public void lockSupportPrint2() {
        thread1 = new Thread(() -> {
            while (true) {
                int current = num.get();
                if (current > 100) {
                    LockSupport.unpark(thread2); // 确保唤醒线程2以退出
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ":" + current);
                num.incrementAndGet();
                LockSupport.unpark(thread2); // 唤醒线程2
                if (num.get() > 100) {
                    break;
                }
                LockSupport.park(); // 挂起当前线程
            }
        }, "thread-1");

        thread2 = new Thread(() -> {
            while (true) {
                LockSupport.park(); // 初始挂起，等待唤醒
                int current = num.get();
                if (current > 100) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ":" + current);
                num.incrementAndGet();
                LockSupport.unpark(thread1); // 唤醒线程1
                if (num.get() > 100) {
                    break;
                }
            }
        }, "thread-2");

        thread1.start();
        thread2.start();
    }
}
