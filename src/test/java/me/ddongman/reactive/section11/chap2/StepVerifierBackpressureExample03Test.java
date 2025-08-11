package me.ddongman.reactive.section11.chap2;

import me.ddongman.reactive.section11.BackpressureExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * Backpressure DROP 전략을 사용하는 예제
 * - hasDropped()는 Hooks.onNextDropped()으로 dropped된 데이터를 검증하는 것으로
 * - OverflowStrategy의 DROP 전략과는 다르므로 테스트 시 예외 발생
 */
public class StepVerifierBackpressureExample03Test {

    @Test
    void test() {
        StepVerifier
                .create(BackpressureExample.generateNumberByDropStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .expectComplete()
                .verifyThenAssertThat()
                .hasDiscardedElements()
                .hasDiscarded(2, 3, 4, 5, 6, 98, 99, 100)
                //.hasDropped(2, 3, 4, 5, 6, 98, 99, 100)
        ;
    }
}
