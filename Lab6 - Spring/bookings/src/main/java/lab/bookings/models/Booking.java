package lab.bookings.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Apartment apartment;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;

    private LocalDate toDate;

    @Min(value = 1)
    @Max(value = 366)
    private Integer numDays = 1;

    @Min(value = 1)
    private Integer numGuests = 1;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @PrePersist
    private void onPrePersist() {
        toDate = fromDate.plusDays(numDays);
    }

    public LocalDate getToDate() {
        return fromDate.plusDays(numDays);
    }
}