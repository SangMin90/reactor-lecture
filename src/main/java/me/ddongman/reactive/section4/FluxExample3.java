package me.ddongman.reactive.section4;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 2개의 Mono를 연결해서 Flux로 변환하는 예제
 */
public class FluxExample3 {
    public static void main(String[] args) {
        Flux<Object> flux = Mono.justOrEmpty(null)
                .concatWith(Mono.justOrEmpty("Jobs"));

        flux.subscribe(data -> System.out.println("result: " + data));
    }
}
