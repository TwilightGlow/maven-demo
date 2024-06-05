package com.example.function;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * @author zhangjw54
 */
public class BinaryOperatorExample {

    // BinaryOperator表示接受两个相同类型的输入参数 T 并返回一个结果 T 的操作。
    public static void main(String[] args) {
        // 示例1：对两个整数进行相加
        // BiFunction<Integer, Integer, Integer> add = Integer::sum;
        BinaryOperator<Integer> add = Integer::sum;
        int sum = add.apply(5, 3);
        System.out.println(sum); // 输出: 8

        // 示例2：比较两个字符串的长度并返回较长的那个
        BinaryOperator<String> longerString = (str1, str2) -> str1.length() >= str2.length() ? str1 : str2;
        String result = longerString.apply("Hello", "World");
        System.out.println(result); // 输出: Hello

        // 示例3：组合多个函数
        BinaryOperator<Integer> multiplyAndAdd = (num1, num2) -> (num1 * 2) + (num2 * 3);
        sum = multiplyAndAdd.apply(2, 3);
        System.out.println(sum); // 输出: 13

        // 示例4：输出较长字符串
        BinaryOperator<String> maxLength = BinaryOperator.maxBy(Comparator.comparingInt(String::length));
        String longer = maxLength.apply("apple", "banana");
        System.out.println("Longer string: " + longer); // 输出: Longer string: banana

        // 示例5：输出较小数字
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compare);
        int smaller = min.apply(3, 5);
        System.out.println("Min value: " + smaller); // 输出: Min value: 3
    }
}
