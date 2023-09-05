package test.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Customer {

    @Getter
    private final String name;

    @Getter
    @Setter
    private int frequentRentalPoints = 0;

    @JsonIgnore
    @Getter
    private List<Rental> rentals = new ArrayList<>();

    public void addRental(Rental rental) {
        rentals.add(rental);
    }
}
