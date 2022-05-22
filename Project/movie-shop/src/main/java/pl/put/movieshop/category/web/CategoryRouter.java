package pl.put.movieshop.category.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CategoryRouter {

    public static final String CATEGORY_BASE_URL = "/api/categories";

    @Bean
    RouterFunction<ServerResponse> categoryRoutes(CategoryController categoryController) {
        return route(GET(CATEGORY_BASE_URL).and(accept(APPLICATION_JSON)), categoryController::listCategories);
    }
}
