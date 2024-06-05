package com.example.algorithm.reservior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhangjw54
 */
public class FindK {

    private final ListNode head;
    private final Random random;

    public FindK(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int[] getRandom(int k) {
        List<Integer> reservoir = new ArrayList<>(k);
        ListNode current = head;

        // Fill the reservoir array with the first k elements
        for (int i = 0; i < k && current != null; i++) {
            reservoir.add(current.val);
            current = current.next;
        }

        int n = k;
        // Continue traversing the list
        // 每个节点被选中的概率是 k/i
        while (current != null) {
            int j = random.nextInt(n + 1);
            if (j < k) {
                reservoir.set(j, current.val);
            }
            n++;
            current = current.next;
        }

        // Convert the reservoir list to an array and return it
        return reservoir.stream().mapToInt(i -> i).toArray();
    }
}
