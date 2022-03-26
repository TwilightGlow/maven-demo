package com.example.thread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestScheduledPool {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        // 两次开始执行最小间隔时间, 如果执行时间大于间隔，则会根据间隔等当前任务执行完毕后找到下一个任务的开始时间
        pool.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()),
                1, 1, TimeUnit.SECONDS);
        // 前一次执行结束到下一次执行开始的间隔时间
        pool.scheduleWithFixedDelay(() -> System.out.println(Thread.currentThread().getName()),
                1, 1, TimeUnit.SECONDS);
    }
}
