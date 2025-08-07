package me.ddongman.reactive.section11.chap1;

import me.ddongman.reactive.section11.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * expectError()를 사용해서 onError signal 발생 여부를 검증
 */
public class StepVerifierGeneralExample04Test {
    
    @Test
    void occurErrorTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier
                .create(GeneralExample.occurError(source))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectError()
                .verify();
    }
}
