package pl.put.movieshop.movie.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("movie")
public class MovieEntity {
    @Id
    private Integer id;
    private String title;
    private Integer category;
    private Integer year;
    private String description;
    private Double price;
}
