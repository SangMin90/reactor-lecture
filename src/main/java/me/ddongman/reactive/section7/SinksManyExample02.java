package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks Many 예제
 * - multicast()를 사용하여 하나 이상의 Subscriber에게 데이터를 emit하는 에제
 */
public class SinksManyExample02 {
    public static void main(String[] args) {
        Sinks.Many<Integer> sinks = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux = sinks.asFlux();

        sinks.emitNext(1, FAIL_FAST);
        sinks.emitNext(2, FAIL_FAST);

        flux.subscribe(data -> Logger.onNext("Subscriber1: {}", data));

        // hot sequence 중 warm up 방식 (warm up: 최초 구독이 발생하기 전까지는 데이터의 emit이 발생하지 않는 것을 말합니다.)
        flux.subscribe(data -> Logger.onNext("Subscriber2: {}", data));

        sinks.emitNext(3, FAIL_FAST);
    }
}
