package pl.put.movieshop.common.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerResponseFactory {

    public static Mono<ServerResponse> createHttpSuccessResponse(Object payload) {
        return ServerResponse.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(ResponseTemplate.builder()
                                                                           .status(HttpStatus.OK.value())
                                                                           .payload(payload)
                                                                           .build()));
    }

    public static Mono<ServerResponse> createHttpBadRequestCantParseErrorResponse() {
        String message = "bad.request;cannot.parse.parameter";
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(ResponseTemplate.builder()
                                                                           .status(HttpStatus.BAD_REQUEST.value())
                                                                           .message(message)
                                                                           .build()));
    }

    public static Mono<ServerResponse> createHttpNotFoundResponse(NoSuchElementException e) {
        return ServerResponse.status(HttpStatus.NOT_FOUND)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(ResponseTemplate.builder()
                                                                           .status(HttpStatus.NOT_FOUND.value())
                                                                           .message(e.getMessage() + ".not.found")
                                                                           .build()));
    }

    public static Mono<ServerResponse> createHttpInternalServerErrorResponse() {
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(ResponseTemplate.builder()
                                                                           .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                                           .message("internal.server.error")
                                                                           .build()));
    }
}
