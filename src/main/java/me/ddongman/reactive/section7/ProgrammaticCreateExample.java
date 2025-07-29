package me.ddongman.reactive.section7;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/**
 * create Operator() 를 사용하는 예제
 * - 일반적으로 Publisher의 데이터 생성을 단일 스레드에서 진행한다. 멀티 스레드에서도 가능
 * - 데이터 emit은 create Operator 내부에 가능
 * - Backpressure 적용 가능
 */
public class ProgrammaticCreateExample {
    public static void main(String[] args) {
        int tasks = 6;
        Flux
                .create((FluxSink<String> sink) -> {
                    IntStream
                            .range(0, tasks)
                            .forEach(i -> sink.next(doTask(i)));
                })
                .subscribeOn(Schedulers.boundedElastic())   // 쓰레드풀???
                .doOnNext(data -> Logger.info("create(): {}", data))
                .publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(data -> Logger.info("map(): {}", data))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> Logger.info("onNext: {}", data));

        TimeUtils.sleep(500L);
    }

    private static String doTask(int taskNumber) {
        return "task " + taskNumber + " result";
    }
}
