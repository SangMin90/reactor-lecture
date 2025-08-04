package me.ddongman.reactive.section10.chap2;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * checkpoint() Operator를 이용한 디버깅 예제
 * - 에러가 에상되는 assembly 지점에 checkpoint()를 사용해서 에러 발생 지점을 확인할 수 있다.
 * - checkpoint()는 에러 발생 시, traceback이 추가된다.
 */
public class CheckPointDebuggingExample01 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint()
                .subscribe(Logger::onNext);
    }
}
