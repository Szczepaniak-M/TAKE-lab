package pl.put.movieshop.category.web;

import pl.put.movieshop.common.web.ServerResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.put.movieshop.category.service.CategoryService;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    public Mono<ServerResponse> listCategories(ServerRequest serverRequest) {
        return categoryService.listCategories()
                              .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                              .doOnError(e -> log.error(e.getMessage(), e))
                              .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse());

    }
}
