package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks Many 에제
 * - unicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit 하는 예제
 */
public class SinksManyExample01 {
    public static void main(String[] args) {
        Sinks.Many<Integer> sinks = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> flux = sinks.asFlux();

        sinks.emitNext(1, FAIL_FAST);
        sinks.emitNext(2, FAIL_FAST);

        flux.subscribe(data -> Logger.onNext("Subscriber1: {}", data));

        sinks.emitNext(3, FAIL_FAST);

        flux.subscribe(data -> Logger.onNext("Subscriber2: {}", data));
    }
}
