package com.example.test;

import com.example.controller.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author Javen
 * @date 2022/4/19
 */
@SpringBootTest
public class ProfileTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        MyController myController = applicationContext.getBean("myController", MyController.class);
        System.out.println(myController);
    }
}
