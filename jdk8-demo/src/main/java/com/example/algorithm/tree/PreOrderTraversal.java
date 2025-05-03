package com.example.algorithm.tree;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author zhangjw54
 */
public class PreOrderTraversal {

    private final TreeNode root;

    {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
    }

    @Test
    public void recursiveTraversal() {
        List<String> path = new ArrayList<>();
        recursive(root, path);
        System.out.println(path);
    }

    private void recursive(TreeNode root, List<String> path) {
        if (root == null) return;
        path.add(String.valueOf(root.val));
        recursive(root.left, path);
        recursive(root.right, path);
    }

    @Test
    public void queueTraversal() {
        List<Integer> path = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            path.add(node.val);

            // 先压右子树
            if (node.right != null) {
                stack.push(node.right);
            }

            // 再压左子树
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println(path);
    }

    @Test
    public void morrisTraversal() {
        List<Integer> path = new ArrayList<>();
        TreeNode node = root;

        while (node != null) {
            if (node.left == null) {
                path.add(node.val);  // 访问根节点
                node = node.right;   // 遍历右子树
            } else {
                TreeNode pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    path.add(node.val);  // 访问根节点
                    pre.right = node;    // 建立临时连接
                    node = node.left;    // 遍历左子树
                } else {
                    pre.right = null;    // 移除临时连接
                    node = node.right;   // 遍历右子树
                }
            }
        }
        System.out.println(path);
    }
}
