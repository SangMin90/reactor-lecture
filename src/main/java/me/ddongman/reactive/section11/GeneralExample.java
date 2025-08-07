package me.ddongman.reactive.section11;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

public class GeneralExample {

    public static Flux<String> sayHelloReactor() {
        return Flux.just("Hello", "Reactor");
    }

    public static Flux<Integer> occurError(Flux<Integer> source) {
        return source
                .zipWith(Flux.just(2, 2, 2, 2, 0), (x, y) -> x / y);
    }

    public static Flux<Integer> divideByTwo(Flux<Integer> source) {
        return source
                .zipWith(Flux.just(2, 2, 2, 2, 2), (x, y) -> x / y);
    }

    public static Flux<Integer> takeNumber(Flux<Integer> source, int n) {
        return source.take(n);
    }

    public static Flux<Tuple2<String, Integer>> getVoteCount(Flux<Long> source) {
        return source
                .zipWith(Flux.just(
                        Tuples.of("중구", 15400),
                        Tuples.of("서초구", 20020),
                        Tuples.of("강서구", 32040),
                        Tuples.of("강동구", 14506),
                        Tuples.of("서대문구", 35650)
                ))
                .map(Tuple2::getT2);
    }
}
