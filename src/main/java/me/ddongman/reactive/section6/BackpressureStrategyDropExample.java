package me.ddongman.reactive.section6;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Unbounded request일 경우, Downstream에 Backpressure drop 전략을 사용하는 예제
 * Downstrea에 전달된 데이터가 버퍼에 가득찰 경우, 버퍼 밖에서 대기하는 먼저 emit된 데이터를 drop 시키는 전략
 */
public class BackpressureStrategyDropExample {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(1L))
                //.doOnNext(Logger::doOnNext)
                .onBackpressureDrop(dropped -> Logger.info("dropped: {}", dropped))
                .publishOn(Schedulers.parallel())
                .subscribe(
                        data -> {
                            TimeUtils.sleep(5L);
                            Logger.onNext(data);
                        },
                        Logger::onError
                );

        TimeUtils.sleep(2000L);
    }
}
