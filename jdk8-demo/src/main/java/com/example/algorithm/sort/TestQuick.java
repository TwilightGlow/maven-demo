package com.example.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zhangjw54
 */
public class TestQuick {

    private int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void print() {
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;

        int middle = partition(nums, start, end);

        quickSort(nums, start, middle - 1);
        quickSort(nums, middle + 1, end);

    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start + 1, right = end;
        while (left <= right) {
            if (nums[left] > pivot && nums[right] < pivot) {
                ArrayUtil.swap(nums, left, right);
                left++;
                right--;
            }
            if (nums[left] <= pivot) left++;
            if (nums[right] >= pivot) right--;
        }
        ArrayUtil.swap(nums, start, right);
        return right;
    }
}
