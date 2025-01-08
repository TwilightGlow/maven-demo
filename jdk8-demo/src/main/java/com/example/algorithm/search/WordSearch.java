package com.example.algorithm.search;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/word-search/?envType=problem-list-v2&envId=array
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * @author zhangjw54
 */
public class WordSearch {

    private char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
    };

    private String word = "ABCCED";

    @Test
    public void wordSearch() {
        System.out.println(dfsExist(board, word));
        System.out.println(bfsExist(board, word));
    }

    private boolean dfsExist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfsExist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0) &&
                        bfs(board, word, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int x, int y) {
        if (index == word.length()) {
            return true;
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(index)) {
            return false;
        }

        // visited[x][y] = true;
        char temp = board[x][y];
        board[x][y] = '#'; // 标记为访问过的

        boolean found = dfs(board, word, index + 1, x - 1, y)
                || dfs(board, word, index + 1, x + 1, y)
                || dfs(board, word, index + 1, x, y - 1)
                || dfs(board, word, index + 1, x, y + 1);

        // visited[x][y] = false;
        board[x][y] = temp; // 恢复原始状态

        return found;
    }

    private boolean bfs(char[][] board, String word, int startX, int startY) {
        int m = board.length;
        int n = board[0].length;
        Queue<State> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.add(new State(startX, startY, 0));
        visited[startX][startY] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.index == word.length() - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                int newIndex = current.index + 1;

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && board[newX][newY] == word.charAt(newIndex)) {
                    queue.add(new State(newX, newY, newIndex));
                    visited[newX][newY] = true;
                }
            }
        }

        return false;
    }

    @AllArgsConstructor
    private static class State {
        int x, y, index;
    }
}
