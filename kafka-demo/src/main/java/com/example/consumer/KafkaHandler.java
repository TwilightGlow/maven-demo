package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jinwei Zhang
 */
@Slf4j
@Component
public class KafkaHandler {

    @KafkaListener(id = "myConsumer", topics = "test")
    public void receiveHandle(String record) {
        System.out.println("收到请求： " + record);
    }
}
