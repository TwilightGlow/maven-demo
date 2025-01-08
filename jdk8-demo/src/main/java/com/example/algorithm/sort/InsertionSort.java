package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 插入排序的时间复杂度为 O(n^2)，最佳情况下为 O(n)。由于其简单性和小数据集的高效性，插入排序在某些情况下仍然是一个不错的选择。
 * 插入排序是稳定的排序算法
 *
 * @author zhangjw54
 */
public class InsertionSort {

    private int[] nums = {12, 4, 2, 8, 3, 1, 15, 7};
    // private int[] nums = {1, 2, 3, 4, 5, 1000, 6, 7, 8, 9, 10};

    @Test
    public void print() {
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private void selectionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > tmp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tmp;
        }
    }
}
