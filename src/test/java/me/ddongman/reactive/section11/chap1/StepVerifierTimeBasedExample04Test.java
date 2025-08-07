package me.ddongman.reactive.section11.chap1;

import me.ddongman.reactive.section11.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuples;

import java.time.Duration;

/**
 * expectNoEvent(Duration)으로 지정된 대기 시간 동안 이벤트가 없음을 검증하는 예제
 */
public class StepVerifierTimeBasedExample04Test {

    @Test
    void getVoteCount() {
        StepVerifier
                .withVirtualTime(() -> GeneralExample.getVoteCount(
                        Flux.interval(Duration.ofMinutes(1))
                ))
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNext(Tuples.of("중구", 15400))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }
}
