package me.ddongman.reactive.section9.chap3;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Context는 각각의 구독을 통해 Reactor Sequence에 연결되며,
 * - 체인의 각각 Operator는 실행 쓰레드가 달라도 연결된 Context에 접근할 수 있다.
 */
public class ContextFeatureExample01 {
    public static void main(String[] args) {
        String key1 = "id";
        Mono<String> mono = Mono.deferContextual(
                        ctx -> Mono.just("ID: " + ctx.get(key1))
                )
                .publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(data -> Logger.onNext("Subscriber 1", data));

        mono.contextWrite(context -> context.put(key1, "itWorld"))
                .subscribe(data -> Logger.onNext("Subscriber 2", data));

        TimeUtils.sleep(100L);
    }
}
