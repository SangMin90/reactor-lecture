package me.ddongman.reactive.section11.chap1;

import me.ddongman.reactive.section11.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * 1개 이상의 emit된 데이터를 검증
 */
public class StepVerifierGeneralExample05Test {
    
    @Test
    void divideByTwoTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier
                .create(GeneralExample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }
}
