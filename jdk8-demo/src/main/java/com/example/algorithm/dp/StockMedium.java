package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

/**
 * @author zhangjw54
 */
public class StockMedium {

    /**
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     */
    @Test
    public void test() {
        int[] prices = {1, 100, 50, 10000};
        System.out.println(dp(prices));
    }

    private int greedy(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    private int dp(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    private int dpOptimize(int[] prices) {
        int n = prices.length;
        int maxProfit = 0, maxPurchaseSurplus = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(maxProfit, maxPurchaseSurplus + prices[i]);
            int newDp1 = Math.max(maxPurchaseSurplus, maxProfit - prices[i]);
            maxProfit = newDp0;
            maxPurchaseSurplus = newDp1;
        }
        return maxProfit;
    }


}
