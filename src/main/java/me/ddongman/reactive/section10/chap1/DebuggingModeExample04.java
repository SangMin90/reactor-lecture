package me.ddongman.reactive.section10.chap1;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.util.HashMap;
import java.util.Map;

/**
 * 디버깅 모드 사용 예제
 */
public class DebuggingModeExample04 {

    private static Map<String, String> fluits = new HashMap<>();

    static {
        fluits.put("banana", "바나나");
        fluits.put("apple", "사과");
        fluits.put("peer", "배");
        fluits.put("grape", "포도");
    }

    public static void main(String[] args) {

        Hooks.onOperatorDebug();

        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEERS", "MELONS"})
                .map(String::toLowerCase)
                .map(fluit -> fluit.substring(0, fluit.length() - 1))
                .map(fluits::get)
                .map(translated -> "맛있는 " + translated)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
