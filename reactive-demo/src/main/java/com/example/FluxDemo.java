package com.example;

import reactor.core.publisher.Flux;

/**
 * @author Javen
 * @date 2022/1/8
 */
public class FluxDemo {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3).map(value -> {
            if (value == 2) {
//                throw new RuntimeException();
                return value;
            }
            return value;
        });
        flux.subscribe(System.out::println,
                throwable -> System.out.println("Error"),
                () -> System.out.println("Subscription is completed!"));;
    }

}
