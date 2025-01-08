package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 1. 性能优越
 * 时间复杂度：TimSort 的平均和最坏情况时间复杂度为 O(nlogn)，在面对随机数据时表现良好。同时，在数据已经部分排序的情况下，它的性能更优，能够达到 O(n) 的时间复杂度。
 * 适应性：TimSort 对部分已排序的数据具有很好的适应性。当输入数据是部分排序或已经有序时，TimSort 可以利用这个特性进行高效排序。
 *
 * 2. 稳定性
 * 保持元素相对顺序：TimSort 是一个稳定的排序算法，这意味着相同元素的相对顺序在排序后保持不变。这在许多应用场景中（例如，排序多个字段的记录）是非常重要的。
 *
 * 3. 合并排序的优势
 * 归并排序的优势：TimSort 是一种基于归并排序的算法，利用了归并排序在处理大型数据集时的高效性。它通过将数据分割成小块（称为“run”），然后将这些小块合并来构建最终的排序结果。
 *
 * 4. 运行时优化
 * 使用自然顺序：TimSort 会首先查找数据中的“自然顺序”运行（即已经排序的部分），并利用这些运行来提高排序效率。它在处理实际数据时，通常能够减少比较和移动的次数。
 * 小数据集优化：TimSort 在处理小数据集时，会使用插入排序，这在小规模数据上非常高效。插入排序的时间复杂度为 O(n^2)，但在小数据集上其常数因子较小，因此执行速度较快。
 *
 * 5. 广泛的应用和验证
 * 广泛使用：TimSort 由 Google 首次实现并应用于 Python 的排序函数，后续被 Java 采用。它经过广泛的实际使用和验证，表现出较高的稳定性和性能。
 *
 * 6. 内存使用
 * 空间复杂度：TimSort 的空间复杂度为 O(n)，这在许多情况下是可以接受的，尤其是在处理大型数据集时。
 *
 * @author zhangjw54
 */
public class TimSort {

    // Java中RUN默认等于32
    private final int RUN = 4;
    private final int[] nums = {12, 4, 6, 8, 3, 1, 15, 7, 3};

    @Test
    public void print() {
        timSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private void timSort(int[] nums) {
        int n = nums.length;
        // 对每个子数组进行插入排序
        for (int start = 0; start < n; start += RUN) {
            int end = Math.min(start + RUN - 1, n - 1);
            insertionSort(nums, start, end);
        }
        // 合并已排序的子数组
        for (int size = RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                // 合并两个子数组
                if (mid < right) {
                    merge(nums, left, mid, right);
                }
            }
        }
    }

    // 插入排序对于已经排好序的数组效率很高，时间复杂度是O(n)
    // 对比冒泡排序稳定性和时间复杂度和插入排序一样，但是如果数组已经排好序，其中一个元素是无序的，冒泡排序遍历到这个元素后需要对每个元素两两交换并遍历两轮，而插入排序只需要每轮交换一个元素遍历一轮
    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // 合并两个已排序的子数组
    private void merge(int[] array, int left, int mid, int right) {
        // 找到两个子数组的大小
        int len1 = mid - left + 1;
        int len2 = right - mid;

        // 创建临时数组
        int[] leftArray = new int[len1];
        int[] rightArray = new int[len2];

        // 将数据复制到临时数组
        System.arraycopy(array, left, leftArray, 0, len1);
        System.arraycopy(array, mid + 1, rightArray, 0, len2);

        // 合并两个临时数组
        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        // 复制剩余元素
        while (i < len1) {
            array[k++] = leftArray[i++];
        }
        while (j < len2) {
            array[k++] = rightArray[j++];
        }
    }
}
