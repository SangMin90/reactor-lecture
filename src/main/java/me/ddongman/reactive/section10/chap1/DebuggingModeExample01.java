package me.ddongman.reactive.section10.chap1;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * Non-debugging mode
 */
public class DebuggingModeExample01 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
