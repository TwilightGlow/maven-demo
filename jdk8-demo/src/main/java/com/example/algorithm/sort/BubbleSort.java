package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zhangjw54
 */
public class BubbleSort {

    int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void bubbleSort() {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length -1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
