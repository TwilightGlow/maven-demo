package com.example.algorithm.slidingwindow;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/solutions/310099/zui-chang-zhong-fu-zi-shu-zu-by-leetcode-solution/?envType=problem-list-v2&envId=sliding-window
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 * 示例 1：
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * <p>
 * 示例 2：
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 *
 * @author zhangjw54
 */
public class FindSameLength {

    private final int[] nums1 = {1, 2, 3, 2, 1};
    private final int[] nums2 = {3, 2, 1, 4, 7};

    @Test
    public void findLength() {
        System.out.println(dp(nums1, nums2));
        System.out.println(slidingWindow(nums1, nums2));
        System.out.println(violentSearch(nums1, nums2));
    }

    public int dp(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // dp[i][j] 表示以 nums1[i-1] 和 nums2[j-1] 结尾的最长公共子数组的长度
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxLength;
    }

    public int slidingWindow(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        // A数组的第一个元素和B数组的第一个元素对齐，然后向左滑
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        // B数组的第一个元素和A数组的第一个元素对齐，然后向左滑
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    // add代表在前面补位，数组向左滑几个就需要补几位，目的是为了让两个数组对齐
    private int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }

    public int violentSearch(int[] num1, int[] num2) {
        int max = 0;
        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num2.length; j++) {
                int k = 0;
                while (i + k < num1.length && j + k < num2.length && num1[i + k] == num2[j + k]) {
                    k++;
                }
                max = Math.max(max, k);
            }
        }
        return max;
    }

}
