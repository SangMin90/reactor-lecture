package me.ddongman.reactive.section6;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
public class BackpressureExample2 {
    private static int count = 0;
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnNext(Logger::doOnNext)
                .doOnRequest(Logger::doOnRequest)
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        count++;
                        Logger.onNext(value);
                        if (count == 2) {
                            TimeUtils.sleep(2000L);
                            request(2);
                            count = 0;
                        }
                    }
                });
    }
}
