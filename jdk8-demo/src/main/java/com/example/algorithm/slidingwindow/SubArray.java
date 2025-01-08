package com.example.algorithm.slidingwindow;

import org.junit.jupiter.api.Test;

/**
 * 找出子数组
 *
 * @author zhangjw54
 */
public class SubArray {

    private final int[] mainArray = {1, 2, 3, 2, 1};
    private final int[] subArray = {3, 2, 1};

    @Test
    public void findSubArray() {
        System.out.println(doublePointer(mainArray, subArray));
        System.out.println(kmp(mainArray, subArray));
    }

    // 滑动窗口（或称为双指针）解法，时间复杂度O(n * m)
    private boolean doublePointer(int[] mainArray, int[] subArray) {
        int mainLength = mainArray.length;
        int subLength = subArray.length;

        // 如果子数组长度大于主数组长度，直接返回 false
        if (subLength > mainLength) {
            return false;
        }

        // 遍历主数组，检查是否存在子数组
        for (int i = 0; i <= mainLength - subLength; i++) {
            boolean isMatch = true;
            for (int j = 0; j < subLength; j++) {
                if (mainArray[i + j] != subArray[j]) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                return true;
            }
        }
        return false;
    }

    public boolean kmp(int[] mainArray, int[] subArray) {
        int n = mainArray.length;
        int m = subArray.length;

        if (m == 0) {
            return true;  // 空子数组总是被包含
        }

        int[] pi = computePrefixFunction(subArray);
        int q = 0;

        for (int i = 0; i < n; i++) {
            while (q > 0 && subArray[q] != mainArray[i]) {
                q = pi[q - 1];
            }
            if (subArray[q] == mainArray[i]) {
                q++;
            }
            if (q == m) {
                return true;
            }
        }

        return false;
    }

    private int[] computePrefixFunction(int[] pattern) {
        int m = pattern.length;
        int[] pi = new int[m];
        int k = 0;

        for (int i = 1; i < m; i++) {
            while (k > 0 && pattern[k] != pattern[i]) {
                k = pi[k - 1];
            }
            if (pattern[k] == pattern[i]) {
                k++;
            }
            pi[i] = k;
        }

        return pi;
    }

}
