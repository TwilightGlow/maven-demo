package com.example;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @author Javen
 * @date 2022/1/8
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println(value);
        requestUnbounded();
//        request(1);
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Success completed");
    }

    @Override
    protected void hookOnError(Throwable throwable) {
        System.out.println("Error!!!");
    }
}
