package com.example.algorithm.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangjw54
 */
public class PostOrderTraversal {

    private final TreeNode root;

    {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
    }

    public void recursiveTraversal() {
        List<String> path = new ArrayList<>();
        recursive(root, path);
        System.out.println(path);
    }

    private void recursive(TreeNode root, List<String> path) {
        if (root == null) return;
        recursive(root.left, path);
        recursive(root.right, path);
        path.add(String.valueOf(root.val));
    }

    /**
     * 使用一个栈和一个辅助变量 lastVisited 来实现迭代的后序遍历。
     * 首先将左子树的所有节点压入栈。
     * 然后根据条件，决定是否访问右子树或者根节点。
     * 当右子树为空或者已经访问过时，访问根节点并将其从栈中弹出。
     */
    /**
     * 初始状态：current = 1，栈为空。
     * 将1和2压入栈，current变为null。
     * 弹出2，由于2没有右子树，直接访问2并更新lastVisited为2。
     * 弹出1，由于1的右子树是3且3没有被访问，继续处理右子树，current变为3。
     * 将3和4压入栈，current变为null。
     * 弹出4，由于4没有右子树，直接访问4并更新lastVisited为4。
     * 弹出3，由于3的右子树是5且5没有被访问，继续处理右子树，current变为5。
     * 将5压入栈，current变为null。
     * 弹出5，由于5没有右子树，直接访问5并更新lastVisited为5。
     * 弹出3，此时3的右子树5已经被访问，可以访问3并更新lastVisited为3。
     * 弹出1，此时1的右子树3已经被访问，可以访问1并更新lastVisited为1。
     * 最终路径为[2, 4, 5, 3, 1]，符合后序遍历的要求。
     */
    @Test
    public void queueTraversal() {
        List<Integer> path = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        TreeNode lastVisited = null;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.peek();
            if (current.right == null || current.right == lastVisited) {
                path.add(current.val);
                stack.pop();
                lastVisited = current;
                current = null;
            } else {
                current = current.right;
            }
        }
        System.out.println(path);
    }
}
