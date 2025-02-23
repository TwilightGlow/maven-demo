package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

/**
 * @author zhangjw54
 */
public class BinarySearch {

    private int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 15};

    @Test
    public void print() {
        System.out.println(binarySearch(nums, 15));
    }

    /**
     * 二分查找
     *
     * @param nums 有序数组
     * @param target 目标值
     * @return 目标值的索引，如果不存在返回-1
     */
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1; // 目标元素在右半部分
            } else {
                right = mid - 1; // 目标元素在左半部分
            }
        }
        return -1;
    }
}
