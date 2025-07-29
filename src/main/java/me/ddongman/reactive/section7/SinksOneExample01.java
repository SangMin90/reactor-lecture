package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks One 예제
 * - 한 건의 데이터만 emit 하는 예제
 */
public class SinksOneExample01 {
    public static void main(String[] args) {
        // emit된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달된다. 나머지 데이터는 drop 됨.
        Sinks.One<String> sinks = Sinks.one();
        Mono<String> mono = sinks.asMono();

        sinks.emitValue("Hello Reactor", FAIL_FAST);

        mono.subscribe(data -> Logger.onNext("subscriber1: {}", data));
        mono.subscribe(data -> Logger.onNext("subscriber2: {}", data));
    }
}
