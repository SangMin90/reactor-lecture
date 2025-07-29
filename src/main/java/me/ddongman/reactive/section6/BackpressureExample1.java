package me.ddongman.reactive.section6;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
public class BackpressureExample1 {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnNext(Logger::doOnNext) // 요청한 데이터 개수만큼 emit
                .doOnRequest(Logger::doOnRequest)   // Subscriber가 요청한 데이터 개수
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        TimeUtils.sleep(2000L);
                        Logger.onNext(value);
                        request(1);
                    }
                });

    }
}
