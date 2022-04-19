package com.example.starter;

import com.example.service.GenerateIdService;
import com.example.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Javen
 * @date 2022/4/14
 */
@SpringBootTest
public class HelloStarterTest {

    @Autowired
    private HelloService helloService;

    @Autowired
    private GenerateIdService generateIdService;

    @Test
    public void test() {
        System.out.println(helloService.sayHello("123"));
    }

    @Test
    public void testId() {
        System.out.println(generateIdService.getId());
    }
}
