package com.example.springboot3demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangjw54
 */
@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot 3!";
    }

    Map<String, Object> lock = new ConcurrentHashMap<>();
    @PostMapping("/saving")
    public String saving(String school) {
        lock.compute(school, (k, v) -> v == null ? new Object() : v);
        lock.computeIfPresent(school, (k, v) -> v == null ? new Object() : v);
        lock.computeIfAbsent(school, k -> new Object());
        synchronized (school.intern()) {
            System.out.println(school + "学生交卷");
            save(school);
            System.out.println(school + "学生交卷成功");
            return school + "学生交卷成功";
        }
    }

    static Map<String, Integer> values = new ConcurrentHashMap<>();
    private void save(String account) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (values.containsKey(account)) {
            values.put(account, values.get(account) + 1);
            System.out.println("2222");
        } else {
            values.put(account, 1);
            System.out.println("1111");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyController myController = new MyController();
        new Thread(() -> myController.saving("北大")).start();
        new Thread(() -> myController.saving("清华")).start();
        new Thread(() -> myController.saving("清华")).start();
        Thread.sleep(10000);
        System.out.println(values);
    }
}
