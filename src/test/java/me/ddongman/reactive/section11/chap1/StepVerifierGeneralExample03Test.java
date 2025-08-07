package me.ddongman.reactive.section11.chap1;

import me.ddongman.reactive.section11.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * verifyComplete()을 사용하여 검증 실행을 실행하고, 기대값으로 onComplete signal이 맞는지 검증하는 예제
 * - as()를 사용하여 테스트 실패한 expectXXXX()의 description을 지정한다.
 */
public class StepVerifierGeneralExample03Test {

    @Test
    void sayHelloReactorTest() {
        StepVerifier.create(GeneralExample.sayHelloReactor())
                .expectSubscription()
                .as("# expect subscription")    // expectXXXX에 대한 description
                .expectNext("Hi")
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }
}
