package com.example.spEL;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangjw54
 */
@Slf4j
public class RegisterFunctionTest {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    @Test
    void registerFunction() throws NoSuchMethodException {
        // 原始数据
        String hello = "hello world";
        // 标准评估上下文，并将rootObject传入
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(hello);
        // 注册方法
        standardEvaluationContext.registerFunction("trimAllWhitespace",
                StringUtils.class.getDeclaredMethod("trimAllWhitespace", String.class));

        // 调用注册的方法，并通过#this/#root将context中的rootObject传进去
        // #this始终是定义的，并引用当前的评估对象。#root变量总是被定义并引用根上下文对象。尽管#this可能会随着表达式的组件的计算而变化，但是#root始终引用根。以下示例说明如何使用#this和#root变量：http://www.flydean.com/spring5-spel/
        // Expression expression = PARSER.parseExpression("#trimAllWhitespace(#this)");
        Expression expression = PARSER.parseExpression("#trimAllWhitespace(#root)");
        log.info(expression.getValue(standardEvaluationContext).toString());
    }

    @Test
    void thisAndRoot() {
        // create an array of integers
        List<Integer> primes = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        // create parser and set variable 'primes' as the array of integers
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("primes", primes);

        // all prime numbers > 10 from the list (using selection ?{...})
        // evaluates to [11, 13, 17]
        List<Integer> primesGreaterThanTenThis = (List<Integer>) PARSER.parseExpression(
                "#primes.?[#this>10]").getValue(context);

        // evaluate to []
        List<Integer> primesGreaterThanTenRoot = (List<Integer>) PARSER.parseExpression(
                "#primes.?[#root>10]").getValue(context);

        log.info("primesGreaterThanTenThis -> {}", primesGreaterThanTenThis);
        log.info("primesGreaterThanTenRoot -> {}", primesGreaterThanTenRoot);
    }
}
