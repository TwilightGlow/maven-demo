package com.example.config;

import com.example.consumer.HelloEventHandler;
import com.example.event.HelloEventFactory;
import com.example.model.MessageModel;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Javen
 * @date 2022/3/26
 */
@Configuration
public class MQManager {

    @Bean("messageModel")
    public RingBuffer<MessageModel> messageModelRingBuffer() {
        //定义用于事件处理的线程池， Disruptor通过java.util.concurrent.ExecutorService提供的线程来触发consumer的事件处理
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //指定事件工厂
        HelloEventFactory factory = new HelloEventFactory();

        //指定RingBuffer字节大小，必须为2的N次方（能将求模运算转为位运算提高效率），否则将影响效率
        int bufferSize = 1024 * 256;

        //单线程模式，获取额外的性能
        CustomizableThreadFactory threadFactory = new CustomizableThreadFactory("javen-threadPool-");
        Disruptor<MessageModel> disruptor = new Disruptor<>(factory, bufferSize, threadFactory,
                ProducerType.SINGLE, new BlockingWaitStrategy());

        //设置事件业务处理器---消费者
        disruptor.handleEventsWith(new HelloEventHandler());

        // 启动disruptor线程
        disruptor.start();

        //获取RingBuffer环，用于接取生产者生产的事件
        return disruptor.getRingBuffer();
    }
}
