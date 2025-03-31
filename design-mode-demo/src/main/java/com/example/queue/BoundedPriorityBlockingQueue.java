package com.example.queue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangjw54
 */
public class BoundedPriorityBlockingQueue<E> {
    private final PriorityBlockingQueue<E> queue;
    private final int maxSize;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedPriorityBlockingQueue(int maxSize) {
        this.queue = new PriorityBlockingQueue<>();
        this.maxSize = maxSize;
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= maxSize) {
                notFull.await();
            }
            queue.put(e);
            notEmpty.signal(); // 通知等待取元素的线程
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            E item = queue.take();
            notFull.signal(); // 通知等待放元素的线程
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        BoundedPriorityBlockingQueue<Integer> boundedQueue = new BoundedPriorityBlockingQueue<>(3);

        // 启动一个生产者线程
        new Thread(() -> {
            try {
                boundedQueue.put(3);
                System.out.println("Inserted 3");
                boundedQueue.put(1);
                System.out.println("Inserted 1");
                boundedQueue.put(2);
                System.out.println("Inserted 2");
                boundedQueue.put(4); // 这个插入会阻塞，直到有空间
                System.out.println("Inserted 4");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // 启动一个消费者线程
        new Thread(() -> {
            try {
                Thread.sleep(3000); // 让生产者线程先运行
                System.out.println("Took " + boundedQueue.take()); // 1
                System.out.println("Took " + boundedQueue.take()); // 2
                System.out.println("Took " + boundedQueue.take()); // 3
                System.out.println("Took " + boundedQueue.take()); // 4
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
