package me.ddongman.reactive.section10.chap1;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * Non-Debugging Mode
 */
public class DebuggingModeExample03 {
    private static Map<String, String> fluits = new HashMap<>();

    static {
        fluits.put("banana", "바나나");
        fluits.put("apple", "사과");
        fluits.put("peer", "배");
        fluits.put("grape", "포도");
    }

    public static void main(String[] args) {
        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEERS", "MELONS"})
                .map(String::toLowerCase)
                .map(fluit -> fluit.substring(0, fluit.length() - 1))
                .map(fluits::get)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
