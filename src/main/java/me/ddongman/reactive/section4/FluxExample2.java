package me.ddongman.reactive.section4;

import reactor.core.publisher.Flux;

/**
 * Flux에서의 Operator 체인 사용 예제
 */
public class FluxExample2 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(multiply -> System.out.println("multiply: " + multiply));
    }
}
