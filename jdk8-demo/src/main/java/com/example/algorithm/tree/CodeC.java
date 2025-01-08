package com.example.algorithm.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * 1. 先序遍历的特点
 * 先序遍历的顺序是：
 *
 * 访问根节点
 * 遍历左子树
 * 遍历右子树
 * 这种遍历方式具有以下优势：
 *
 * 根节点首先被访问：在序列化过程中，根节点的信息最先被记录，这意味着在反序列化时，可以直接从根节点开始构建树结构，从而方便树的重建。
 * 空节点的位置被明确记录：通过在遍历时记录空节点（例如用特殊字符 # 表示），先序遍历能够精确地重建树结构，包括所有的空节点。
 * 2. 中序遍历和后序遍历的局限性
 * 中序遍历
 * 中序遍历的顺序是：
 *
 * 遍历左子树
 * 访问根节点
 * 遍历右子树
 * 中序遍历的问题在于：
 *
 * 无法唯一确定树结构：中序遍历仅提供节点值的排列顺序，无法明确区分树的结构。例如，对于同一组节点值，不同的树结构可能产生相同的中序遍历结果。
 * 空节点信息难以保留：即使记录空节点，中序遍历也无法直接反映出树的层次结构，导致难以重建原始树。
 * 后序遍历
 * 后序遍历的顺序是：
 *
 * 遍历左子树
 * 遍历右子树
 * 访问根节点
 * 后序遍历的问题在于：
 *
 * 根节点在最后访问：在序列化过程中，根节点的信息最后被记录，这意味着反序列化时需要从最后一个节点开始，这增加了复杂性。
 * 树结构重建复杂：由于根节点在最后，重建树时需要先重建左右子树，这在没有明确的层次结构信息时增加了难度。
 * </pre>
 *
 * @author zhangjw54
 */
public class CodeC {

    @Test
    public void test() throws JsonProcessingException {
        // 构建二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // 序列化二叉树
        String data = serialize(root);
        System.out.println(data);

        // 反序列化二叉树
        TreeNode node = deserialize(data);
        System.out.println(node);

        System.out.println("-----------------");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        System.out.println(jsonStr);

        TreeNode treeNode = objectMapper.readValue(jsonStr, TreeNode.class);
        System.out.println(treeNode);
    }

    // 序列化二叉树
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // 反序列化二叉树
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(nodes);
    }

    private TreeNode deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        TreeNode node;
        if ("#".equals(val)) {
            return null;
        } else {
            node = new TreeNode(Integer.parseInt(val));
        }
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        return node;
    }

}
