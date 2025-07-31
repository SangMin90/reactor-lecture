package me.ddongman.reactive.section9.chap3;

import me.ddongman.reactive.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * Context 특징
 * - 직교성을 가지는 정보를 표현할 때 주로 사용된다.
 */
public class ContextRealExample01 {

    private static final String HEADER_NAME_AUTH_TOKEN = "authToken";

    public static void main(String[] args) {
        Mono<String> mono = postBook(Mono.just(
                new Book("abcd-1111-3533-2809", "Reactor's bible", "Kevin")
        ))
                .contextWrite(Context.of(HEADER_NAME_AUTH_TOKEN, "eafjkjdoiajvdkaljklfdaf"));

        mono.subscribe(Logger::onNext);
    }

    private static Mono<String> postBook(Mono<Book> book) {
        return Mono.zip(book, Mono.deferContextual(
                            ctx -> Mono.just(ctx.get(HEADER_NAME_AUTH_TOKEN))
                        )
                )
                .flatMap(Mono::just) // 외부 API 서버로 HTTP POST 요청을 전송한다고 가정
                .flatMap(tuple -> {
                    String response = "POST the book(" + tuple.getT1().getBookName()
                            + ", " + tuple.getT1().getAuthor() + ") with token: "
                            + tuple.getT2();

                    return Mono.just(response);
                });
    }
}
