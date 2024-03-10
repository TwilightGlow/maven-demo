package com.example.spEL;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Objects;

/**
 * @author zhangjw54
 *
 * 主要接口 https://blog.csdn.net/weixin_45794666/article/details/123372058
 * <p>
 * ExpressionParser 接口：表示解析器，默认实现是 org.springframework.expression.spel.standard 包中的 SpelExpressionParser 类，
 * 使用 parseExpression 方法将字符串表达式转换为 Expression 对象，对于 ParserContext 接口用于定义字符串表达式是不是模板，及模板开始与结束字符；
 * <p>
 * EvaluationContext 接口：表示上下文环境，默认实现是 org.springframework.expression.spel.support 包中的 StandardEvaluationContext类，
 * 使用 setRootObject 方法来设置根对象，使用 setVariable 方法来注册自定义变量，使用 registerFunction 来注册自定义函数等等。
 * <p>
 * Expression 接口：表示表达式对象，默认实现是 org.springframework.expression.spel.standard 包中的 SpelExpression，
 * 提供 getValue 方法用于获取表达式值，提供 setValue 方法用于设置对象值。
 */
@Slf4j
public class SpelExpressionParserTest {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    // 直接解析字符串需要加''
    @Test
    void normalString() {
        Expression helloWorld = PARSER.parseExpression("'Hello World'");
        log.info("value -> {}", Objects.requireNonNull(helloWorld.getValue()));
        log.info("expressionString -> {}", helloWorld.getExpressionString());
        log.info("valueType -> {}", helloWorld.getValueType());
        log.info("valueTypeDescriptor -> {}", helloWorld.getValueTypeDescriptor());
    }

    // 直接解析表达式不用加''
    @Test
    void normalCalculate() {
        Expression calculate = PARSER.parseExpression("10 * (2 + 1) * 1 + 66");
        log.info("value -> {}", Objects.requireNonNull(calculate.getValue()));
        log.info("expressionString -> {}", calculate.getExpressionString());
        log.info("valueType -> {}", calculate.getValueType());
        log.info("valueTypeDescriptor -> {}", calculate.getValueTypeDescriptor());
    }

    /**
     * 在SpEL表达式中，使用T(Type)运算符会调用类的作用域和方法。换句话说，就是可以通过该类类型表达式来操作类。
     * 使用T(Type)来表示java.lang.Class实例，Type必须是类全限定名，但”java.lang”包除外，因为SpEL已经内置了该包，
     * 即该包下的类可以不指定具体的包名；使用类类型表达式还可以进行访问类静态方法和类静态字段。
     */
    @Test
    void type() {
        Expression expression = PARSER.parseExpression("T(java.lang.Math)");
        log.info("value -> {}", Objects.requireNonNull(expression.getValue()));
        log.info("expressionString -> {}", expression.getExpressionString());
        log.info("valueType -> {}", expression.getValueType());
        log.info("valueTypeDescriptor -> {}", expression.getValueTypeDescriptor());
    }

    @Test
    void calc() {
        Expression expression = PARSER.parseExpression("T(java.lang.Runtime).getRuntime().exec('calc')");
        // getValue会弹出计算器
        log.info("value -> {}", Objects.requireNonNull(expression.getValue()));
        log.info("expressionString -> {}", expression.getExpressionString());
        log.info("valueType -> {}", expression.getValueType());
        log.info("valueTypeDescriptor -> {}", expression.getValueTypeDescriptor());
    }

    @Test
    void newCalc() {
        // 上面那个例子也可以通过new对象的方式调用
        Expression expression = PARSER.parseExpression("new java.lang.ProcessBuilder('cmd','/c','calc').start()");
        log.info("value -> {}", Objects.requireNonNull(expression.getValue()));
        log.info("expressionString -> {}", expression.getExpressionString());
        log.info("valueType -> {}", expression.getValueType());
        log.info("valueTypeDescriptor -> {}", expression.getValueTypeDescriptor());
    }
}
