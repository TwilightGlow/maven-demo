package com.example.algorithm.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
 * 请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
 * https://leetcode.cn/problems/sequential-digits/description/
 *
 *
 * 示例 1：
 * 输出：low = 100, high = 300
 * 输出：[123,234]
 *
 * 示例 2：
 * 输出：low = 1000, high = 13000
 * 输出：[1234,2345,3456,4567,5678,6789,12345]
 *
 * @author zhangjw54
 */
public class SequentialDigits {

    @Test
    public void print() {
        int low = 100;
        int high = 300;
        System.out.println(sequentialDigits1(low, high));
        System.out.println(sequentialDigits2(low, high));
    }

    private List<Integer> sequentialDigits1(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int num = i;
            for (int j = i + 1; j <= 9; j++) {
                num = num * 10 + j;
                if (num >= low && num <= high) {
                    ans.add(num);
                }
                if (num >= high) {
                    break;
                }
            }
        }
        return ans;
    }

    public List<Integer> sequentialDigits2(int low, int high) {
        List<Integer> list = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            if (checkValid(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean checkValid(int number) {
        String str = String.valueOf(number);
        int tmp = Character.getNumericValue(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            int digit = Character.getNumericValue(str.charAt(i));
            if (digit == tmp + 1) {
                tmp = digit;
            } else {
                return false;
            }
        }
        return true;
    }
}
