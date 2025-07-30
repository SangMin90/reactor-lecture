package me.ddongman.reactive.section9.chap2;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context API 예제 코드
 * - putAll(ContextView) API 사용
 */
public class ContextAPIExample02 {
    public static void main(String[] args) {
        String key1 = "ID";
        String key2 = "NANE";
        String key3 = "COUNTRY";

        Mono<String> mono = Mono.deferContextual(ctx ->
                        Mono.just("ID - " + ctx.get(key1)
                                + ", NAME - " + ctx.get(key2)
                                + ", COUNTRY - " + ctx.get(key3)
                        )
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(ctx ->
                        ctx.putAll(
                                Context.of(key2, "DDONGMAN", key3, "KOREA").readOnly()
                        )
                )
                .contextWrite(ctx -> ctx.put(key1, "LEE"));

        mono.subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
