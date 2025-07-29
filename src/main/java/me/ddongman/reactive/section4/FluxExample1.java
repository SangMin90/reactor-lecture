package me.ddongman.reactive.section4;

import reactor.core.publisher.Flux;

/**
 * Flux 기본 예제
 */
public class FluxExample1 {
    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(
                        remainder -> System.out.println("remainder: " + remainder)
                );
    }
}
