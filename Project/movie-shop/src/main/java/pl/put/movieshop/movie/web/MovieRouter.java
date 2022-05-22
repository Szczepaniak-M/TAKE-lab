package pl.put.movieshop.movie.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static pl.put.movieshop.common.web.CommonRequestVariable.MOVIE_ID_PATH_PARAM;
import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovieRouter {

    public static final String MOVIE_BASE_URL = "/api/movies";
    public static final String MOVIE_DETAILS_URL = format("/api/movies/{%s}", MOVIE_ID_PATH_PARAM);

    @Bean
    RouterFunction<ServerResponse> movieRoutes(MovieController movieController) {
        return route(GET(MOVIE_BASE_URL).and(accept(APPLICATION_JSON)), movieController::listMovies)
                .andRoute(GET(MOVIE_DETAILS_URL).and(accept(APPLICATION_JSON)), movieController::getMovieById);
    }
}
