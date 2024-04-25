package com.example.function;

import java.util.function.Consumer;

import static com.example.function.FunctionUtil.*;

/**
 * @author zhangjw54
 */
public class Test {

    public static void main(String[] args) {

        isTrue(true).throwMsg("自定义错误信息");

        isTrueOrFalse(false).handle(
                () -> System.out.println("成功"),
                () -> System.out.println("失败")
        );

        isPresent("123").presentOrHandle(
                System.out::println,
                () -> System.out.println("空")
        );
    }
}
