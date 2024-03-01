package com.example.regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangjw54
 */
public class RegexTest {

    public static void main(String[] args) {
        String input = "('1',  '2', '3 4', 'xxx xxx')";
        ArrayList<String> matches = new ArrayList<String>();

        // 定义正则表达式匹配模式
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(input);

        // 找到所有匹配项并将其添加到数组中
        while (matcher.find()) {
            String match = matcher.group(1);
            matches.add(match);
        }

        // 打印匹配结果
        for (String match : matches) {
            System.out.println(match);
        }
    }
}
