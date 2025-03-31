package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 希尔排序是基于插入排序的改进，是一种不稳定的排序算法 <a href='https://blog.csdn.net/qq_42449106/article/details/130833591'>希尔排序</a>
 *
 * 希望排序也是内排序的时间复杂度为 O(nlogn)，空间复杂度为 O(1) <br>
 *
 * 不同的插入排序过程中，因为相同的元素可能在各自的插入排序中移动，最后其稳定性就会被打乱，所以shell排序是不稳定的（跨组交换）
 *
 * @see InsertionSort
 * @author zhangjw54
 */
public class ShellSort {

    private final int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void print() {
        shellSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private void shellSort(int[] nums) {
        int n = nums.length;
        // 初始化增量为 n/2，不断缩小增量直到 1
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每个增量进行插入排序
            for (int i = gap; i < n; i++) {
                int temp = nums[i]; // 保存当前元素
                int j = i;
                // 在当前增量的分组内进行插入排序
                while (j >= gap && nums[j - gap] > temp) {
                    nums[j] = nums[j - gap];
                    j -= gap;
                }
                nums[j] = temp; // 插入当前元素
            }
        }
    }
}
