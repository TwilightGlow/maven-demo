package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 题目描述: 最大上升子序列（Longest Increasing Subsequence，LIS）
 * 给定一个整数数组nums，找到其中最长的严格递增子序列的长度。严格递增子序列是指子序列中的元素严格单调递增，即对于任意i < j，满足nums[i] < nums[j]。
 *
 * <p> 例如，对于输入数组nums = [10, 9, 2, 5, 3, 7, 101, 18]，最长的严格递增子序列为[2, 3, 7, 101]，长度为4。
 */
public class Lis {

    // int[] nums = new int[]{1, 3, 2, 5, 9, 7, 100, 99, 6, 8, 5, 9};
    int[] nums = new int[]{1, 3, 3, 4, 5, 1};

    // 使用递归，记忆化搜索，带备忘录的递归，如果不用记忆化时间复杂度为O(n * 2^n)
    final Map<Integer, Integer> map = new HashMap<>();
    final Map<Integer, Integer> cache = new HashMap<>();
    @Test
    public void recursive() {
        int ans = IntStream.range(0, nums.length)
                .map(i -> recursiveSearch(nums, i))
                .max().orElse(0);
        System.out.println("最大上升子序列: " + ans);

        int ans1 = IntStream.range(0, nums.length)
                .map(i -> recursiveContinuousSearch(nums, i))
                .max().orElse(0);
        System.out.println("最大连续上升子序列: " + ans1);
    }

    private int recursiveSearch(int[] nums, int index) {
        if (index == nums.length - 1) return 1;
        if (map.containsKey(index)) return map.get(index);
        int maxLength = 1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                maxLength = Math.max(maxLength, recursiveSearch(nums, i) + 1);
            }
        }
        map.put(index, maxLength);
        return maxLength;
    }

    private int recursiveContinuousSearch(int[] nums, int index) {
        if (index == nums.length - 1) return 1;
        if (cache.containsKey(index)) return cache.get(index);
        int maxLength = 1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] == nums[index] + 1) {
                maxLength = Math.max(maxLength, recursiveContinuousSearch(nums, i) + 1);
            } else {
                break;
            }
        }
        cache.put(index, maxLength);
        return maxLength;
    }

    // 使用动态规划，时间复杂度 O(n^2)
    @Test
    public void dynamicPlanning() {
        int ans = lengthOfLIS(nums);
        System.out.println("最大上升子序列: " + ans);
    }

    private int lengthOfLIS(int[] nums) {
        int[] f = new int[nums.length];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    @Test
    public void dynamicPlanning2() {
        int[] f = new int[nums.length] ;
        Arrays.fill(f, 1);
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        int ans = Arrays.stream(f).max().orElse(0);
        System.out.println("最大上升子序列: " + ans);
    }


}
