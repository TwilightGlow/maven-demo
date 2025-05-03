package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给定不同面额的硬币coins和一个总金额amount，编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果无法凑成总金额，则返回-1。
 * 01背包问题 - 硬币不能重复
 *
 * <p>这是一个典型的01背包问题，要求每种硬币只能使用一次。如果从前向后遍历金额（i），在更新dp[i]时，dp[i - coin]可能已经被当前硬币的计算结果覆盖，从而导致同一硬币被重复使用。
 * <p>从后向前遍历时，dp[i - coin]的值在当前硬币的计算过程中不会被覆盖，因此可以确保每种硬币只被使用一次。
 *
 * <p>
 *     假设当前硬币为coin = 3，如果从前向后遍历：
 *  当i = 3时，dp[3] = dp[3 - 3] + 1 = dp[0] + 1 = 1。
 *  当i = 6时，dp[6] = dp[6 - 3] + 1 = dp[3] + 1 = 2。此时dp[3]已经被更新，导致硬币3被重复使用。
 *      而从后向前遍历时：
 *  当i = 6时，dp[6] = dp[6 - 3] + 1 = dp[3] + 1，此时dp[3]仍然是初始值，确保硬币3只被使用一次。
 * </p>
 */
public class CoinUnique {

    private final int[] coins = {1, 3, 5, 10};
    private final int amount = 11;
    private final int[] dp = new int[amount + 1];

    @Test
    public void dp() {
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = amount; i >= coin; i--) { // 从后往前遍历，避免重复使用同一硬币
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        int minTotal = dp[amount] > amount ? -1 : dp[amount];
        System.out.println("最少硬币个数：" + minTotal);
    }
}
