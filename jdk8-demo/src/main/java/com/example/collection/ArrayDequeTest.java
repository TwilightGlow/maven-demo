package com.example.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @author Javen
 * @date 2022/2/28
 */
public class ArrayDequeTest {

    @Test
    public void test() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        // 插入操作
        // addFirst(E e)：在队列的头部插入元素，若插入失败抛出异常。
        // addLast(E e)：在队列的尾部插入元素，若插入失败抛出异常。
        // offerFirst(E e)：在队列的头部插入元素，若插入失败返回 false。
        // offerLast(E e)：在队列的尾部插入元素，若插入失败返回 false。
        arrayDeque.addFirst("1");
        arrayDeque.addLast("9");
        arrayDeque.offerFirst("2");
        arrayDeque.offerLast("8");
        System.out.println(arrayDeque);
        // 删除操作
        // removeFirst()：删除并返回队列的头部元素，若队列为空抛出异常。
        // removeLast()：删除并返回队列的尾部元素，若队列为空抛出异常。
        // pollFirst()：删除并返回队列的头部元素，若队列为空返回 null。
        // pollLast()：删除并返回队列的尾部元素，若队列为空返回 null。
        arrayDeque.removeFirst();
        System.out.println(arrayDeque);
        // 检查操作
        // getFirst()：获取队列的头部元素，不删除，若队列为空抛出异常。
        // getLast()：获取队列的尾部元素，不删除，若队列为空抛出异常。
        // peekFirst()：获取队列的头部元素，不删除，若队列为空返回 null。
        // peekLast()：获取队列的尾部元素，不删除，若队列为空返回 null。
        System.out.println(arrayDeque.peekLast());
        // 栈操作
        // ArrayDeque 还可以用作栈（LIFO），提供了栈的操作方法：
        // push(E e)：将元素推入栈顶，相当于 addFirst(E e)。
        // pop()：移除并返回栈顶元素，相当于 removeFirst() / pollFirst()。
        // peek()：返回栈顶元素但不移除，相当于 peekFirst()。
        arrayDeque.push("3");
        arrayDeque.push("4");
        arrayDeque.poll();
        System.out.println(arrayDeque);

    }

}
