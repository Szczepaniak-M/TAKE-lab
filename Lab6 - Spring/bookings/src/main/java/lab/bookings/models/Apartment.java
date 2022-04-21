package lab.bookings.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Min(value = 1)
    private int capacity;

    @OneToMany(mappedBy = "apartment")
    private List<Booking> bookings;

}
