package com.example.algorithm.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,3,4,3]
 * 输出: [2,3,4,-1,4]
 *\
 * @author zhangjw54
 */
public class NextGreaterElement {

    private final int[] nums = {5, 2, 1, 6, 2};

    @Test
    public void print() {
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }

    public int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        // 单调栈
        LinkedList<Integer> stack = new LinkedList<>();
        int[] res = new int[length];
        Arrays.fill(res, -1);
        for (int i = 0; i < length * 2 - 1; i++) {
            int index = i % length;
            while(!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                int tmp = stack.pop();
                res[tmp] = nums[index];
            }
            // 只在第一次遍历时入栈
            if (i < length) {
                stack.push(index);
            }
        }
        return res;
    }
}
