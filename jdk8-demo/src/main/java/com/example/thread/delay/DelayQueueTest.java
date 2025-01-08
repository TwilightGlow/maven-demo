package com.example.thread.delay;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjw54
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Delayed> delayQueue = new DelayQueue<>();

        // 模拟生产者添加任务（实际应用中可能需要独立的生产者线程）
        // delayQueue.put(new DelayedTask("Task-1", 3000));
        // delayQueue.put(new DelayedTask("Task-2", 1000));
        // delayQueue.put(new DelayedTask("Task-3", 5000));
        delayQueue.put(new Delayed() {

            final String taskName = "Task-4";
            final long executeTime = System.currentTimeMillis() + 4000; // 延迟4秒

            @Override
            public long getDelay(TimeUnit unit) {
                long remaining = executeTime - System.currentTimeMillis();
                return unit.convert(remaining, TimeUnit.MILLISECONDS);
            }

            @Override
            public int compareTo(Delayed o) {
                return Long.compare(this.executeTime, ((Delayed) o).getDelay(TimeUnit.MILLISECONDS));
            }

            @Override
            public String toString() {
                return "Task: " + taskName + " (Execute at: " + executeTime + ")";
            }
        });

        // 消费者循环取出任务
        while (!delayQueue.isEmpty()) {
            Delayed task = delayQueue.take(); // 阻塞直到有任务到期
            System.out.println("Executed: " + task + " at " + System.currentTimeMillis());
        }
    }

    static class DelayedTask implements Delayed {
        private final String taskName;     // 任务名称
        private final long executeTime;    // 执行时间（时间戳，单位：毫秒）

        public DelayedTask(String taskName, long delayMs) {
            this.taskName = taskName;
            this.executeTime = System.currentTimeMillis() + delayMs;
        }

        // 获取剩余延迟时间（动态计算）
        @Override
        public long getDelay(TimeUnit unit) {
            long remaining = executeTime - System.currentTimeMillis();
            return unit.convert(remaining, TimeUnit.MILLISECONDS);
        }

        // 按到期时间排序（优先级队列内部使用）
        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.executeTime, ((DelayedTask) o).executeTime);
        }

        @Override
        public String toString() {
            return "Task: " + taskName + " (Execute at: " + executeTime + ")";
        }
    }
}
