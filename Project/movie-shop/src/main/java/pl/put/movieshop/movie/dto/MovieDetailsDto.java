package pl.put.movieshop.movie.dto;

import pl.put.movieshop.category.database.CategoryEntity;
import pl.put.movieshop.category.dto.CategoryDto;
import pl.put.movieshop.movie.database.MovieEntity;

public record MovieDetailsDto(Integer id,
                              String title,
                              CategoryDto category,
                              Integer year,
                              String description,
                              Double price) {

    public MovieDetailsDto(MovieEntity movieEntity, CategoryEntity category) {
        this(movieEntity.getId(),
                movieEntity.getTitle(),
                new CategoryDto(category),
                movieEntity.getYear(),
                movieEntity.getDescription(),
                movieEntity.getPrice());
    }
}
