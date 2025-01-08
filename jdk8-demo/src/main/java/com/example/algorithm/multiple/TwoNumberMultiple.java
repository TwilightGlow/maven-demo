package com.example.algorithm.multiple;

import org.junit.jupiter.api.Test;

/**
 * @author zhangjw54
 */
public class TwoNumberMultiple {

    @Test
    public void twoNumberMultiple() {
        // 示例大数
        String num1 = "12345678901234567890";
        String num2 = "98765432109876543210";

        // 计算大数乘积
        String result = multiplyLargeNumbers(num1, num2);

        // 输出结果
        System.out.println("Result: " + result);
    }

    private String multiplyLargeNumbers(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] result = new int[len1 + len2];

        // 从个位开始逐位相乘
        for (int i = len1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int product = digit1 * digit2 + result[i + j + 1];

                result[i + j + 1] = product % 10; // 当前位
                result[i + j] += product / 10; // 进位
            }
        }

        // 将结果数组转换为字符串
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            // 忽略前导零
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        // 如果结果为空，说明乘积是0
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
