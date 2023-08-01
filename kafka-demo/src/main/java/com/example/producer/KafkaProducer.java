package com.example.producer;

import com.example.model.Foo1;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinwei Zhang
 */
@RestController
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @GetMapping("/kafka/send/string")
    public void sendString() {
        kafkaTemplate.send("test", "123");
    }

    @GetMapping("/kafka/send/foo")
    public void sendFpp() {
        kafkaTemplate.send("test", new Foo1("456"));
    }

}
