package me.ddongman.reactive.section11.chap1;

import me.ddongman.reactive.section11.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * expectNext()를 사용하여 emit된 n개의 데이터를 검증
 */
public class StepVerifierGeneralExample02Test {

    @Test
    public void sayHelloReactorTest() {
        StepVerifier.create(GeneralExample.sayHelloReactor())
                .expectSubscription()   // 구독이 발생하길 기대
                .expectNext("Hello")
                .expectNext("Reactor")
                .expectComplete()
                .verify();
    }
}
