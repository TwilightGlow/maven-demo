package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <pre>
 *     假设我们有一个背包能容纳的最大重量是W，有一系列物品，每个物品有自己的重量和价值。
 *     我们的目标是找到一种最佳的方式，使得背包中放入的物品总价值最大，但不能超过背包的最大容量。
 * </pre>
 *
 * @author zhangjw54
 */
public class Knapsack {

    private final int[] values = {60, 100, 120};
    private final int[] weights = {10, 20, 30};
    private final int capacity = 50;
    private final int n = values.length;
    private final int[][] cache = new int[n + 1][capacity + 1];

    @Test
    public void zeroOneKnapsack() {
        // 递归方式
        for (int[] rows : cache) {
            Arrays.fill(rows, -1);
        }
        int maxValue = recursiveZeroOneKnapsack(n, capacity);
        System.out.println("maxValue = " + maxValue);

        // 迭代方式
        for (int[] rows : cache) {
            Arrays.fill(rows, 0);
        }
        int maxValue2 = iterativeZeroOneKnapsack();
        System.out.println("maxValue2 = " + maxValue2);
    }

    @Test
    public void totalKnapsack() {
        // 递归方式
        for (int[] rows : cache) {
            Arrays.fill(rows, -1);
        }
        int maxValue = recursiveTotalKnapsack(n, capacity);
        System.out.println("maxValue = " + maxValue);

        // 迭代方式
        for (int[] rows : cache) {
            Arrays.fill(rows, 0);
        }
        int maxValue2 = iterativeTotalKnapsack();
        System.out.println("maxValue2 = " + maxValue2);
    }

    private int recursiveZeroOneKnapsack(int n, int currentCapacity) {
        if (n == 0 || currentCapacity == 0) return 0;
        if (cache[n][currentCapacity] != -1) return cache[n][currentCapacity];
        if (weights[n - 1] > currentCapacity) {
            cache[n][currentCapacity] = recursiveZeroOneKnapsack(n - 1, currentCapacity);
        } else {
            cache[n][currentCapacity] = Math.max(recursiveZeroOneKnapsack(n - 1, currentCapacity),
                    recursiveZeroOneKnapsack(n - 1, currentCapacity - weights[n - 1]) + values[n - 1]);
        }
        return cache[n][currentCapacity];
    }

    private int recursiveTotalKnapsack(int n, int currentCapacity) {
        if (n == 0 || currentCapacity == 0) return 0;
        if (cache[n][currentCapacity] != -1) return cache[n][currentCapacity];
        // 一个物品也不拿总价值就是0，从最后一个物品开始循环拿的个数，取最大值
        int maxVal = 0;
        for (int i = 0; i * weights[n - 1] <= currentCapacity; i++) {
            maxVal = Math.max(maxVal,
                    recursiveTotalKnapsack(n - 1, currentCapacity - i * weights[n - 1]) + i * values[n - 1]);
        }
        cache[n][currentCapacity] = maxVal;
        return maxVal;
    }

    private int iterativeZeroOneKnapsack() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] > j) {
                    cache[i][j] = cache[i - 1][j];
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], values[i - 1] + cache[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return cache[n][capacity];
    }

    private int iterativeTotalKnapsack() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                int maxVal = 0;
                for (int k = 0; k * weights[i - 1] <= j; k++) {
                    maxVal = Math.max(maxVal, k * values[i - 1] + cache[i - 1][j - k * weights[i - 1]]);
                }
                cache[i][j] = maxVal;
            }
        }
        return cache[n][capacity];
    }

}
