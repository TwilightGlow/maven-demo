package com.example;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;

/**
 * @author Javen
 * @date 2022/1/8
 */
public class FluxApiDemo {

    public static void main(String[] args) {
        Flux.generate(() -> 0, (value, sink) -> {
            if (value == 6) {
                sink.complete();
            } else {
                sink.next("value = " + value);
            }
            return value + 2;
        }).subscribe(System.out::println);

        Flux.range(0, 10)
                .publishOn(Schedulers.immediate())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(Thread.currentThread().getName() + integer);
                    }
                });

        try {
            Thread.currentThread().join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
