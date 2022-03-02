package com.example.collection;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @author Javen
 * @date 2022/2/28
 */
public class TestArrayDeque {

    public static void main(String[] args) {
        ArrayDeque<String> strings = new ArrayDeque<>();
        strings.add("123");
        strings.add("456");
        strings.add("789");
        strings.add("000");
        System.out.println(strings.remove("789"));
        System.out.println(strings.getFirst());
        System.out.println(strings.getLast());

        LinkedList<String> strings1 = new LinkedList<>();
        strings1.add("123");
        strings1.add("456");
        strings1.add("789");
        strings1.add("000");
        System.out.println(strings1.remove("789"));
        System.out.println(strings1.getFirst());
        System.out.println(strings1.getLast());
    }
}
