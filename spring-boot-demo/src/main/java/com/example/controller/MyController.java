package com.example.controller;

import com.example.annotation.MyAnnotation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javen
 * @date 2022/3/12
 */
@RestController
public class MyController {

    @MyAnnotation
    @GetMapping("test")
    public String test() {
        return "Hello World!";
    }

    @Transactional
    @GetMapping("transaction")
    public String trans() {
        return "Transactional!";
    }

}
