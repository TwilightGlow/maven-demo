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
        // 建堆的过程就是将每个非叶子节点（从后往前）逐个向下堆化
        // 假如有N个节点，那么高度为H=logN，最后一层每个父节点最多只需要下调1次，倒数第二层最多只需要下调2次，
        // 顶点最多需要下调H次，而最后一层父节点共有2^(H-1)个,倒数第二层公有2^(H-2),顶点只有1(2^0)个，
        // 所以总共的时间复杂度为s = 1 * 2^(H-1) + 2 * 2^(H-2) + ... + (H-1) * 2^1 + H * 2^0将H代入后s= 2N - 2 - log2(N)，
        // 近似的时间复杂度就是O(N)。经评论提醒，H=log2(N)  +  1
        // 作者：wuxinliulei
        // 链接：https://www.zhihu.com/question/20729324/answer/231955716
        // 来源：知乎
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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

    // 堆化，作用是在n个元素堆中调整第i个元素的位置。堆化指的是将一个节点以及它的子节点调整到符合堆的性质。
    // 将以i为根节点的子树调整为堆
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
