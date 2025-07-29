package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks One 예제
 * - 두 건의 데이터를 emit 하는 예제
 */
public class SinksOneExample02 {
    public static void main(String[] args) {
        Sinks.One<String> sinks = Sinks.one();
        Mono<String> mono = sinks.asMono();

        sinks.emitValue("Hello Reactor", FAIL_FAST);

        // Sinks One은 단 한 건의 데이터만 emit하므로 그 이상의 데이터는 Drop 한다.
        sinks.emitValue("Hi Reactor", FAIL_FAST);

        mono.subscribe(data -> Logger.onNext("subscriber1: {}", data));
        mono.subscribe(data -> Logger.onNext("subscriber2: {}", data));
    }
}
