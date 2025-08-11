package me.ddongman.reactive.section11.chap2;

import me.ddongman.reactive.section11.BackpressureExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * Backpressure 전략에 따라 Exception이 발생하는 예제
 * - request 데이터 개수보다 많은 데이터가 emit되면 OverFlowException이 발생
 * - OverflowException이 발생한 데이터는 discard 된다.
 * - 나머지 emit된 데이터는 Hooks.onNextDropped()에 의해 dropped 된다.
 */
public class StepVerifierBackpressureExample01Test {

    @Test
    void case1() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }
}
