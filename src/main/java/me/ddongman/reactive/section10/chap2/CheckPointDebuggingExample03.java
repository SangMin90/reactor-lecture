package me.ddongman.reactive.section10.chap2;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * checkpoint() Operator를 사용하여 디버깅
 * - 에러가 예상되는 지점에 checkpoint()를 추가하여 assembly traceback을 확인한다.
 */
public class CheckPointDebuggingExample03 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint()
                .map(x -> x + 2)
                .checkpoint()
                .subscribe(Logger::onNext);
    }
}
