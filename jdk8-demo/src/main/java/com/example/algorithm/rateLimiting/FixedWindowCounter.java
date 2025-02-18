package com.example.algorithm.rateLimiting;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 计数器（固定窗口）
 * 计数器限流（Fixed Window）算法，又称固定窗口算法， 是最为简单粗暴的解决方案，通过在单位时间内维护的计数器来控制该时间单位内的最大访问量。
 * 主要用来限制总并发数，比如数据库连接池大小、线程池大小、接口访问并发数等都是使用计数器算法。例如：使用 AomicInteger 来进行统计当前正在并发执行的次数，如果超过阈值就直接拒绝请求，提示系统繁忙。
 * 原文链接：https://blog.csdn.net/sluck_0430/article/details/136311473
 *
 * 优点：
 * 实现简单，容易理解
 * 占用内存小
 *
 * 缺点：
 * 不够平滑，会导致以下问题：
 * 一段时间内服务不可用——比如 10s 内限流 100 次请求，但是 0s-1s 就来了 100 个请求，则 2s-10s 的请求都是被拒绝的，无法处理。
 * 临界问题，无法保证限流速率，窗口切换时可能会出现两倍阈值的请求——用户通过在时间窗口的重置节点处突发请求， 可以瞬间超过我们的速率限制。假设有一个恶意用户，他在0:59时，瞬间发送了100个请求，并且1:00又瞬间发送了100个请求，那么其实这个用户在 1秒里面，瞬间发送了200个请求。
 *
 * @author zhangjw54
 */
public class FixedWindowCounter {

    private final int rate; // 每个窗口允许的最大请求数
    private final long windowSizeInMillis; // 窗口大小（毫秒）
    private long windowStart; // 当前窗口的起始时间
    private int requestCount; // 当前窗口内的请求数
    private final ReentrantLock lock = new ReentrantLock(); // 线程安全锁

    /**
     * 构造函数
     * @param rate 每个窗口允许的最大请求数
     * @param windowSizeInMillis 窗口大小（毫秒）
     */
    public FixedWindowCounter(int rate, long windowSizeInMillis) {
        this.rate = rate;
        this.windowSizeInMillis = windowSizeInMillis;
        this.windowStart = System.currentTimeMillis();
        this.requestCount = 0;
    }

    /**
     * 是否允许请求
     * @return 如果允许请求返回true，否则返回false
     */
    public boolean allowRequest() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            if (now - windowStart >= windowSizeInMillis) {
                windowStart = now;
                requestCount = 0; // 重置请求计数
            }
            if (requestCount < rate) {
                requestCount++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FixedWindowCounter counter = new FixedWindowCounter(10, 1000); // 创建固定窗口计数器，每秒最多允许10个请求
        for (int i = 0; i < 20; i++) {
            if (counter.allowRequest()) {
                System.out.println("Request allowed");
            } else {
                System.out.println("Request denied");
            }
            Thread.sleep(100); // 每100毫秒发起一次请求
        }
    }
}
