package com.example.event;

import com.example.model.MessageModel;
import com.lmax.disruptor.EventFactory;

/**
 * @author Javen
 * @date 2022/3/26
 */
public class HelloEventFactory implements EventFactory<MessageModel> {

    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
