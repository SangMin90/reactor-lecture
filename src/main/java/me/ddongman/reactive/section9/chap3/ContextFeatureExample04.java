package me.ddongman.reactive.section9.chap3;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Inner Sequence 내부에서는 외부 Context에 저장된 값을 읽을 수 있다.
 * - Inner Sequence 내부에서 Context에 저장된 값을 Inner Sequence 외부에서 읽을 수 없다.
 */
public class ContextFeatureExample04 {
    public static void main(String[] args) {
        String key1 = "id";

        Mono.just("Kevin")
                //.transformDeferredContextual((stringMono, contextView) -> contextView.get("job"))
                .flatMap(
                        name ->
                                Mono.deferContextual(
                                        ctx -> Mono.just(ctx.get(key1) + ", " + name)
                                                .transformDeferredContextual(
                                                        (mono, innerCtx) -> mono.map(data -> data + ", " + innerCtx.get("job"))
                                                )
                                                .contextWrite(context -> context.put("job", "Software Engineer"))
                                )
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
