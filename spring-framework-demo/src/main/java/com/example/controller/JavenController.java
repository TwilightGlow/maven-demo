package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javen
 * @date 2022/2/16
 */
@RestController
public class JavenController {

    @GetMapping("/test")
    public String test() {
        return "Hello Javen!!!";
    }
}
