package me.ddongman.reactive.section4;

import reactor.core.publisher.Mono;

/**
 * Mono 기본 개념 예제
 * 원본 데이터의 emit 없이 onComplete signal만 emit.
 */
public class MonoExample2 {
    public static void main(String[] args) {
        Mono.empty()
                .subscribe(
                        data -> System.out.println("emitted data: " + data),
                        error -> {},
                        () -> System.out.println("emitted onComplete signal.")
                );
    }
}
