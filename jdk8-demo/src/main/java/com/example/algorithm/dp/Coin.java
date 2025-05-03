package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给定不同面额的硬币coins和一个总金额amount，编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果无法凑成总金额，则返回-1。
 * 完全背包问题 - 硬币可以重复
 *
 * @author zhangjw54
 */
public class Coin {

    private final int[] coins = {2, 2, 5, 10};
    private final int amount = 56;
    private final int[] cache = new int[amount + 1];
    private final int[] dp = new int[amount + 1];

    @Test
    public void coinCharge() {
        Arrays.fill(cache, -2);
        int minTotal = recursive(amount);
        System.out.println("最少硬币个数：" + minTotal);
    }

    @Test
    public void dp() {
        int minTotal = dp(amount);
        System.out.println("最少硬币个数：" + minTotal);
    }

    private int recursive(int resAmount) {
        if (resAmount == 0) return 0;
        if (resAmount < 0) return -1;

        if (cache[resAmount] != -2) {
            return cache[resAmount];
        }
        int minTotal = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = recursive(resAmount - coin);
            if (res >= 0 && res < minTotal) {
                minTotal = res + 1;
            }
        }
        cache[resAmount] = minTotal == Integer.MAX_VALUE ? -1 : minTotal;
        return cache[resAmount];
    }

    private int dp(int amount) {
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
