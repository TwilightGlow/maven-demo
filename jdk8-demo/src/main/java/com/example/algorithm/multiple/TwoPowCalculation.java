package com.example.algorithm.multiple;

import org.junit.jupiter.api.Test;

/**
 * @author zhangjw54
 */
public class TwoPowCalculation {

    @Test
    public void twoPow() {
        int[] resultArray = new int[3000];
        resultArray[0] = 1;
        int resultSize = 1;
        int exponent = 24;
        for (int i = 0; i < exponent; i++) {
            int carry = 0; // 进位
            for (int j = 0; j < resultSize; j++) {
                int product = resultArray[j] * 2 + carry;
                resultArray[j] = product % 10; // 当前位
                carry = product / 10; // 进位
            }
            if (carry > 0) {
                resultArray[resultSize] = carry;
                resultSize++;
            }
        }
        for (int i = resultSize - 1; i >= 0; i--) {
            System.out.print(resultArray[i]);
        }
    }
}
