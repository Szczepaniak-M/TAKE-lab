package pl.put.movieshop.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.put.movieshop.category.database.CategoryEntity;
import pl.put.movieshop.category.database.CategoryRepository;
import pl.put.movieshop.movie.database.MovieRepository;
import pl.put.movieshop.movie.dto.MovieDetailsDto;
import pl.put.movieshop.movie.dto.MovieListDto;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;

import static pl.put.movieshop.common.web.CommonRequestVariable.EMPTY_QUERY_PARAM;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public Mono<Page<MovieListDto>> listMovies(Integer categoryId, PageRequest pageRequest) {
        return Mono.just(categoryId)
                   .flatMap(category -> findMoviesAndCount(category, pageRequest))
                   .map(moviesAndCount -> new PageImpl<>(moviesAndCount.getT1(), pageRequest, moviesAndCount.getT2()));
    }

    public Mono<MovieDetailsDto> getMovieById(Integer movieId) {
        return movieRepository.findById(movieId)
                              .flatMap(movie -> categoryRepository.findById(movie.getCategory())
                                                                  .map(category -> Tuples.of(movie, category)))
                              .map(movieAndCategory -> new MovieDetailsDto(movieAndCategory.getT1(), movieAndCategory.getT2()));
    }

    private Mono<Tuple2<List<MovieListDto>, Long>> findMoviesAndCount(Integer categoryId, PageRequest pageRequest) {
        if (categoryId == EMPTY_QUERY_PARAM) {
            return movieRepository.findAllBy(pageRequest)
                                  .flatMap(movie -> categoryRepository.findById(movie.getCategory())
                                                                      .map(category -> new MovieListDto(movie, category)))
                                  .collectList()
                                  .zipWith(movieRepository.count());
        } else {
            Mono<CategoryEntity> category = categoryRepository.findById(categoryId);
            return movieRepository.findAllByCategory(categoryId, pageRequest)
                                  .flatMap(movie -> Mono.just(movie).zipWith(category))
                                  .map(movieAndCategory -> new MovieListDto(movieAndCategory.getT1(), movieAndCategory.getT2()))
                                  .collectList()
                                  .zipWith(movieRepository.countByCategory(categoryId));
        }
    }
}
