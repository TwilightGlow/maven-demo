package com.example.algorithm.tree;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class MaxHeight {

    @Test
    public void dfs() {
        TreeNode root = TreeNode.of();
        int maxHeight = maxHeightDfs(root);
        System.out.println(maxHeight);
    }

    private int maxHeightDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxHeightDfs(root.left);
        int rightHeight = maxHeightDfs(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Test
    public void bfs() {
        TreeNode root = TreeNode.of();
        int maxHeight = maxHeightBfs(root);
        System.out.println(maxHeight);
    }

    private int maxHeightBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
                size--;
            }
            height++;
        }
        return height;
    }
}
