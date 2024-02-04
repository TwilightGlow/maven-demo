package com.example.function;

import org.junit.Test;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Spliterator是一种特殊的迭代器，可用于遍历集合中的元素，但是设计理念是为了并行执行任务而涉及的
 * 一般用于将一个stream()中的元素分成几分而涉及
 * .spliterator()方法返回一个Spliterator，该Spliterator可以用来遍历集合中的元素
 * .tryAdvance()方法对剩余元素执行动作，返回true，否则返回false
 * .trySplit()方法将剩余元素分成两部分，返回一个Spliterator
 *
 * @author zhangjw54
 */
public class TestSpliterator {

    @Test
    public void spliterator() {
//        Stream<Integer> stream = Stream.iterate(0, i -> i + 1).limit(10);
//        Stream<Integer> stream = Stream.generate(() -> 0).limit(10);
//         注意Stream.of()返回的ArraySpliterator使用trySplit()方法才会切换，Stream.iterate()不可以
        Stream<Integer> stream = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 获取可拆分器，0 - 9
        Spliterator<Integer> spliterator = stream.spliterator();
        // spliterator: 5-9, spliterator1: 0-4
        Spliterator<Integer> spliterator1 = spliterator.trySplit(); // trySplit相当于无符号右移一位即除以2
        // spliterator2: 5-6, spliterator: 7-9
        Spliterator<Integer> spliterator2 = spliterator.trySplit();
        // spliterator3: 0-1, spliterator1: 2-4
        Spliterator<Integer> spliterator3 = spliterator1.trySplit();
        spliterator.forEachRemaining(System.out::println);
        System.out.println("------------------------------------");
        spliterator1.forEachRemaining(System.out::println);
        System.out.println("------------------------------------");
        spliterator2.forEachRemaining(System.out::println);
        System.out.println("------------------------------------");
        spliterator3.forEachRemaining(System.out::println);
        System.out.println("------------------------------------");
    }

    @Test
    public void testSpliterator() {
        List<String> list = Stream.generate(() -> "Hello").limit(5).collect(Collectors.toList());
        System.out.println(list);
        Spliterator<String> first = list.spliterator();
        Spliterator<String> second = list.stream().spliterator().trySplit();
        System.out.println(first.estimateSize());
        System.out.println(second.estimateSize());
        System.out.println(second.getExactSizeIfKnown());
        System.out.println(second.characteristics());
        System.out.println(StreamSupport.stream(second, false).collect(Collectors.toList()));
        System.out.println(call(second));
        System.out.println(StreamSupport.stream(second, false).collect(Collectors.toList()));
    }

    private String call(Spliterator<String> spliterator) {
        int current = 0;
        while (spliterator.tryAdvance(System.out::println)) {
            current++;
        }
        return Thread.currentThread().getName() + ":" + current;
    }
}
