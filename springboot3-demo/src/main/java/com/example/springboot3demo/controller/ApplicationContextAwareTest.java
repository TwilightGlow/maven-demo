package com.example.springboot3demo.controller;

import com.example.springboot3demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhangjw54
 */
@Component
@RequiredArgsConstructor
public class ApplicationContextAwareTest implements ApplicationContextAware {

    private TestService service;

    // public ApplicationContextAwareTest() {
    //     System.out.println("...");
    // }

    // public ApplicationContextAwareTest(TestService service) {
    //     System.out.println("...");
    // }

    // @Autowired
    public void setService(TestService service) {
        this.service = service;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAwareTest.setApplicationContext");
    }
}
