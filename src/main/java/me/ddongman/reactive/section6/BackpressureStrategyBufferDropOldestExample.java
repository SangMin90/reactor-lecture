package me.ddongman.reactive.section6;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackpressureStrategyBufferDropOldestExample {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(300L))
                .doOnNext(data -> Logger.info("emitted by original flux: {}", data))
                .onBackpressureBuffer(
                        2,
                        dropped -> Logger.info("Overflow & dropped: {}", dropped),
                        BufferOverflowStrategy.DROP_OLDEST
                )
                .doOnNext(data -> Logger.info("emitted by buffer: {}", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtils.sleep(1000L);
                            Logger.onNext(data);
                        },
                        Logger::onError
                );

        TimeUtils.sleep(3000L);
    }
}
