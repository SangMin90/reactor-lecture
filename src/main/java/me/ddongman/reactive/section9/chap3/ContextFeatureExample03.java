package me.ddongman.reactive.section9.chap3;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Context에 이미 존재하는 키에 대해 write할 경우, 값을 덮어쓴다.
 */
public class ContextFeatureExample03 {
    public static void main(String[] args) {
        String key1 = "id";

        Mono.deferContextual(
                ctx -> Mono.just("ID: " + ctx.get(key1))
        )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itWorld"))
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
