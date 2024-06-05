package com.example.test;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangjw54
 */
public class DataSetDifference {

    private final int size1 = 100;
    private final int size2 = 100;

    private final Row[] rows = new Row[size1];
    private final Map<String, Row> map = new HashMap<>();

    static class Row {
        private String line;
        private int count1;
        private int count2;
        private int difference;
    }

    // a b c d
    private final String[] str1 = new String[size1];
    // a c d f
    private final String[] str2 = new String[size2];

    @Test
    public void test4() {
        for (String s : str1) {
            Row row = new Row();
            // fuzhi
            map.put(s, new Row());
        }

        for (String s : str2) {
            if (map.containsKey(s)) {
                Row row = map.get(s);
                // 更新

            } else {
                map.put(s, new Row());
            }
        }
    }






    private final Set<String> set1 = new HashSet<>();
    private final Set<String> set2 = new HashSet<>();

    @Test
    public void test2() {
        Set<String> difference1 = new HashSet<>();
        Set<String> difference2 = new HashSet<>();
        Set<String> same = new HashSet<>();

        for (String s : set1) {
            if (!set2.contains(s)) {
                difference1.add(s);
            }
        }

        for (String s : set2) {
            if (!set1.contains(s)) {
                difference2.add(s);
            }
        }

        for (String s : set1) {
            if (set2.contains(s)) {
                same.add(s);
            }
        }
    }


    @Data
    static class Difference {
        private final Set<String> same = new HashSet<>();
        private final Set<String> difference = new HashSet<>();
    }

    @Test
    public void test() {
        for (int i = 0; i < str1.length; i++) {
            str1[i] = "str1" + i;
        }
        for (int i = 0; i < str1.length; i++) {
            str2[i] = "str2" + i;
        }
        Difference difference = new Difference();
        for (String s : str1) {
            difference.getSame().add(s);
        }
        Difference difference1 = new Difference();
        for (String s : str2) {
            difference1.getSame().add(s);
        }
        for (String s : difference1.getSame()) {
            if (!difference.getSame().contains(s)) {
                difference.getDifference().add(s);
            }
        }
    }

    @Test
    public void test1() {

        Set<String> set1 = Arrays.stream(str1).collect(Collectors.toSet());

        Set<String> set2 = Arrays.stream(str2).collect(Collectors.toSet());

        for (String s : set1) {
            if (set2.contains(s)) {

            }
        }

        Difference difference = new Difference();
        for (String s : str1) {
            for (String s2 : str2) {
                if (s.equals(s2)) {
                    difference.getSame().add(s);
                } else {
                    difference.getDifference().add(s);
                }
            }
        }

    }

}
