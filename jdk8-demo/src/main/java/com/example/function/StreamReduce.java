package com.example.function;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author zhangjw54
 */
public class StreamReduce {

    @Test
    // https://stackoverflow.com/questions/24308146/why-is-a-combiner-needed-for-reduce-method-that-converts-type-in-java-8
    public void reduce() {

        // 传入的是BinaryOperator<T> accumulator(累加器), 当Stream为空时会返回Optional
        int one = Stream.of(1, 2)
                .reduce((accumulatedInt, element) -> accumulatedInt + element).orElse(100);

        // 第一个参数是初始值，因此不会返回Optional
        int two = Stream.of(1, 2)
                .reduce(0,
                        (accumulatedInt, element) -> accumulatedInt + element);

        // 第一个参数是identity<U>(初始值)，第二个参数是BinaryOperator<U> accumulator(分治方法)，第三个参数是BinaryOperator<U> combiner(规约方法)，返回值是U
        // 所以初始值，分治的返回值，规约的返回值三者泛型应该相同，返回的是聚合结果
        // 一个和两个参数的版本reduce是将Stream<T>减少到T，三个参数的版本是将Stream<T>减少到U
        int three = Stream.of("str1", "str2")
                .reduce(0,
                        (accumulatedInt, str) -> accumulatedInt + str.length(),
                        Integer::sum);

        // 这种方式编译无法通过，因为如果一个参数或者两个参数，stream<T>和BinaryOperator<T> accumulator的泛型相同
        // 而三个参数的使用的是BiFunction<U, ? super T, U> accumulator可以提供更多多元化操作
//        Stream.of("str1", "str2")
//                .reduce(0,
//                        (accumulatedInt, element) -> accumulatedInt + element);

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
    }

    @Test
    public void parallelReduce() {
        Integer one = Stream.of(1, 2, 3, 4, 5)
                .parallel()
                .reduce(Integer::sum).orElse(100);

        // 由于是惰性执行，结果为: (1+10) + (2+10) + (3+10) + (4+10) + (5+10)
        Integer two = Stream.of(1, 2, 3, 4, 5)
                .parallel()
                .reduce(10,
                        Integer::sum);

        // 同样由于惰性的特点，结果为16 + 40 = 56
        // 并行流的reduce()方法尽量不要用在不支持并行的容器上
        int three = Stream.of("str1", "str2", "str3", "str4")
                .parallel()
                .reduce(10,
                        (accumulatedInt, str) -> accumulatedInt + str.length(),
                        Integer::sum);

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
    }
}
