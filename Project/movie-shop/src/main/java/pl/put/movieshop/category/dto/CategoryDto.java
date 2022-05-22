package pl.put.movieshop.category.dto;

import pl.put.movieshop.category.database.CategoryEntity;


public record CategoryDto(Integer id,
                          String name) {

    public CategoryDto(CategoryEntity category) {
        this(category.getId(), category.getName());
    }
}
