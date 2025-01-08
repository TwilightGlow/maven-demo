package com.example.algorithm.reservior;

import org.junit.jupiter.api.Test;

/**
 * @author zhangjw54
 */
public class FindKthElementInSortedArray {

    @Test
    public void find() {
        int[] nums1 = {1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] nums2 = {2};
        System.out.println("Median: " + findMedianSortedArrays(nums1, nums2)); // Output: 2.0

        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("Median: " + findMedianSortedArrays(nums1_2, nums2_2)); // Output: 2.5
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalLength = m + n;

        if (totalLength % 2 == 1) {
            // 如果总长度是奇数，返回中间的一个元素
            return findKthElement(nums1, nums2, totalLength / 2 + 1);
        } else {
            // 如果总长度是偶数，返回中间的两个元素的平均值
            return (findKthElement(nums1, nums2, totalLength / 2) + findKthElement(nums1, nums2, totalLength / 2 + 1)) / 2.0;
        }
    }

    private static int findKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int newIndex1 = Math.min(index1 + k / 2 - 1, length1 - 1);
            int newIndex2 = Math.min(index2 + k / 2 - 1, length2 - 1);
            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
