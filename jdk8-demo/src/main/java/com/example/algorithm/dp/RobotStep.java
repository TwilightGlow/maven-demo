package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <pre>
 * 问题描述: 有一个机器人在 m x n 的网格中，它最开始在最左上角。
 *          这个机器人的目标是移动到网格的最右下角。
 *          它每次只能 向右 或者向下移动一格。
 *          请你计算它能从起点走到终点的 路径数量!
 * 输入:
 *  给定两个整数 m 和 n，其中 m 代表网格的行数(rows).
 *  n代表网格的列数(columns).
 * </pre>
 *
 * @author zhangjw54
 */
public class RobotStep {

    private final int row = 3;
    private final int column = 7;
    private final int[][] cache = new int[row + 1][column + 1];

    @Test
    public void recursiveMethod() {
        int recursive = recursive(row, column, 1, 1);
        System.out.println(recursive);
        // System.out.println(Arrays.deepToString(cache));
    }

    private int recursive(int m, int n, int currentRow, int currentColumn) {
        if (currentRow == m) return 1;
        if (currentColumn == n) return 1;

        if (cache[currentRow][currentColumn] != 0) {
            return cache[currentRow][currentColumn];
        }

        int firstChoice = recursive(m, n, currentRow + 1, currentColumn);
        int secondChoice = recursive(m, n, currentRow, currentColumn + 1);
        cache[currentRow][currentColumn] = firstChoice + secondChoice;
        return cache[currentRow][currentColumn];
    }
}
