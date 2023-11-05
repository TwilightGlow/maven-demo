package com.example.thread.linkedList;

/**
 * @author zhangjw54
 */
public class SinglyLinkedList {

    private ListNode head;

    public SinglyLinkedList() {
        this.head = null;
    }

    // 添加节点到链表末尾
    public void addNode(int val) {
        ListNode newNode = new ListNode(val);
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // 在链表头部插入节点
    public void insertAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
    }

    // 在指定位置插入节点
    public void insertAtIndex(int val, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }

        ListNode newNode = new ListNode(val);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode current = head;
            int i = 0;

            while (current != null && i < index - 1) {
                current = current.next;
                i++;
            }

            if (current == null) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }

            newNode.next = current.next;
            current.next = newNode;
        }
    }

    // 删除指定值的节点
    public void deleteNode(int val) {
        if (head == null) {
            return;
        }

        if (head.val == val) {
            head = head.next;
            return;
        }

        ListNode current = head;
        while (current.next != null && current.next.val != val) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // 查找节点
    public boolean contains(int val) {
        ListNode current = head;
        while (current != null) {
            if (current.val == val) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 打印链表元素
    public void printList() {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    // 静态嵌套类表示链表节点
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);

        linkedList.deleteNode(2);
        linkedList.printList();

        linkedList.insertAtHead(0);
        linkedList.printList();

        linkedList.insertAtIndex(4, 4);
        linkedList.printList();

        linkedList.printList();
    }

}
