package com.example.interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zhangjw54
 */
public class Context {

    private final Map<Expression, Integer> map = new HashMap<>();

    // 定义变量
    public void add(Expression s, Integer value) {
        map.put(s, value);
    }

    // 将变量转换成数字
    public Integer lookup(Expression s) {
        return map.get(s);
    }

    // 构建语法树的主要方法
    // public Expression build(String str) {
    //     // 主要利用栈来实现
    //     Stack<Expression> objects = new Stack<>();
    //     for (int i = 0; i < str.length(); i++) {
    //         char c = str.charAt(i);
    //         // 遇到运算符号+号时候
    //         if (c == '+') {
    //             // 先出栈
    //             Expression pop = objects.pop();
    //             // 将运算结果入栈
    //             objects.push(new PlusOperation(pop, new TerminalExpression(String.valueOf(str.charAt(++i)))));
    //         } else if (c == '-') {
    //             // 遇到减号类似加号
    //             Expression pop = objects.pop();
    //             objects.push(new MinusOperation(pop, new TerminalExpression(String.valueOf(str.charAt(++i)))));
    //         } else {
    //             // 遇到非终结符直接入栈（基本就是第一个数字的情况）
    //             objects.push(new TerminalExpression(String.valueOf(str.charAt(i))));
    //         }
    //     }
    //     // 把最后的栈顶元素返回
    //     return objects.pop();
    // }

    public Expression build(String str) {
        // 主要利用栈来实现
        Stack<Expression> objects = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 遇到运算符号+号时候
            if (c == '+') {
                // 先出栈
                Expression pop = objects.pop();
                // 将运算结果入栈
                String maxStr = getMaxDigitsSubstring(str, i + 1);
                objects.push(new PlusOperation(pop, new TerminalExpression(maxStr)));
                i = i + maxStr.length();
            } else if (c == '-') {
                // 遇到减号类似加号
                Expression pop = objects.pop();
                String maxStr = getMaxDigitsSubstring(str, i + 1);
                objects.push(new MinusOperation(pop, new TerminalExpression(maxStr)));
                i = i + maxStr.length();
            } else {
                // 遇到非终结符直接入栈（基本就是第一个数字的情况）
                String maxStr = getMaxDigitsSubstring(str, i);
                i = i + maxStr.length() - 1;
                objects.push(new TerminalExpression(maxStr));
            }
        }
        // 把最后的栈顶元素返回
        return objects.pop();
    }

    public static String getMaxDigitsSubstring(String input, int position) {
        StringBuilder sb = new StringBuilder();
        for (int i = position; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                sb.append(currentChar);
            } else {
                // sb.setLength(0);
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMaxDigitsSubstring("abc12345678aa111", 6));
    }
}
