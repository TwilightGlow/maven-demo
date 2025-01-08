package com.example.springboot3demo.bean;

import com.example.springboot3demo.controller.MyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangjw54
 */
@Component
public class Book {

    public Book() {
        System.out.println("无参构造器");
    }

    @Autowired
    public Book(MyController myController) {
        System.out.println("Book.MyController");
        System.out.println("有参构造器");
    }
}
