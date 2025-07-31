package me.ddongman.reactive.section9.chap3;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Context는 체인의 맨 아래에서부터 위로 전파된다.
 */
public class ContextFeatureExample02 {
    public static void main(String[] args) {
        final String key1 = "id";
        final String key2 = "name";

        Mono
                .deferContextual(
                        ctx -> Mono.just(ctx.get(key1))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key2, "Kevin"))
                .transformDeferredContextual(
                        (mono, ctx) -> mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Tom"))
                )
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
