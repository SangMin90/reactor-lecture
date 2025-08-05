package me.ddongman.reactive.section10.chap3;

import me.ddongman.reactive.utils.Logger;
import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

/**
 * log() operator를 사용하여 디버깅
 * - Custom Category를 입력해서 Operator마다 출력되는 signal event를 구분할 수 있다.
 */
public class LogOperatorDebuggingExample03 {

    private static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) {
        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
                .subscribeOn(Schedulers.boundedElastic())
                .log("fruit.source")
                .publishOn(Schedulers.parallel())
                .map(String::toLowerCase)
                .log("fruit.lower")
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .log("fruit.substring")
                .map(fruits::get)
                .log("fruit.name")
                .subscribe(Logger::onNext, Logger::onError);

        TimeUtils.sleep(100L);
    }
}
