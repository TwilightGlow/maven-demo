package com.example.algorithm.rateLimiting;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 令牌桶算法 ⭐（guava使用）
 * 令牌桶算法是一个存放固定容量令牌的桶，按照固定速率往桶里添加令牌。
 * 桶中存放的令牌数有最大上限，超出之后就被丢弃或者拒绝。当流量或者网络请求到达时，每个请求都要获取一个令牌，如果能够获取到，则直接处理，并且令牌桶删除一个令牌。如果获取不同，该请求就要被限流，要么直接丢弃，要么在缓冲区等待。
 *
 * 令牌桶限流大致的规则如下
 * 进水口按照某个速度，向桶中放入令牌。
 * 令牌的容量是固定的，但是放行的速度不是固定的，只要桶中还有剩余令牌，一旦请求过来就能申请成功，然后放行。
 * 如果令牌的发放速度，慢于请求到来速度，桶内就无牌可领，请求就会被拒绝。
 * 总之，令牌的发送速率可以设置，从而可以对突发的出口流量进行有效的应对。
 *
 * 令牌桶好处
 * 能够在限制调用的平均速率
 * 可以方便地应对 突发出口流量（后端能力的提升）可以方便地应对 突发出口流量（后端能力的提升）
 * 动态调整生成令牌的速率
 *
 * @author zhangjw54
 */
public class TokenBucket {

    private final long capacity; // 桶的最大容量
    private final long tokensPerSecond; // 每秒生成的令牌数
    private long tokens; // 当前令牌数
    private long lastRefillTimestamp; // 上次填充令牌的时间戳
    private final ReentrantLock lock = new ReentrantLock(); // 线程安全锁

    public TokenBucket(long capacity, long tokensPerSecond) {
        this.capacity = capacity;
        this.tokensPerSecond = tokensPerSecond;
        this.tokens = capacity; // 初始令牌数等于桶的容量
        this.lastRefillTimestamp = System.nanoTime(); // 初始化上次填充时间
    }

    /**
     * 是否允许请求
     * @return 如果允许请求返回true，否则返回false
     */
    public boolean allowRequest() {
        lock.lock();
        try {
            refill(); // 尝试填充令牌
            if (tokens > 0) {
                tokens--; // 消耗一个令牌
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 填充令牌
     */
    private void refill() {
        long now = System.nanoTime();// 获取当前时间（纳秒）
        long tokensToAdd = (now - lastRefillTimestamp) * tokensPerSecond / 1_000_000_000;// 计算从上一次填充到现在产生的令牌数
        tokens = Math.min(capacity, tokens + tokensToAdd);// 更新令牌数，但不超过桶的容量
        lastRefillTimestamp = now;// 更新上一次填充的时间戳
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket bucket = new TokenBucket(10, 1); // 创建令牌桶，容量为10，每秒生成1个令牌
        for (int i = 0; i < 50; i++) {
            if (bucket.allowRequest()) {
                System.out.println("Request allowed");
            } else {
                System.out.println("Request denied");
            }
            Thread.sleep(100); // 每100毫秒发起一次请求
        }
    }
}
