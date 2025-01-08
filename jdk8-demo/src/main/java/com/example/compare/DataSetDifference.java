package com.example.compare;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangjw54
 */
public class DataSetDifference {


    private final String[] array1 = new String[] {"a", "a", "b", "c", "c", "d"};
    private final String[] array2 = new String[] {"a", "b", "b", "c", "c", "f"};

    @Test
    public void test() {
        System.out.println("去重对称差集1：" + Arrays.toString(distinctDisjunction1(array1, array2)));
        System.out.println("去重对称差集2：" + Arrays.toString(distinctDisjunction2(array1, array2)));
        System.out.println("对称差集1：" + Arrays.toString(disjunction1(array1, array2)));
        System.out.println("对称差集2：" + Arrays.toString(disjunction2(array1, array2)));
        System.out.println("对称差集3：" + Arrays.toString(disjunction3(array1, array2)));

        System.out.println("去重交集1：" + Arrays.toString(distinctIntersection1(array1, array2)));
        System.out.println("去重交集2：" + Arrays.toString(distinctIntersection2(array1, array2)));
        System.out.println("去重交集3：" + Arrays.toString(distinctIntersection3(array1, array2)));
        System.out.println("交集1：" + Arrays.toString(intersection1(array1, array2)));
        System.out.println("交集2：" + Arrays.toString(intersection2(array1, array2)));
        System.out.println("交集3：" + Arrays.toString(intersection3(array1, array2)));

        System.out.println("去重并集1：" + Arrays.toString(distinctUnion1(array1, array2)));
        System.out.println("去重并集2：" + Arrays.toString(distinctUnion2(array1, array2)));
        System.out.println("并集1：" + Arrays.toString(union1(array1, array2)));
        System.out.println("并集2：" + Arrays.toString(union2(array1, array2)));
        System.out.println("并集3：" + Arrays.toString(union3(array1, array2)));
    }


    //***** 两个数组的去重后的并集 *****//
    private String[] distinctUnion1(String[] array1, String[] array2) {
        return Stream.concat(Arrays.stream(array1).distinct(), Arrays.stream(array2).distinct()).distinct().toArray(String[]::new);
    }

    private String[] distinctUnion2(String[] array1, String[] array2) {
        return CollectionUtil.unionDistinct(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList())).toArray(new String[0]);
    }


    //***** 两个数组的并集 *****//
    private String[] union1(String[] array1, String[] array2) {
        Map<String, Long> countMap1 = Arrays.stream(array1).collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));
        Map<String, Long> countMap2 = Arrays.stream(array2).collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));
        Set<String> union = Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).collect(Collectors.toSet());
        List<String> result = new ArrayList<>();
        for (String s : union) {
            long count1 = countMap1.getOrDefault(s, 0L);
            long count2 = countMap2.getOrDefault(s, 0L);
            int abs = (int) Math.max(count1, count2);
            for (int i = 0; i < abs; i++) {
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }

    private String[] union2(String[] array1, String[] array2) {
        return CollectionUtil.union(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList())).toArray(new String[0]);
    }

    private String[] union3(String[] array1, String[] array2) {
        return CollectionUtils.union(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList())).toArray(new String[0]);
    }


    //***** 两个数组的去重后的交集 *****//
    private String[] distinctIntersection1(String[] array1, String[] array2) {
        if (array1.length == 0 || array2.length == 0) return new String[0];
        Set<String> set1 = Arrays.stream(array1).collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(array2).collect(Collectors.toSet());
        return set1.stream().filter(set2::contains).toArray(String[]::new);
    }

    // 时间复杂度 O(n1 + n2)
    private String[] distinctIntersection2(String[] array1, String[] array2) {
        Set<String> set1 = Arrays.stream(array1).collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(array2).collect(Collectors.toSet());
        set1.retainAll(set2);
        return set1.toArray(new String[0]);
    }

    private String[] distinctIntersection3(String[] array1, String[] array2) {
        return CollectionUtil.intersectionDistinct(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList())).toArray(new String[0]);
    }


    //***** 两个数组的的交集 *****//
    private String[] intersection1(String[] array1, String[] array2) {
        if (array1.length == 0 || array2.length == 0) return new String[0];
        Map<String, Long> countMap1 = Arrays.stream(array1).collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));
        Map<String, Long> countMap2 = Arrays.stream(array2).collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));
        Set<String> set1 = Arrays.stream(array1).collect(Collectors.toSet());
        List<String> result = new ArrayList<>();
        for (String s : set1) {
            long count1 = countMap1.getOrDefault(s, 0L);
            long count2 = countMap2.getOrDefault(s, 0L);
            int abs = (int) Math.min(count1, count2);
            for (int i = 0; i < abs; i++) {
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }

    private String[] intersection2(String[] array1, String[] array2) {
        return CollectionUtil.intersection(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList())).toArray(new String[0]);
    }

    private String[] intersection3(String[] array1, String[] array2) {
        return CollectionUtils.intersection(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList())).toArray(new String[0]);
    }


    //***** 两个数组的去重后的对称差集 *****//
    private String[] distinctDisjunction1(String[] array1, String[] array2) {
        Set<String> set1 = Arrays.stream(array1).collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(array2).collect(Collectors.toSet());

        Stream<String> uniqueS1 = set1.stream().filter(x -> !set2.contains(x));
        Stream<String> uniqueS2 = set2.stream().filter(x -> !set1.contains(x));

        return Stream.concat(uniqueS1, uniqueS2).toArray(String[]::new);
    }

    private String[] distinctDisjunction2(String[] array1, String[] array2) {
        Set<String> set1 = Arrays.stream(array1).collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(array2).collect(Collectors.toSet());

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        // retainAll()方法是求交集，时间复杂度是O(min(n1, n2))
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        // removeAll()方法的时间复杂度也是O(min(n1, n2))
        union.removeAll(intersection);

        return union.toArray(new String[0]);
    }


    //***** 两个数组的对称差集 *****//
    private String[] disjunction1(String[] array1, String[] array2) {
        Collection<String> disjunction = CollectionUtil.disjunction(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList()));
        return disjunction.toArray(new String[0]);
    }

    private String[] disjunction2(String[] array1, String[] array2) {
        Collection<String> disjunction = CollectionUtils.disjunction(Arrays.stream(array1).collect(Collectors.toList()), Arrays.stream(array2).collect(Collectors.toList()));
        return disjunction.toArray(new String[0]);
    }

    // 时间复杂度 O(n1 + n2)
    private String[] disjunction3(String[] array1, String[] array2) {
        if (array1.length == 0) return array2;
        if (array2.length == 0) return array1;
        Map<String, Long> countMap1 = Arrays.stream(array1).collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));
        Map<String, Long> countMap2 = Arrays.stream(array2).collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));

        Set<String> unionSet = Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).collect(Collectors.toSet());

        List<String> result = new ArrayList<>();
        for (String s : unionSet) {
            long count1 = countMap1.getOrDefault(s, 0L);
            long count2 = countMap2.getOrDefault(s, 0L);
            int abs = (int) Math.abs(count1 - count2);
            for (int i = 0; i < abs; i++) {
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }



    // private final int size1 = 100;
    // private final int size2 = 100;
    //
    // private final Row[] rows = new Row[size1];
    // private final Map<String, Row> map = new HashMap<>();
    //
    // static class Row {
    //     private String line;
    //     private int count1;
    //     private int count2;
    //     private int difference;
    // }
    //
    // // a b c d
    // private final String[] str1 = new String[size1];
    // // a c d f
    // private final String[] str2 = new String[size2];
    //
    // @Test
    // public void test4() {
    //     for (String s : str1) {
    //         Row row = new Row();
    //         // fuzhi
    //         map.put(s, new Row());
    //     }
    //
    //     for (String s : str2) {
    //         if (map.containsKey(s)) {
    //             Row row = map.get(s);
    //             // 更新
    //
    //         } else {
    //             map.put(s, new Row());
    //         }
    //     }
    // }
    //
    //
    //
    //
    //
    //
    // private final Set<String> set1 = new HashSet<>();
    // private final Set<String> set2 = new HashSet<>();
    //
    // @Test
    // public void test2() {
    //     Set<String> difference1 = new HashSet<>();
    //     Set<String> difference2 = new HashSet<>();
    //     Set<String> same = new HashSet<>();
    //
    //
    //
    //     for (String s : set1) {
    //         if (!set2.contains(s)) {
    //             difference1.add(s);
    //         }
    //     }
    //
    //     for (String s : set2) {
    //         if (!set1.contains(s)) {
    //             difference2.add(s);
    //         }
    //     }
    //
    //     for (String s : set1) {
    //         if (set2.contains(s)) {
    //             same.add(s);
    //         }
    //     }
    // }
    //
    //
    // @Data
    // static class Difference {
    //     private final Set<String> same = new HashSet<>();
    //     private final Set<String> difference = new HashSet<>();
    // }
    //
    // @Test
    // public void test() {
    //     for (int i = 0; i < str1.length; i++) {
    //         str1[i] = "str1" + i;
    //     }
    //     for (int i = 0; i < str1.length; i++) {
    //         str2[i] = "str2" + i;
    //     }
    //     Difference difference = new Difference();
    //     for (String s : str1) {
    //         difference.getSame().add(s);
    //     }
    //     Difference difference1 = new Difference();
    //     for (String s : str2) {
    //         difference1.getSame().add(s);
    //     }
    //     for (String s : difference1.getSame()) {
    //         if (!difference.getSame().contains(s)) {
    //             difference.getDifference().add(s);
    //         }
    //     }
    // }
    //
    // @Test
    // public void test1() {
    //
    //     Set<String> set1 = Arrays.stream(str1).collect(Collectors.toSet());
    //
    //     Set<String> set2 = Arrays.stream(str2).collect(Collectors.toSet());
    //
    //     for (String s : set1) {
    //         if (set2.contains(s)) {
    //
    //         }
    //     }
    //
    //     Difference difference = new Difference();
    //     for (String s : str1) {
    //         for (String s2 : str2) {
    //             if (s.equals(s2)) {
    //                 difference.getSame().add(s);
    //             } else {
    //                 difference.getDifference().add(s);
    //             }
    //         }
    //     }
    //
    // }

}
