package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/**
 * Sinks를 사용하는 예제
 * - Publisher의 데이터 생성을 멀티 쓰레드에서 진행해도 Thread safe 하다
 */
public class ProgrammaticSinksExample {
    public static void main(String[] args) {
        int tasks = 6;

        Sinks.Many<String> sinks = Sinks.many().unicast().onBackpressureBuffer();
        Flux<String> flux = sinks.asFlux();
        IntStream
                .range(0, tasks)
                .forEach(data -> {
                    try {
                        new Thread(() -> {
                            sinks.emitNext(doTask(data), Sinks.EmitFailureHandler.FAIL_FAST);
                            Logger.info("emitted {}", data);
                        }).start();
                        TimeUtils.sleep(100L);
                    } catch (Exception e) {
                    }
                });

        flux
                .publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(data -> Logger.info("map(): {}", data))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> Logger.info("onNext: {}", data));

        TimeUtils.sleep(200L);
    }

    private static String doTask(int taskNumber) {
        return "task " + taskNumber + " result";
    }
}
