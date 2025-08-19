package me.ddongman.reactive.section11;

import reactor.core.publisher.Mono;

public class PublisherProbeExample {
    public static Mono<String> useStandbyPower() {
        return Mono.just("# use Standby Power");
    }

    public static Mono<String> useMainPower() {
        return Mono.empty();
    }

    public static Mono<?> processWith(Mono<String> main, Mono<String> standby) {
        return main
                .flatMap(Mono::just)
                .switchIfEmpty(standby);
    }
}
