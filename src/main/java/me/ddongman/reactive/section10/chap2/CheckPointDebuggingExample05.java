package me.ddongman.reactive.section10.chap2;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * checkpoint(description)를 사용하여 디버깅
 * - description을 추가해서 에러가 발생한 지점을 구분할 수 있다.
 * - description을 추가할 경우, 에러가 발생한 assembly 지점의 trackback을 표시하지 않는다.
 */
public class CheckPointDebuggingExample05 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("zipWith.checkpoint")
                .map(x -> x + 2)
                .checkpoint("map.checkpoint")
                .subscribe(Logger::onNext, Logger::onError);
    }
}
