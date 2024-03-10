package com.example.interpreter.shop;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangjw54
 */
public class Client {

    public static void main(String[] args) {
        List<String> cartItems = Arrays.asList("item3", "item2");
        // 构建上下文
        Context context = new Context(cartItems);
        // 构建规则
        Expression rule = new AndExpression(
                new ProductExpression("item1"),
                new OrExpression(
                        new ProductExpression("item2"),
                        new ProductExpression("item3")
                ));
        // 解释运行
        boolean result = rule.interpret(context);
        System.out.println(result);
    }
}
