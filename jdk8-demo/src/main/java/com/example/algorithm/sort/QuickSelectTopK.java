package com.example.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zhangjw54
 */
public class QuickSelectTopK {

    private int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};
    private int k = 6;

    @Test
    public void print() {
        int[] topK = findTopK(nums, k);
        System.out.println(Arrays.toString(topK));
    }

    private int[] findTopK(int[] nums, int k) {
        if (k >= nums.length) {
            return nums;
        }
        quickSelect(nums, 0, nums.length - 1, nums.length - k);
        return Arrays.copyOfRange(nums, nums.length - k, nums.length);
    }

    private void quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) return;

        int middle = partition(nums, start, end);

        if (middle == k) {
            return; // 找到第k大的元素
        } else if (middle < k) {
            quickSelect(nums, middle + 1, end, k); // 在右半部分继续查找
        } else {
            quickSelect(nums, start, middle - 1, k); // 在左边部分继续查找
        }
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
