package me.ddongman.reactive.section10.chap1;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

/**
 * Hooks.onOperatorDebug() 메서드를 이용한 Debugging mode
 * - 애플리케이션 전체에서 global하게 동작
 * - 모든 Operator의 stack trace를 캡처링한다.
 * - 비용이 많이 듦
 */
public class DebuggingModeExample02 {
    public static void main(String[] args) {
        Hooks.onOperatorDebug();

        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
