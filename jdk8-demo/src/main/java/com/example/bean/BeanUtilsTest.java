package com.example.bean;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangjw54
 */
public class BeanUtilsTest {

    public static void main(String[] args) {
        Book book = BeanUtils.instantiateClass(Book.class);
        System.out.println(book);
    }
}
