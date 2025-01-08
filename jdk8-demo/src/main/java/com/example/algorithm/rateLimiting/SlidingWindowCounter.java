package com.example.algorithm.rateLimiting;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 滑动窗口⭐（Sentinel使用）
 *      滑动窗口计数器算法 算的上是固定窗口计数器算法的升级版。最早接触滑动窗口是在TCP协议，流量控制和拥塞控制。
 *      为了防止瞬时流量，可以把固定窗口近一步划分成多个格子。
 *      每次向后移动一小格，而不是固定窗口大小，这就是滑动窗口（Sliding Window）。
 *
 * 例如，定义时间窗口为5秒，滑动窗口不断向前移动，控制这5秒内的请求数目。时间窗口的跨度越长时，限流效果就越平滑。
 * 原文链接：https://blog.csdn.net/sluck_0430/article/details/136311473
 *
 * 优点：
 * 和固定窗口算法相比，避免了临界问题
 * 和漏桶算法相比，新来的请求也能够被处理到，避免了饥饿问题
 *
 * 缺点：
 * 不够平滑——当窗口中流量到达阈值时，流量会瞬间切断
 *
 * 滑动窗口计数的不够平滑主要体现在以下几个方面：
 * 1. 窗口边界效应
 * 请求积累：在时间窗口的边界，尤其是接近时间窗口末尾时，如果请求数达到了阈值（rate），那么在当前窗口内将不再允许新的请求。
 * 瞬间恢复：当时间窗口滑动到下一段时，之前积累的请求数被清除，新窗口开始计数，这时会瞬间恢复请求处理能力。这种瞬间的变化会导致流量的突增。
 * 2. 窗口内的请求分布不均
 * 突发流量：如果在一个小的时间段内（例如一个桶的时间大小）突然出现大量请求，这些请求会集中在当前桶内，可能会导致当前窗口内的请求数迅速达到阈值。
 * 瞬间切断：在这种情况下，当前窗口内的请求处理会被瞬间切断，直到窗口滑动到下一段。
 *
 * @author zhangjw54
 */
public class SlidingWindowCounter {

    private final int rate; // 每个窗口允许的最大请求数
    private final int bucketCount; // 窗口内的桶数量
    private final long bucketSizeInMillis; // 每个桶的时间大小（毫秒）
    private final Map<Long, Integer> buckets = new HashMap<>(); // 存储每个桶的请求数
    private final ReentrantLock lock = new ReentrantLock(); // 线程安全锁

    /**
     * 构造函数
     * @param rate 每个窗口允许的最大请求数
     * @param windowSizeInMillis 窗口大小（毫秒）
     * @param bucketCount 窗口内的桶数量
     */
    public SlidingWindowCounter(int rate, long windowSizeInMillis, int bucketCount) {
        this.rate = rate;
        this.bucketCount = bucketCount;
        this.bucketSizeInMillis = windowSizeInMillis / bucketCount;
    }

    /**
     * 获取当前时间所属的桶
     * @return 当前时间所属的桶
     */
    private long getCurrentBucket() {
        long now = System.currentTimeMillis();
        return now / bucketSizeInMillis;
    }

    /**
     * 是否允许请求
     * @return 如果允许请求返回true，否则返回false
     */
    public boolean allowRequest() {
        long currentBucket = getCurrentBucket();
        lock.lock();
        try {
            // 移除过期的桶
            buckets.entrySet().removeIf(entry -> entry.getKey() <= currentBucket - bucketCount);

            // 计算滑动窗口内的总请求数
            int totalRequests = buckets.values().stream().mapToInt(Integer::intValue).sum();

            if (totalRequests < rate) {
                buckets.put(currentBucket, buckets.getOrDefault(currentBucket, 0) + 1);
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowCounter counter = new SlidingWindowCounter(10, 1000, 10); // 创建滑动窗口计数，每秒最多允许10个请求，划分为10个桶
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
