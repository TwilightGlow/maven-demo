package com.example.spEL;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

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

        // 调用注册的方法，并通过this将context中的rootObject传进去
        Expression expression = PARSER.parseExpression("#trimAllWhitespace(#this)");
        log.info(expression.getValue(standardEvaluationContext).toString());
    }
}
