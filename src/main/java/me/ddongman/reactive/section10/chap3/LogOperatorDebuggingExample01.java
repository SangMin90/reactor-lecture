package me.ddongman.reactive.section10.chap3;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * log operator()를 사용하여 디버깅
 * - log operator()는 Flux 또는 Mono에서 발생하는
 * - signal events(onNext, onError, onComplete, subscriptions, cancellations, requests)를 출력한다.
 */
public class LogOperatorDebuggingExample01 {
    private static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) {
        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
                .log()
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .map(fruits::get)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
