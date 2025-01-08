package com.example.algorithm.list;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * @author zhangjw54
 */
public class TwoNumsSum {

    @Test
    public void print() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSumWithMap(nums, target);
        System.out.println(result[0] + "," + result[1]);
    }

    public int[] twoSumWitDoublePointer(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        // i 可以等于 j， 防止数组元素为奇数时，有个元素遍历不到
        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            // i 检查过就放到 map 中，防止后面的 j 检查不到 i元素
            map.put(nums[i], i);
            // i 先放入map中了，i != j 防止 i 和 j 遍历到同一个元素
            if (map.containsKey(target - nums[j]) && i != j) {
                return new int[]{j, map.get(target - nums[j])};
            }
            map.put(nums[j], j);
        }

        // int i = 0, j = nums.length - 1;
        // while (i <= j) {
        //     if (map.containsKey(target - nums[i])) {
        //         return new int[]{i, map.get(target - nums[i])};
        //     }
        //     map.put(nums[i], i);
        //
        //     if (i != j && map.containsKey(target - nums[j])) {
        //         return new int[]{j, map.get(target - nums[j])};
        //     }
        //     map.put(nums[j], j);
        //
        //     i++;
        //     j--;
        // }
        return new int[]{-1, -1};
    }

    // 此方法需要先对数组进行排序
    public int[] twoSumWitSorted(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSumWithMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

}
