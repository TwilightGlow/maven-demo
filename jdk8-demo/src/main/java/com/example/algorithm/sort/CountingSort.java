package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 计数排序
 * <p>计数排序是一种非比较排序算法，适用于整数范围比较小的场景。它的基本思想是通过统计每个数出现的次数来确定每个数在排序后的位置，最终构建出排序后的数组。
 *
 * <p>计数排序是一种稳定的排序算法，关键在于从后往前遍历以保证稳定排序。
 * <p>当我们从后往前遍历输入数组时，遇到相同的元素时，我们会首先处理输入数组中靠后的元素，并将其放在累积计数所指示的位置上，然后将计数减一。这样，在遇到相同元素时，后出现的元素会被放置在前出现的元素之后，从而保持它们在输入数组中的相对顺序。
 *
 * <p>计数排序的时间复杂度为 O(n + k)，其中 n 是数组的长度，k 是数组中的最大值和最小值的差值。
 *
 * @author zhangjw54
 */
public class CountingSort {

    int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void countingSort() {
        countingSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private void countingSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        // 找到数组中最大值和最小值
        int max = array[0];
        int min = array[0];

        for (int num : array) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        // 创建计数数组
        int range = max - min + 1;
        int[] count = new int[range];

        // 统计每个元素的出现次数
        for (int num : array) {
            count[num - min]++;
        }

        // 计算每个元素的起始位置，count[i] 表示小于或等于 i + min 的元素的数量。
        // count数组值记录的是，前面小于或等于当前元素的元素个数
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // 创建输出数组
        int[] output = new int[array.length];

        // 将元素放入正确位置（从后往前遍历以保证稳定排序）
        for (int i = array.length - 1; i >= 0; i--) {
            int num = array[i];
            output[count[num - min] - 1] = num;
            count[num - min]--; // 将计数减一，这一步的作用就是保证相同元素的相对顺序。如果没有重复元素，可以不用这一步。
        }

        // 将排序后的数组复制回原数组
        System.arraycopy(output, 0, array, 0, array.length);
    }
}
