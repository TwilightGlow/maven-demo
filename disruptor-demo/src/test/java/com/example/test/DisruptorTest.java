package com.example.test;

import com.example.service.DisruptorMqService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Javen
 * @date 2022/3/26
 */
@Slf4j
@SpringBootTest
public class DisruptorTest {

    @Autowired
    private DisruptorMqService disruptorMqService;

    @Test
    public void sayHelloMqTest() throws Exception{
        disruptorMqService.sayHelloMq("消息到了，Hello world!");
        disruptorMqService.sayHelloMq("Hello Javen!");
        disruptorMqService.sayHelloMq("Welcome Javen!");
        log.info("消息队列已发送完毕");
        Thread.currentThread().join();
    }
}
