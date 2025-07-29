package me.ddongman.reactive.section5;

import me.ddongman.reactive.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotSequence {
    public static void main(String[] args) {
        Flux<String> hotSequence = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
                .delayElements(Duration.ofSeconds(1))
                .share(); // 원본 Flux를 여러 Subscriber가 공유한다.(cold to hot)
        
        hotSequence.subscribe(data -> System.out.println("Subscriber1 is watching " + data + "'s song."));

        TimeUtils.sleep(2500);

        hotSequence.subscribe(data -> System.out.println("Subscriber2 is watching " + data + "'s song."));

        TimeUtils.sleep(3000);
    }
}
