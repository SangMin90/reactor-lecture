package me.ddongman.reactive.section8.chap2;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscribeOn 메서드는 구독 직후에 실행될 쓰레드를 지정한다.
 * 즉, 최상위 Upstream Publisher의 실행 쓰레드를 subscribeOn에서 지정한 쓰레드로 변경한다.
 */
public class SchedulerOperatorExample04 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
