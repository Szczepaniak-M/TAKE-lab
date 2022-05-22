package pl.put.movieshop.movie.dto;

import pl.put.movieshop.category.database.CategoryEntity;
import pl.put.movieshop.category.dto.CategoryDto;
import pl.put.movieshop.movie.database.MovieEntity;

public record MovieListDto(Integer id,
                           String title,
                           CategoryDto category,
                           Integer year,
                           Double price) {

    public MovieListDto(MovieEntity movieEntity, CategoryEntity categoryEntity) {
        this(movieEntity.getId(),
                movieEntity.getTitle(),
                new CategoryDto(categoryEntity),
                movieEntity.getYear(),
                movieEntity.getPrice());
    }
}
