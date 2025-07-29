package me.ddongman.reactive.section8.chap1;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * parallel()만 수행할 경우 병렬로 작업을 수행하지 않는다.
 * - ParallelFlux의 runOn()을 사용하여 Scheduler를 할당하여 병렬로 작업을 수행한다.
 */
public class ParallelExample02 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
