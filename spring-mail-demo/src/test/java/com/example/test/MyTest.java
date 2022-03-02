package com.example.test;

import com.example.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Javen
 * @date 2022/2/14
 */
@SpringBootTest
public class MyTest {

    @Autowired
    private MailService mailService;

    @Test
    public void test() {
        mailService.sendSimple();
    }

    @Test
    public void sendComplicated() {
        mailService.sendComplicated();
    }
}
