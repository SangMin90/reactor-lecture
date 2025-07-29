package me.ddongman.reactive.section8.chap2;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Operator 체인에서 publishOn 메서드가 호출되면 publishOn 이후에 호출되는 Operator 체인은
 * 다음 publishOn 메서드가 호출되기 전까지 publishOn에서 지정한 쓰레드에서 실행된다.
 */
public class SchedulerOperatorExample03 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);
    }
}
