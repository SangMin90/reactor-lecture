package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks Many 예제
 * - replay.all()을 사용하여 구독 시점과 관계없이 emit된 모든 데이터를 replay하는 예제
 */
public class SinksManyExample05 {
    public static void main(String[] args) {
        Sinks.Many<Integer> sinks = Sinks.many().replay().all();
        Flux<Integer> flux = sinks.asFlux();

        sinks.emitNext(1, FAIL_FAST);
        sinks.emitNext(2, FAIL_FAST);

        flux.subscribe(data -> Logger.onNext("Subscriber1: {}", data));

        sinks.emitNext(3, FAIL_FAST);

        flux.subscribe(data -> Logger.onNext("Subscriber2: {}", data));
    }
}
