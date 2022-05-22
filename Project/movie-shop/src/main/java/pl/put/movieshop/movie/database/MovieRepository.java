package pl.put.movieshop.movie.database;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface MovieRepository extends R2dbcRepository<MovieEntity, Integer> {
    Flux<MovieEntity> findAllBy(Pageable pageable);
    Flux<MovieEntity> findAllByCategory(int category, Pageable pageable);
    Mono<Long> countByCategory(int category);
}
