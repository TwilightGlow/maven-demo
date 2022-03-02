package com.example.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Javen
 * @date 2022/2/19
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>(3);
        pbq.add(1);
        pbq.add(5);
        pbq.add(3);
        pbq.add(2);
        pbq.add(4);
        System.out.println(pbq.take());
        System.out.println(pbq.take());
        System.out.println(pbq.take());
        System.out.println(pbq.take());
        System.out.println(pbq.take());

    }
}
