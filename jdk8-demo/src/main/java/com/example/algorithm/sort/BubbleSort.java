package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 冒泡排序是稳定的排序算法，时间复杂度是O(n^2)，空间复杂度是O(1)，最优情况下时间复杂度是O(n)
 *
 * @author zhangjw54
 */
public class BubbleSort {

    int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};
    
    // 优化后的冒泡排序，对于原本有序的数组，时间复杂度可以进化为O
    @Test
    public void bubbleSortOpt() {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < nums.length -1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swapped = true;
                }
            }
            if (Boolean.FALSE.equals(swapped)) {
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

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
