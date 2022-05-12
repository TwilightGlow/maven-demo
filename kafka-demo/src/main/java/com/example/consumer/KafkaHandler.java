package com.example.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jinwei Zhang
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaHandler {

    private final KafkaTemplate kafkaTemplate;

    @KafkaListener(id = "myConsumer", topics = "test")
    public void receiveHandle(String record) {
        System.out.println("收到请求： " + record);
    }

    @KafkaListener(id = "myConsumer1", topics = "batch")
    public void receiveBatch(ConsumerRecord<Object, Object> consumerRecord) {
        System.out.println("收到请求： " + consumerRecord.value());
    }

    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("testtopic", 8, (short) 2);
    }
}
