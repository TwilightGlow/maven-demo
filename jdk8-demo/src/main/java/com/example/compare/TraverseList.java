package com.example.compare;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author zhangjw54
 */
@Slf4j
public class TraverseList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        // iteratorLoop(list);
        listIteratorLoop(list);
        // forLoop(list);
        // forEnhanceLoop(list);
        log.info("list: {}", list);
    }

    // 迭代器可以在遍历的同时对元素进行删除操作，但是不能对list的元素进行增加操作，可以调用list.set()方法，不能调用list.add() 和 list.remove()方法，增强for循环同理
    private static void iteratorLoop(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
            if ("c".equals(s)) {
                // list.add(4, "f");
                // list.add("f");
                list.set(2, "r");
                // iterator.remove();
            }
        }
    }

    private static void listIteratorLoop(List<String> list) {
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String s = listIterator.next();
            System.out.println(s);
            if ("c".equals(s)) {
                listIterator.add("f");
                listIterator.remove();
            }
        }
    }

    // for循环可以在遍历的同时对集合进行增删操作，但是要注意遍历的次数
    private static void forLoop(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
            if ("c".equals(s)) {
                list.add(4, "f");
                list.remove(s);
            }
        }
    }

    // 增强for循环不能在遍历的同时对集合进行增删操作，否则会抛出ConcurrentModificationException
    private static void forEnhanceLoop(List<String> list) {
        for (String s : list) {
            System.out.println(s);
            if ("c".equals(s)) {
                list.set(2, "r");
                // list.add(4, "f");
                // list.remove(s);
            }
        }
    }
}
