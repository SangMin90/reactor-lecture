package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks Many 예제
 * - replay()를 사용해서 하나 이상의 Subscriber에게 이미 emit된 데이터 중 특정 개수의 최근 데이터만 emit하는 예제
 */
public class SinksManyExample03 {
    public static void main(String[] args) {
        Sinks.Many<Integer> sinks = Sinks.many().replay().limit(2);
        Flux<Integer> flux = sinks.asFlux();

        sinks.emitNext(1, FAIL_FAST);
        sinks.emitNext(2, FAIL_FAST);
        sinks.emitNext(3, FAIL_FAST);

        flux.subscribe(data -> Logger.onNext("Subscriber1: {}", data));
        flux.subscribe(data -> Logger.onNext("Subscriber2: {}", data));
    }
}
