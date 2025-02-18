package com.example.algorithm.node;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjw54
 */
public class ReverseSinglyNode {

    // 虽然使用 Map 可以实现链表反转，但这种方法的空间复杂度是 O(n)，因为需要额外存储 n 个节点的前驱节点。
    // 而标准的迭代或递归方法只需要 O(1) 额外空间。
    @Test
    public void map() {
        SinglyNode singlyNode = SinglyNode.initSinglyNode();
        SinglyNode.print(singlyNode);

        Map<SinglyNode, SinglyNode> map = new HashMap<>();
        SinglyNode prev = null;
        SinglyNode current = singlyNode;
        while (current != null) {
            map.put(current, prev);
            prev = current;
            current = current.next;
        }

        SinglyNode newHead = prev;
        current = newHead;
        while (current != null) {
            SinglyNode nextNode = map.get(current);
            current.next = nextNode;
            current = nextNode;
        }

        SinglyNode.print(newHead);
    }

    @Test
    public void iterative() {
        SinglyNode singlyNode = SinglyNode.initSinglyNode();
        SinglyNode.print(singlyNode);

        SinglyNode prev = null;
        SinglyNode current = singlyNode;
        while (current != null) {
            SinglyNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        SinglyNode.print(prev);
    }

    @Test
    public void recursive() {
        SinglyNode singlyNode = SinglyNode.initSinglyNode();
        SinglyNode.print(singlyNode);

        SinglyNode newHead = recursive(singlyNode);

        SinglyNode.print(newHead);
    }

    private SinglyNode recursive(SinglyNode singlyNode) {
        if (singlyNode == null || singlyNode.next == null) {
            return singlyNode;
        }
        SinglyNode newHead = recursive(singlyNode.next);
        singlyNode.next.next = singlyNode; // 反转当前节点的下一个节点的 next 指针，使其指向当前节点。
        singlyNode.next = null; // 将当前节点的 next 指针置为 null，避免形成环。
        return newHead;
    }

}
