package com.example.springboot3demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjw54
 */
@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot 3!";
    }
}
