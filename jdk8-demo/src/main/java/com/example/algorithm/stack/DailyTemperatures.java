package com.example.algorithm.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <pre>
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 *
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 * </pre>
 */
public class DailyTemperatures {

    private final int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};

    @Test
    public void print() {
        int[] result = dailyTemperatures(nums);
        System.out.println(Arrays.toString(result));
    }

    private int[] dailyTemperatures(int[] nums) {
        LinkedList<Integer> monotonicStack = new LinkedList<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, 0);
        for (int i = 0; i < nums.length; i++) {
            // 如果当前温度大于栈顶元素的温度，则说明找到了更高的温度
            while (!monotonicStack.isEmpty() && nums[monotonicStack.peek()] < nums[i]) {
                int index = monotonicStack.pop();
                result[index] = i - index;
            }
            // 将当前索引入栈
            monotonicStack.push(i);
        }
        return result;
    }
}
