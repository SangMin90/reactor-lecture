package me.ddongman.reactive.section8.chap2;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscribeOn()과 publishOn()이 같이 있다면, publishOn()을 만나기 전까지 Upstream Operator 체인은
 * subscribeOn()에서 지정한 쓰레드에서 실행되고, publishOn()을 만날 때마다
 * publishOn() 이후의 Downstream Operator 체인은 publishOn()에서 지정한 쓰레드에서 실행된다.
 */
public class SchedulerOperatorExample05 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> Logger.doOnNext("fromArray", data))
                .subscribeOn(Schedulers.boundedElastic())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
