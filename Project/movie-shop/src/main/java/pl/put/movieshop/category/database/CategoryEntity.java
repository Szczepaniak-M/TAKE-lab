package pl.put.movieshop.category.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("category")
public class CategoryEntity {
    @Id
    private Integer id;
    private String name;
}
