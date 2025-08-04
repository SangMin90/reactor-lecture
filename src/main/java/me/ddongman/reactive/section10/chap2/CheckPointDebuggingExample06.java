package me.ddongman.reactive.section10.chap2;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * checkpoint(description, true/false) Operator를 사용하여 디버깅
 * - description을 추가하고, 에러가 발생한 assembly 지점 또는 에러가 전파된 assembly 지점의 traceback도 추가한다.
 */
public class CheckPointDebuggingExample06 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("zipWith.checkpoint", true)
                .map(x -> x + 2)
                .checkpoint("map.checkpoint", true)
                .subscribe(Logger::onNext);
    }
}
