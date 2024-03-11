package com.example.spEL;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Objects;

/**
 * @author zhangjw54
 */
@Slf4j
public class ParseContextTest {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    // 用于自定义模板，在一个SpEL表达式中可以指定模板前缀和模板后缀，用于解析模板中的内容
    @Test
    void parseContext() {
        ParserContext parserContext = new TemplateParserContext("#{", "}");
        // Expression expression = PARSER.parseExpression("random number is #{T(java.lang.Math).random()}", parserContext);
        Expression expression = PARSER.parseExpression("random number is #{T(java.lang.Math).random()}", ParserContext.TEMPLATE_EXPRESSION);
        log.info("value -> {}", Objects.requireNonNull(expression.getValue()));
        log.info("expressionString -> {}", expression.getExpressionString());
        log.info("valueType -> {}", expression.getValueType());
        log.info("valueTypeDescriptor -> {}", expression.getValueTypeDescriptor());
    }

    @Test
    void parseContextStr() {
        ParserContext parserContext = new TemplateParserContext("#{", "}");
        Expression expression = PARSER.parseExpression("Hello World", ParserContext.TEMPLATE_EXPRESSION);
        log.info("value -> {}", Objects.requireNonNull(expression.getValue()));
        log.info("expressionString -> {}", expression.getExpressionString());
        log.info("valueType -> {}", expression.getValueType());
        log.info("valueTypeDescriptor -> {}", expression.getValueTypeDescriptor());
    }

    @Test
    void parseProgram() {
        ParserContext parserContext = new TemplateParserContext("#{", "}");
        Expression expression = PARSER.parseExpression("#{T(System).out.println('Hello!!!!!!!!')} ; #{T(System).out.println('World!!!{}!!!!!')}", parserContext);
        expression.getValue();
    }
}
