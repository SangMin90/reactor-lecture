package me.ddongman.reactive.section8.chap3;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newBoundedElastic() 적용
 */
public class SchedulerNewBoundedElasticExample01 {
    public static void main(String[] args) {
        Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread");
        Mono<Integer> mono = Mono.just(1)
                .subscribeOn(scheduler);

        Logger.info("# Start");

        mono.subscribe(data -> {
            Logger.onNext("subscribe 1 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 1 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 2 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 2 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 3 doing", data);
            Logger.onNext("subscribe 3 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 4 doing", data);
            Logger.onNext("subscribe 4 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 5 doing", data);
            Logger.onNext("subscribe 5 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 6 doing", data);
            Logger.onNext("subscribe 6 done", data);
        });

        TimeUtils.sleep(5000L);
        scheduler.dispose();
    }
}
