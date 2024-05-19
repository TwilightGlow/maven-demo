package com.example.controller;

import com.example.annotation.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javen
 * @date 2022/3/12
 */
@Slf4j
@Profile("dev")
@RestController
public class MyController {

    @Autowired
    private MyController myController;

    @MyAnnotation("Gallen")
    @GetMapping("test")
    public String test() {
        System.out.println(myController);
        log.trace("This is trace log");
        log.debug("This is debug log");
        log.info("This is info log");
        log.warn("This is warn log");
        log.error("This is error log");
        return "Hello World!";
    }

    @Transactional
    @GetMapping("transaction")
    public String trans() {
        return "Transactional!";
    }
}
