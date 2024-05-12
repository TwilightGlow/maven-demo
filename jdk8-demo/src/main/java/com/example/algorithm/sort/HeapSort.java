package com.example.algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zhangjw54
 */
public class HeapSort {

    int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void heapSort() {
        int n = nums.length;
        // 构建最大堆，从最后一个非叶子节点开始
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }
        // 依次取出堆顶元素并调整堆
        for (int i = n - 1; i > 0; i--) {
            // 交换堆顶元素和当前未排序部分的最后一个元素，此时堆顶元素就是num[0]
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            // 调整堆
            heapify(nums, i, 0);
        }
        System.out.println(Arrays.toString(nums));
    }

    // 堆化，作用是调整n个元素堆中第i个元素的位置
    // 构建一个大顶堆，n表示堆中元素的格式，i表示元素的位置，从i节点开始向下调整
    private void heapify(int[] nums, int n, int i) {
        int largest = i; // 假设当前节点是最大值
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 找出当前节点、左子节点和右子节点中的最大值
        if (left < n && nums[left] > nums[largest]) {
            largest = left;
        }

        if (right < n && nums[right] > nums[largest]) {
            largest = right;
        }

        // 如果最大值不是当前节点，交换节点值并继续向下调整
        if (largest != i) {
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;

            heapify(nums, n, largest);
        }
    }
}
