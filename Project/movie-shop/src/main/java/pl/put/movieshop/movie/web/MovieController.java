package pl.put.movieshop.movie.web;

import pl.put.movieshop.common.web.ServerResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.put.movieshop.movie.service.MovieService;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

import static pl.put.movieshop.common.web.CommonRequestVariable.CATEGORY_QUERY_PARAM;
import static pl.put.movieshop.common.web.CommonRequestVariable.MOVIE_ID_PATH_PARAM;
import static pl.put.movieshop.common.web.ControllerUtils.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public Mono<ServerResponse> listMovies(ServerRequest serverRequest) {
        return withQueryParam(serverRequest, CATEGORY_QUERY_PARAM,
                category -> withPageParams(serverRequest,
                        pageRequest -> movieService.listMovies(category, pageRequest)
                                                   .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                                                   .doOnError(e -> log.error(e.getMessage(), e))
                                                   .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse())));
    }

    public Mono<ServerResponse> getMovieById(ServerRequest serverRequest) {
        return withPathParam(serverRequest, MOVIE_ID_PATH_PARAM,
                movieId -> movieService.getMovieById(movieId)
                                          .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                                          .onErrorResume(NoSuchElementException.class, ServerResponseFactory::createHttpNotFoundResponse)
                                          .doOnError(e -> log.error(e.getMessage(), e))
                                          .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse()));
    }
}
