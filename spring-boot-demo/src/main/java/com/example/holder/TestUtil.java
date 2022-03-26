package com.example.holder;

/**
 * @author Javen
 * @date 2022/3/3
 */
public class TestUtil {

    public static String getSpringBean(String str) {
        return SpringContextHolder.getApplicationContext().getBean(str).toString();
    }
}
