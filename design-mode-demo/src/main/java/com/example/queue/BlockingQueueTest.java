package com.example.queue;

import java.util.concurrent.*;

/**
 * @author Javen
 * @date 2022/2/19
 */
public class BlockingQueueTest {

    // Blocking Queue生产者和消费者是根据队列长度阻塞
//    static LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
    static LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);

    public static void main(String[] args) {
        ExecutorService exService = Executors.newFixedThreadPool(2);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        exService.execute(producer);
        exService.execute(consumer);
        exService.shutdown();

    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("Producer is waiting to transfer...");
                    blockingQueue.put("第" + i + "个元素");
                    System.out.println("producer transfer element: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("Consumer is waiting to take element...");
                    String take = blockingQueue.take();
                    System.out.println("Consumer received Element: " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
