package com.example.algorithm.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 *
 * @author zhangjw54
 */
public class InOrderTraversal {

    private final TreeNode root;

    {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
    }

    @Test
    public void recursiveTraversal() {
        List<String> path = new ArrayList<>();
        recursive(root, path);
        System.out.println(path);
    }

    private void recursive(TreeNode root, List<String> path) {
        if (root == null) return;
        recursive(root.left, path);
        path.add(String.valueOf(root.val));
        recursive(root.right, path);
    }

    @Test
    public void queueTraversal() {
        List<Integer> path = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Reach the left most Node of the current Node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current must be null at this point
            current = stack.pop();
            path.add(current.val);

            // We have visited the node and its left subtree. Now, it's right subtree's turn
            current = current.right;
        }
        System.out.println(path);
    }

    @Test
    public void morrisTraversal() {
        List<String> path = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                path.add(String.valueOf(node.val));
                node = node.right;
            } else {
                TreeNode pre = node.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = node;
                TreeNode temp = node;
                node = node.left;
                temp.left = null;
            }
        }
        System.out.println(path);
    }
}
