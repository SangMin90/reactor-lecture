package me.ddongman.reactive.section4;

import reactor.core.publisher.Mono;

/**
 * Mono 기본 개념 예제
 * 1개의 데이터를 생성해서 emit.
 */
public class MonoExample1 {
    public static void main(String[] args) {
        Mono.just("Hello Reactor")
                .subscribe(data -> System.out.println("emitted data: " + data));
    }
}
