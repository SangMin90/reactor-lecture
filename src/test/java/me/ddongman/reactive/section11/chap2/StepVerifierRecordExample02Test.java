package me.ddongman.reactive.section11.chap2;

import me.ddongman.reactive.section11.RecordExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

/**
 * emit되는 모든 데이터들을 캡쳐하여 기록한 후, 기록된 데이터들을 검증하는 예제
 * - expectRecordedMatches()를 사용하여 기록된 데이터들이 조건에 맞는지 검증한다.
 */
public class StepVerifierRecordExample02Test {

    @Test
    void test() {
        StepVerifier
                .create(RecordExample.getCountry(Flux.just("france", "russia", "greece", "poland")))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .expectRecordedMatches(
                        countries ->
                                countries.stream()
                                        .allMatch(country -> Character.isUpperCase(country.charAt(0)))
                )
                .expectComplete()
                .verify();
    }
}
