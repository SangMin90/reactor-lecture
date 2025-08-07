package me.ddongman.reactive.section11.chap1;

import me.ddongman.reactive.section11.TimeBasedExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

/**
 * 실제 시간을 가상 시간으로 대체하는 테스트
 * - 특정 시간만큼 앞당긴다.
 */
public class StepVerifierTimeBasedExample01Test {
    
    @Test
    void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                        Flux.interval(Duration.ofHours(12L)).take(1)
                ))
                .expectSubscription()
                .then(() -> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(12L)))
                .expectNextCount(11)
                .expectComplete()
                .verify();

    }
}
