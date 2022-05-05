package com.example;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Javen
 * @date 2022/1/8
 */
public class ReactorTest {

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        System.out.println(integers);
    }

    @Test
    public void testMono() {
        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");
    }

    @Test
    public void testFlux() {
        Flux<Integer> ints = Flux.range(1, 4);
//        ints.subscribe(System.out::println,
//                error -> System.err.println("Error " + error),
//                () -> System.out.println("Done"),
//                sub -> sub.request(2));

        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        ints.subscribe(ss);
    }

    @Test
    public void testBackpressure() {
        Flux.range(1, 10)
                .doOnSubscribe(r -> System.out.println("调用Publisher的doOnSubscribes(), subscribe of " + r))
                .doOnRequest(r -> System.out.println("调用Publisher的doOnRequest(), request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Canceling after having received " + value);
                        request(1);
                    }
                });
    }

    @Test
    public void testGenerate() {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 2;
                });
        flux.subscribe(System.out::println);

        Flux<String> flux1 = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
//                    System.out.println("3 x " + i + " = " + 3 * i);
                    if (i == 10) {
                        sink.complete();
                    }
                    return state;
                });
        flux1.subscribe(System.out::println);
    }

}
