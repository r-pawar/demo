package test.rental.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Rental {

    @Valid
    private Movie movie;

    @Min(1)
    private int daysRented;
}
