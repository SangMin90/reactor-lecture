package me.ddongman.reactive.section8.chap3;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newParallel() 적용
 */
public class SchedulerNewParallelExample01 {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono
                .just(1)
                .publishOn(Schedulers.newParallel("Parallel Thread", 4, true));

        mono
                .subscribe(data -> {
                    TimeUtils.sleep(5000L);
                    Logger.onNext("subscribe 1", data);
                });

        mono
                .subscribe(data -> {
                    TimeUtils.sleep(4000L);
                    Logger.onNext("subscribe 2", data);
                });

        mono
                .subscribe(data -> {
                    TimeUtils.sleep(3000L);
                    Logger.onNext("subscribe 3", data);
                });

        mono
                .subscribe(data -> {
                    TimeUtils.sleep(2000L);
                    Logger.onNext("subscribe 4", data);
                });

        TimeUtils.sleep(6000L);
    }
}
