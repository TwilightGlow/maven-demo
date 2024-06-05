package com.example.algorithm.reservior;

import java.util.Random;

/**
 * @author zhangjw54
 */
public class FindOne {

    private final ListNode head;
    private final Random random;

    public FindOne(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int result = head.val;
        ListNode current = head;
        int n = 1;

        while (current != null) {
            // Randomly select an index
            if (random.nextInt(n) == 0) {
                result = current.val;
            }
            n++;
            current = current.next;
        }

        return result;
    }
}
