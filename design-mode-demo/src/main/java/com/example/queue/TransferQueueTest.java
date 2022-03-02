package com.example.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Javen
 * @date 2022/2/19
 */
public class TransferQueueTest {

    // Transfer Queues生产者是根据有无消费进行阻塞
    private static final LinkedTransferQueue<String> lnkTransQueue = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        ExecutorService exService = Executors.newFixedThreadPool(2);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        exService.execute(producer);
        exService.execute(consumer);
        exService.shutdown();

    }

    private static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("Producer is waiting to transfer...");
                    lnkTransQueue.transfer("A" + i);
                    System.out.println("producer transfered element: A" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("Consumer is waiting to take element...");
                    String s = lnkTransQueue.take();
                    System.out.println("Consumer received Element: " + s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
