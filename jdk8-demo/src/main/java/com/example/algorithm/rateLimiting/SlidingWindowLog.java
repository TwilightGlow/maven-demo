package com.example.algorithm.rateLimiting;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 滑动日志
 * 高级一点的滑动窗口算法，所以实现上和滑动窗口类似
 * 滑动日志限速算法需要记录请求的时间戳，通常使用 有序集合 来存储，我们可以在单个有序集合中跟踪用户在一个时间段内所有的请求
 * 假设我们要限制给定T时间内的请求不超过N，我们只需要存储最近T时间之内的请求日志，每当请求到来时判断最近T时间内的请求总数是否超过阈值。
 *
 * 优点：
 * 能够避免突发流量，实现较为精准的限流
 * 更加灵活，能够支持更加复杂的限流策略 ，如多级限流，每分钟不超过100次，每小时不超过300次，每天不超过1000次
 *
 * 缺点：
 * 占用存储空间要高于其他限流算法
 *
 * @author zhangjw54
 */
public class SlidingWindowLog {

    private final int rate; // 每个窗口允许的最大请求数
    private final long windowSizeInMillis; // 窗口大小（毫秒）
    private final Deque<Long> requestTimestamps = new LinkedList<>(); // 存储请求的时间戳
    private final ReentrantLock lock = new ReentrantLock(); // 线程安全锁

    /**
     * 构造函数
     * @param rate 每个窗口允许的最大请求数
     * @param windowSizeInMillis 窗口大小（毫秒）
     */
    public SlidingWindowLog(int rate, long windowSizeInMillis) {
        this.rate = rate;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    /**
     * 是否允许请求
     * @return 如果允许请求返回true，否则返回false
     */
    public boolean allowRequest() {
        long now = System.currentTimeMillis();
        lock.lock();
        try {
            while (!requestTimestamps.isEmpty() && requestTimestamps.peekFirst() <= now - windowSizeInMillis) {
                requestTimestamps.pollFirst(); // 移除不在窗口内的请求
            }
            if (requestTimestamps.size() < rate) {
                requestTimestamps.addLast(now); // 添加当前请求时间戳
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowLog log = new SlidingWindowLog(10, 1000); // 创建滑动窗口日志，每秒最多允许10个请求
        for (int i = 0; i < 20; i++) {
            if (log.allowRequest()) {
                System.out.println("Request allowed");
            } else {
                System.out.println("Request denied");
            }
            Thread.sleep(100); // 每100毫秒发起一次请求
        }
    }
}
