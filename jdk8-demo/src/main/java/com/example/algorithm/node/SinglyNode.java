package com.example.algorithm.node;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjw54
 */
@ToString
@EqualsAndHashCode(exclude = {"next"})
public class SinglyNode {
    int val;
    SinglyNode next;

    public SinglyNode(int val) {
        this.val = val;
    }

    public static SinglyNode initSinglyNode() {
        SinglyNode node = new SinglyNode(1);
        node.next = new SinglyNode(2);
        node.next.next = new SinglyNode(3);
        node.next.next.next = new SinglyNode(4);
        node.next.next.next.next = new SinglyNode(5);
        return node;
    }

    public static void print(SinglyNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.val).append(" ");
            node = node.next;
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }

}
