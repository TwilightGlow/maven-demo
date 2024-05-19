package com.example.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author Javen
 * @date 2022/3/17
 */
@Slf4j
public class TestSelfSpin {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.activeCount());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end...");
        });
        thread.start();

        log.info("线程是否终止: {}", thread.isInterrupted());
        thread.interrupt();
        log.info("线程是否终止: {}", thread.isInterrupted());

        // 这里为自旋，引起CPU空转造成性能浪费
        while (true) {
            if (Thread.activeCount() == 2) break;
        }

        System.out.println(Thread.currentThread().getName() + " end...");
    }
}
