package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

/**
 * <pre>
 *     泰波那契序列 Tn 定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 *
 * 示例 2：
 *
 * 输入：n = 25
 * 输出：1389537
 * </pre>
 *
 * @author zhangjw54
 */
public class Tribonacci {

    private final int[] memo = new int[100];

    @Test
    public void tribonacci() {
        System.out.println(recursive(25));
        System.out.println(dp(25));
    }

    public int recursive(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = recursive(n - 1) + recursive(n - 2) + recursive(n - 3);
        return memo[n];
    }

    public int dp(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

}
