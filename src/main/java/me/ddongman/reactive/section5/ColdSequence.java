package me.ddongman.reactive.section5;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class ColdSequence {
    public static void main(String[] args) {
        Flux<String> coldSequence = Flux.fromIterable(Arrays.asList("RED", "YELLOW", "PINK"))
                .map(String::toLowerCase);

        coldSequence.subscribe(data -> System.out.println("Subscriber1: " + data));
        System.out.println("--------------------------");
        coldSequence.subscribe(data -> System.out.println("Subscriber2: " + data));
    }
}
