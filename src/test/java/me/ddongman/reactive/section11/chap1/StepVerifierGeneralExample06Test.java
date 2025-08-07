package me.ddongman.reactive.section11.chap1;

import me.ddongman.reactive.section11.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

/**
 * onNext signal을 통해 emit된 데이터 개수를 검증하는 예제
 * - 검증 실패 시 StepVerifierOptions에서 지정한 scenarioName를 표시한다.
 */
public class StepVerifierGeneralExample06Test {

    @Test
    void rangeNumberTest() {
        Flux<Integer> source = Flux.range(0, 500);
        StepVerifier
                .create(GeneralExample.takeNumber(source, 500)
                        , StepVerifierOptions.create().scenarioName("Verify 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498)
                //.expectNext(499)
                .expectNext(500)
                .expectComplete()
                .verify();

    }
}
