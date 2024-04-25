package com.example.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @date 2022/4/1
 */
@Indexed
@RestController
public class LoginController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BeanFactory beanFactory;

    @GetMapping("login")
    public Map<String, String> login() {
        HashMap<String, String> map = new HashMap<>();
        System.out.println(applicationContext);
        System.out.println(beanFactory);
        return map;
    }
}
