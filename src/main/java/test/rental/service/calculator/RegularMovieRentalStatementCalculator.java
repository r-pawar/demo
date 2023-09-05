package test.rental.service.calculator;

import org.springframework.stereotype.Component;
import test.rental.domain.MovieType;

@Component
public class RegularMovieRentalStatementCalculator implements StatementCalculator {
    @Override
    public double calculateRent(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

    @Override
    public int calculateFrequentRentalPoints(int daysRented) {
        return 0;
    }

    @Override
    public MovieType getMovieType() {
        return MovieType.REGULAR;
    }
}
