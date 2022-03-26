package com.example.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Javen
 * @date 2022/3/11
 */
public class TestUnSafe {

    private static int i = 0;
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10000; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 100; k++) {
                        i++;
                        atomicInteger.incrementAndGet();
                    }
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(i);
        System.out.println(atomicInteger.intValue());
    }
}
