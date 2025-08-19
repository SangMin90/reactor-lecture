package me.ddongman.reactive.section11.chap3;

import me.ddongman.reactive.section11.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher를 사용한 서비스 로직에 대한 단위 테스트 예제
 */
public class TestPublisherExampleTest {

    @Test
    void 정상_동작하는_TestPublisher_next() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, 10))
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    void 에외_발생_검증하는_TestPublisher_next() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.occurError(source.flux()))
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, 10))
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }
    
    @Test
    void 직접_예외_시그널을_발생시켜_검증하는_TestPublisher_next() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.occurError(source.flux()))
                .expectSubscription()
                .then(() -> {
                    source.next(2, 4, 6, 8, 10);
                    source.error(new ArithmeticException());
                })
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }
    
    @Test
    void 정상_동작하는_TestPublisher_emit() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.takeNumber(source.flux(), 3))
                .expectSubscription()
                .then(() -> source.emit(1, 2, 3, 4, 5))
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

    @Test
    void emit_과_next의_차이_onComplete_시그널_발생_유무() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(source.flux().log())
                .expectSubscription()
                .then(() -> source.emit(1, 2, 3))
                //.then(() -> source.next(1, 2, 3))
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }
    
    @Test
    void Reactive_Streams_사양_위반을_허용하여_비즈니스_로직_검증() {
        // NULL emit 허용
        TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);
        //TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, null))
                .expectNext(1, 2, 3, 4)
                .expectComplete()
                .verify();
    }
}
