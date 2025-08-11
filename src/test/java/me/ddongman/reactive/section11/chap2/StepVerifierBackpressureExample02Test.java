package me.ddongman.reactive.section11.chap2;

import me.ddongman.reactive.section11.BackpressureExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * Backpressure ERROR 전략을 검증하는 예제
 * - expectError()를 사용하여 에러가 발생했는지 검증
 * - verifyThenAssertThat()을 사용하여 검증 이후, assertion method를 사용하여 추가 검증을 할 수 있다.
 * - hasDiscardedElements()를 사용하여 discard된 데이터가 있는지 검증한다.
 * - hasDiscard()를 사용하여 discard된 데이터가 무엇인지 검증한다.
 * - hasDroppedElements()를 사용하여 Hooks.onNextDropped()으로 drop된 데이터가 있는지 검증한다.
 * - hasDropped()를 사용하여 Hooks.onNextDropped()으로 drop된 데이터가 무엇인지 검증한다.
 */
public class StepVerifierBackpressureExample02Test {

    @Test
    void test() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .expectError()
                .verifyThenAssertThat()
                .hasDiscardedElements()
                .hasDiscarded(2)
                .hasDroppedElements()
                .hasDropped(3, 4, 5, 6, 98, 99, 100);
    }
}
