package com.example.thread.print;

import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhangjw54
 */
public class LockSupportTest {

    private final Queue<String> queue = new LinkedBlockingQueue<>();

    @Test
    public void produceConsume() throws InterruptedException {
        // 消费者线程
        Thread consumer = new Thread(() -> {
            while (true) {
                if (queue.isEmpty()) {
                    LockSupport.park(); // 队列空，阻塞消费者
                }
                String item = queue.poll();
                System.out.println("消费者: " + item);
            }
        });
        consumer.start();

        // 生产者线程
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String item = "Item-" + i;
                queue.offer(item);
                System.out.println("生产者: " + item);
                LockSupport.unpark(consumer); // 生产后唤醒消费者
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        Thread.currentThread().join();
    }

    @Test
    public void lockSupport() throws InterruptedException {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println("子线程执行。。。。");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("主线程执行。。。。");
        LockSupport.unpark(thread);
        Thread.currentThread().join();
    }
}
