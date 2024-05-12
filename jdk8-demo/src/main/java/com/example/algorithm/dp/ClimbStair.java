package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

/**
 * 假设你正在爬楼梯，需要n阶才能到达楼顶。每次你可以爬1个或者2个台阶，有多少种不同的方式可以到达楼顶
 *
 * @author zhangjw54
 */
public class ClimbStair {

    private final int n = 10;
    private final int[] memo = new int[n + 1];

    @Test
    public void recursive() {
        System.out.println(recursive(n));
    }

    @Test
    public void dp() {
        System.out.println(dp(n));
        System.out.println(dpOptimize(n));
    }

    private int recursive(int n) {
        if (n < 3) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = recursive(n - 1) + recursive(n - 2);
        return memo[n];
    }

    // 时间复杂度O(n)
    private int dp(int n) {
        if (n < 3) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 优化空间复杂度
    private int dpOptimize(int n) {
        if (n < 3) return n;
        int pre = 1, cur = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = cur;
            cur = pre + cur;
            pre = tmp;
        }
        return cur;
    }

}
