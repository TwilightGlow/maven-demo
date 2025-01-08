package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 选择排序因为是不稳定的排序算法，在实际场景中使用很少 <br>
 * 比如 {3A, 2, 3B, 1}，第一轮排序后变为 {1, 2, 3B, 3A}，3A和3B的相对位置发生了变化 <br>
 *
 * 选择排序的最好和最差时间复杂度都是 O(n^2)，平均时间复杂度也是 O(n^2)
 *
 * @author zhangjw54
 */
public class SelectionSort {

    private final int[] nums = {12, 4, 6, 8, 3, 3, 15, 7, 3};

    @Test
    public void print() {
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            // 如果找到的最小值不是当前值，则交换
            if (minIndex != i) {
                int tmp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = tmp;
            }
        }
    }
}
