package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 快速排序和归并排序的区别:
 * <p> 快速排序是另外一种分治的排序算法，它将一个数组分成两个子数组，将两部分独立的排序。
 * <p> 快速排序和归并排序是互补的∶归并排序将数组分成两个子数组分别排序，并将有序的子数组归并从而将整个数组排序，而快速排序的方式则是当两个数组都有序时，整个数组自然就有序了。
 * <p> 在归并排序中，一个数组被等分为两半，归并调用发生在处理整个数组之前，在快速排序中，切分数组的位置取决于数组的内容，递归调用发斯在处理整个数组之后。
 *
 * <p>
 *  虽然归并排序和快速排序的时间复杂度都为O（nlogn），但实际上快速排序的速度会比归并排序快2-3倍，原因如下：
 *    1.归并排序在执行时，需要一个额外的temp数组去拷贝原数组的数据，会大量占用程序的空间。
 *    2.快速排序再运行时，实际上是直接再原数组进行递归操作，并不会占用额外的空间。
 * </p>
 *
 * @author zhangjw54
 */
public class MergeSort {

    int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void mergeSort() {
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        // 将原数组按照mid切分成两个数组
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        // 给左右两个数组赋值，这一步是为了拷贝数据
        for (int i = 0; i < n1; i++) {
            L[i] = nums[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = nums[mid + 1 + j];
        }
        // 两个指针，每次从左右两个数组中取最小值，将排序好的值更新到原数组中
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // 取左边数组的元组放入原数组
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;
            } else {
                // 取右边数组的元组放入原数组
                nums[k] = R[j];
                j++;
            }
            // k代表原数组下标的位置
            k++;
        }

        // 有可能左边数组的第一个元素比右边数组的每一个元素都大，因此上面的循环完还需要保证i=n1并且j=n2
        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }
}
