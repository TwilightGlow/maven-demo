package com.example.spEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangjw54
 */
@Slf4j
public class EvaluationContextTest {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String name;
        private Integer age;
        private User friend;
    }

    @Test
    void evaluationContext() {
        User user = new User("Javen", 30, new User());
        Expression exp = PARSER.parseExpression("#{name}", ParserContext.TEMPLATE_EXPRESSION);
        String name = exp.getValue(user, String.class);
        log.info("查询name的结果 -> {}", name);

        // 给对象的属性赋值
        Expression exp2 = PARSER.parseExpression("age = 50");
        Integer age = exp2.getValue(user, Integer.class);
        log.info("更新age -> {}", age);
        log.info("更新后的user -> {}", user);

        // 属性值判断
        Expression exp3 = PARSER.parseExpression("age == 50");
        Boolean value = exp3.getValue(user, Boolean.class);
        log.info("对age进行判断 -> {}", value);

        // Map<String, Object> map = Map.of("name", "Javen", "age", 50);
        // Expression exp4 = PARSER.parseExpression("age == 50");
        // Boolean mapValue = exp4.getValue(map, Boolean.class); // 不能传Map
        // log.info("对age进行判断 -> {}", mapValue);
    }

    // #代表表达式中会被替换的值
    @Test
    void standardEvaluationContext() {
        Expression exp = PARSER.parseExpression("#age == 30");
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("age", 30);
        Boolean value = exp.getValue(context, Boolean.class);
        log.info("对age进行判断 -> {}", value);

        User user = new User("Javen", 30, new User());
        Expression exp1 = PARSER.parseExpression("age = #age");
        StandardEvaluationContext context1 = new StandardEvaluationContext(user);
        context1.setVariable("age", 18);
        Integer age = exp1.getValue(context1, Integer.class);
        log.info("更新age -> {}", age);
        log.info("更新后的user -> {}", user);
    }

    // 在访问属性之前，通过?判定，如果为空则直接返回null
    @Test
    void nplJustify() {
        User user = new User();
        Expression expression = PARSER.parseExpression("friend?.name");
        Object result = expression.getValue(user);
        log.info("空指针判断 -> {}", result);

        user.setFriend(new User("Gallen", 30, null));
        Object value = expression.getValue(user);
        log.info("空指针判断 -> {}", value);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Item {
        private Integer price;
        private Integer count;
        private List<String> productNameList;
        private Map<String, Integer> studentScoreMap;
    }

    @Test
    void collectionTest() throws NoSuchMethodException {
        Item item = new Item();
        // 这种方式会将整个花括号中的内容当成一个字符串，最终list中只有一个字符串
        PARSER.parseExpression("productNameList").setValue(item, "{'抽烟','喝酒','烫头','',null}");

        // 先通过表达式将字符串转为list，再setValue
        PARSER.parseExpression("productNameList").setValue(item,
                PARSER.parseExpression("{'抽烟','喝酒','烫头','',null}").getValue(List.class));

        // 将字符串转为map，并设值
        PARSER.parseExpression("studentScoreMap").setValue(item,
                PARSER.parseExpression("{'Tim':59,'李四维':69,'青青':99,'juicy':89}").getValue(Map.class));

        // 新建计算上下文，并设置rootObject
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(item);
        // 注册方法
        standardEvaluationContext.registerFunction("StringUtils_hasText",
                StringUtils.class.getDeclaredMethod("hasText", CharSequence.class));

        // 通过.?[表达式]过滤集合，并产生一个新的集合
        // 通过#methodName调用上面注册的方法
        // 通过#this，引用list当前循环的元素
        List<?> filteredList = PARSER.parseExpression("productNameList.?[#StringUtils_hasText(#this)]")
                .getValue(standardEvaluationContext, List.class);
        log.info("原始的List -> {}", item.getProductNameList());
        log.info("过滤的List -> {}", filteredList);

        // 通过value过滤数据
        Map<?, ?> filterValue = PARSER.parseExpression("studentScoreMap.?[value<60]")
                .getValue(standardEvaluationContext, Map.class);

        // 通过key即可引用当前键值对的key
        Map<?, ?> filterKey = PARSER.parseExpression("studentScoreMap.?[key=='juicy']")
                .getValue(standardEvaluationContext, Map.class);

        // .^[表达式] 获取匹配的第一个元素
        Map<?, ?> first = PARSER.parseExpression("studentScoreMap.^[value>60]")
                .getValue(standardEvaluationContext, Map.class);

        // .$[表达式] 获取匹配的最后一个元素
        Map<?, ?> last = PARSER.parseExpression("studentScoreMap.$[value>60]")
                .getValue(standardEvaluationContext, Map.class);

        // .![表达式] 根据表达式生成一个新的集合
        Set<?> nameSet = PARSER.parseExpression("studentScoreMap.![key]")
                .getValue(standardEvaluationContext, Set.class);

        log.info("原始的Map -> {}", item.getStudentScoreMap());
        log.info("通过value过滤数据 -> {}", filterValue);
        log.info("通过key过滤数据 -> {}", filterKey);
        log.info("通过first过滤数据 -> {}", first);
        log.info("通过last过滤数据 -> {}", last);
        log.info("生成新的集合 -> {}", nameSet);
    }
}
