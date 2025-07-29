package me.ddongman.reactive.section4;

import reactor.core.publisher.Flux;

/**
 * 여러 개의 Flux를 연결해서 하나의 Flux로 결합하는 예제
 */
public class FluxExample4 {
    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Venus"),
                Flux.just("Earth"),
                Flux.just("Mars")
        )
                .collectList()
                .subscribe(plainList -> System.out.println("Solar System: " + plainList));
    }
}
