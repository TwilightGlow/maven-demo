package com.example.queue;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 堆排序的时间复杂度是O(nlogn)
 *
 * @author zhangjw54
 */
public class PriorityQueueTest {

    private final int[] nums = {3, 1, 2, 5, 4, 10000, 50, 100, 1000, 9, 6, 7};

    @Test
    public void smallHeap() {
        // PriorityQueue默认是小顶堆，用于处理TopK问题，找出最大的K的值
        // 建堆的时间复杂度是O(n)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.offer(num);
        }

        // 建的堆只能保证父节点小于于子节点，如果遍历的话并不能保证整体的有序性
        System.out.print("使用for循环遍历: ");
        for (Integer i : priorityQueue) {
            System.out.print(i + " ");
        }

        Iterator<Integer> iterator = priorityQueue.iterator();
        System.out.print("\n使用iterator遍历: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // 使用poll()取出堆顶元素，并重新构建堆，每次堆化的时间复杂度是O(logn)
        // 因此堆排序总的时间复杂度是O(n) + O(nlogn)
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
    }

    @Test
    public void bigHeap() {
        // PriorityQueue可通过传入比较器实现大顶堆，用于处理TopK问题，找出最小的K的值
        // 方式一：
        // PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        // 方式二：
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(5);
        priorityQueue.offer(4);
        // 堆排序只能保证根节点的元素是最小的，如果遍历的话并不能保证有序性
        System.out.println("使用for循环遍历");
        for (Integer i : priorityQueue) {
            System.out.print(i);
        }
        Iterator<Integer> iterator = priorityQueue.iterator();
        System.out.println("\n使用iterator遍历");
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        // 使用poll()取出堆顶元素，并重新构建堆
        System.out.println("\n使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
        System.out.println("使用poll()取出堆顶元素：" + priorityQueue.poll());
    }
}
