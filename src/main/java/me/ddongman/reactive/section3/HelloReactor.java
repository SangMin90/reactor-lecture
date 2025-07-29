package me.ddongman.reactive.section3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HelloReactor {
    public static void main(String[] args) {
        Mono.just("Hello Reactor")
                .subscribe(System.out::println);

        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence
                .map(String::toLowerCase)
                .subscribe(System.out::println);

    }
}
