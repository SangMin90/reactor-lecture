package me.ddongman.reactive.section8.chap1;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * parallel()만 사용할 경우에는 병렬로 작업을 처리할 수 없다.
 * ParallelFlux의 runOn()을 사용하여 Scheduler를 할당하여 병렬로 작업을 처리한다.
 * CPU 코어 갯수 내에서 work thread를 할당한다.
 * work thread 개수를 제한할 수 있다.
 */
public class ParallelExample04 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
                .parallel(4)
                .runOn(Schedulers.parallel())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
