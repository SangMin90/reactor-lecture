package me.ddongman.reactive.section9.chap2;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context API 중 write API 예제 코드
 * - Context.of 사용
 */
public class ContextAPIExample01 {
    public static void main(String[] args) {
        String key1 = "ID";
        String key2 = "NAME";
        Mono<String> mono = Mono.deferContextual(ctx ->
                        Mono.just("ID - " + ctx.get(key1) + ", NAME - " + ctx.get(key2))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(Context.of(key1, "LEE", key2, "DDONGMAN"));

        mono.subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
