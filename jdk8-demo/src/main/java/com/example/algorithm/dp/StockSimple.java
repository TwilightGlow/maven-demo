package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

/**
 * @author zhangjw54
 */
public class StockSimple {

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。<br>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。<br>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
     */
    @Test
    public void test() {
        int[] prices = {1, 100, 50, 10000};
        System.out.println(maxProfit(prices));
    }

    // 动态规划思路的核心就是将历史操作记录下来，然后根据历史操作推导出当前操作
    private int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minValue = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else if (prices[i] - minValue > maxProfit) {
                maxProfit = prices[i] - minValue;
            }
        }
        return maxProfit;
    }

    // public int maxProfit1(int[] prices) {
    //     if (prices == null || prices.length == 0) {
    //         return 0;
    //     }
    //     int maxProfit = 0;
    //     // 遍历价格数组 1, 100, 50, 10000
    //     for (int i = 1; i < prices.length; i++) {
    //         // 如果当前价格比前一天高，意味着可以从前一天买入并在今天卖出
    //         if (prices[i] > prices[i - 1]) {
    //             maxProfit += prices[i] - prices[i - 1];
    //         }
    //     }
    //
    //     return maxProfit;
    // }
}
