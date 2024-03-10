package com.example.spEL;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangjw54
 */
@Slf4j
public class LabelFormulaTest {

    @Test
    void labelFormula() {
        String formula = "#labelA == '1' && #labelB != '2' && labelC in (1, 2)";
        String IN_REGEX = "\\S+(?<!(?i)not)\\s*(?i)in\\s*\\(.*?\\)";
        Matcher matcher = Pattern.compile(IN_REGEX).matcher(formula);
        String labelC = "1";
        while (matcher.find()) {
            String inExpression = matcher.group();
            Matcher valueMatcher = Pattern.compile("(?<=\\().*?(?=\\))").matcher(inExpression);
            String values = "";
            while (valueMatcher.find()) {
                values = valueMatcher.group();
            }
            List<String> list = Arrays.stream(values.split(",")).map(String::trim).toList();
            StringBuilder stringBuffer = new StringBuilder();
            if (list.contains(labelC)) {
                matcher.appendReplacement(stringBuffer, "true");
            } else {
                matcher.appendReplacement(stringBuffer, "false");
            }
            matcher.appendTail(stringBuffer);
            formula = stringBuffer.toString();
        }
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(formula);
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("labelA", "1");
        evaluationContext.setVariable("labelB", "2");
        log.info("表达式的值 -> {}", expression.getValue(evaluationContext));
    }
}
