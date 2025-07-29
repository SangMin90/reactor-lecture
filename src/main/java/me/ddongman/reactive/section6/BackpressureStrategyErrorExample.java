package me.ddongman.reactive.section6;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Error 전략을 적용하는 예제.
 * Downstream으로 전달할 데이터가 버퍼에 가득찰 경우, Exception을 발생 시키는 전략
 */
public class BackpressureStrategyErrorExample {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureError()
                .doOnNext(Logger::doOnNext)
                .publishOn(Schedulers.parallel())   // 스레드 하나 추가
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
