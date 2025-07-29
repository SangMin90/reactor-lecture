package me.ddongman.reactive.section9;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 개념 설명 에제 코드
 * - contextWrite를 통해 Reactor Sequence 상에서 Context에 값을 쓸 수 있고,
 * - deferContextual 또는 transformDeferredContextual을 통해 ContextView를 불러와
 *   저장된 상태 값을 읽어와 Operator 체인에서 공유(서로 다른 쓰레드에서도 가능)할 수 있다.
 */
public class ContextIntroduceExample01 {
    public static void main(String[] args) {
        String key = "message";
        Mono<String> mono = Mono.deferContextual(ctx ->
                        Mono.just("Hello " + ctx.get(key)).doOnNext(Logger::doOnNext)
                )
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual((mono2, ctx) -> mono2.map(data -> data + " " + ctx.get(key)))
                .contextWrite(ctx -> ctx.put(key, "Reactor"));

        mono.subscribe(Logger::onNext);
        TimeUtils.sleep(200L);
    }
}
