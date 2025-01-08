package com.example.algorithm.greedy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangjw54
 */
public class Coin {

    /**
     * 钱币找零问题
     * 给定一组硬币面额和一个总金额，找出最少数量的硬币组合来达到总金额（假设硬币面额有1元时适用）。
     * 每次选择面额最大的硬币可以确保硬币数量的最少化
     */
    @Test
    public void coinChange() {
        int[] coins = {1, 2, 5, 10, 20, 50, 100};
        int amount = 93;
        // 通常情况下，贪心算法的时间复杂度低于动态规划，尤其是当amount无限大时
        int minNumber = greedy(coins, amount);
        System.out.println("最小数量：" + minNumber);
    }

    // 时间复杂度：O(nlogn + n) = O(nlogn)
    private int greedy(int[] coins, int amount) {
        int[] reversedOrderCoins = Arrays.stream(coins).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        int total = 0;
        int remainingAmount = amount;
        for (int coin : reversedOrderCoins) {
            if (remainingAmount == 0) {
                break;
            }
            if (remainingAmount >= coin) {
                int count = remainingAmount / coin;
                total += count;
                remainingAmount -= count * coin;
            }
        }
        // 如果剩余金额不为0，说明无法凑出准确的金额
        if (remainingAmount != 0) {
            throw new IllegalArgumentException("无法凑出准确的金额！");
        }
        return total;
    }

    // 时间复杂度：O(n * amount)
    private int dp(int[] coins, int amount) {
        // 定义dp数组，初始化为amount+1，表示最大值（因为最少硬币数不会超过amount）
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 初始化为一个不可能的高值
        dp[0] = 0; // 金额为0时，硬币数为0

        // 遍历每一个金额
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // 如果dp[amount]没有被更新，返回-1表示无法凑出该金额
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
