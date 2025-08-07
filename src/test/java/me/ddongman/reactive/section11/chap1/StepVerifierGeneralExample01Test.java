package me.ddongman.reactive.section11.chap1;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * StepVerifier 기본 예제
 */
public class StepVerifierGeneralExample01Test {

    @Test
    public void sayHelloReactorTest() {
        StepVerifier.create(Mono.just("Hello Reactor")) // 테스트 대상 Sequence 생성
                .expectNext("Hello Reactor")              // onNext signal에 대한 emit된 데이터 검증
                .expectComplete()                           // onComplete signal 검증
                .verify();                                  // 검증 실행 트리거
    }
}
