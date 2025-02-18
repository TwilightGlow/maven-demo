package com.example.algorithm.rateLimiting;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 漏桶限流规则
 * （1）进水口（对应客户端请求）以任意速率流入进入漏桶。
 * （2）漏桶的容量是固定的，出水（放行）速率也是固定的。
 * （3）漏桶容量是不变的，如果处理速度太慢，桶内水量会超出了桶的容量，则后面流入的水滴会溢出，表示请求拒绝。
 *
 * 漏桶的实现思想
 * 漏桶算法的实现往往依赖于 队列，请求到达如果队列未满则直接放入队列，然后有一个处理器按照 固定频率 从队列头取出请求进行处理。
 * 如果请求量大，则会导致队列满，那么新来的请求就会被抛弃。
 *
 * 漏桶的优点与不足
 * 优点：
 * 削峰：有大量流量进入时，可以控制限流速率，多的会发生溢出，从而限流保护服务可用，起到整流的作用
 * 缓冲：不至于直接请求到服务器, 缓冲压力
 *
 * 不足：
 * 漏桶出水速度固定，也就是请求放行速度是固定的
 * 漏桶出口的速度固定，不能解决流量突发的问题，不能灵活的应对后端能力提升。比如，通过动态扩容，后端流量从1000QPS提升到1WQPS，漏桶没有办法。
 *
 * @author zhangjw54
 */
public class LeakyBucket {

    private final int capacity; // 桶的最大容量
    private final long leakRatePerSecond; // 每秒漏出的请求数
    private long lastLeakTimestamp; // 上次漏出的时间戳
    private final Queue<Long> requests = new LinkedList<>(); // 存储请求的时间戳

    /**
     * @param capacity 桶的最大容量
     * @param leakRatePerSecond 每秒漏出的请求数
     */
    public LeakyBucket(int capacity, long leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
        this.lastLeakTimestamp = System.nanoTime(); // 初始化上次漏出时间
    }

    /**
     * 是否允许请求
     * @return 如果允许请求返回true，否则返回false
     */
    public synchronized boolean allowRequest() {
        leak(); // 尝试漏出请求
        if (requests.size() < capacity) {
            requests.add(System.nanoTime()); // 添加请求时间戳
            return true;
        }
        return false;
    }

    /**
     * 漏出请求
     */
    private void leak() {
        long now = System.nanoTime();// 获取当前时间（纳秒）
        long elapsed = now - lastLeakTimestamp;// 计算自上次漏水以来的时间差（纳秒）
        long leaks = elapsed * leakRatePerSecond / 1_000_000_000;// 计算在时间差内应该漏出的请求数
        for (int i = 0; i < leaks && !requests.isEmpty(); i++) {
            requests.poll(); // 移除漏出的请求
        }
        lastLeakTimestamp = now;// 更新上一次漏水的时间戳
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucket bucket = new LeakyBucket(10, 1); // 创建漏桶，容量为10，每秒漏出1个请求
        for (int i = 0; i < 20; i++) {
            if (bucket.allowRequest()) {
                System.out.println("Request allowed");
            } else {
                System.out.println("Request denied");
            }
            Thread.sleep(100); // 每100毫秒发起一次请求
        }
    }
}
