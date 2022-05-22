package pl.put.movieshop.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.movieshop.category.database.CategoryRepository;
import pl.put.movieshop.category.dto.CategoryDto;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Mono<List<CategoryDto>> listCategories() {
        return categoryRepository.findAll()
                                 .map(CategoryDto::new)
                                 .collectList();
    }
}
