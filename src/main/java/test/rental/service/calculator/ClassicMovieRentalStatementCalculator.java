package test.rental.service.calculator;

import org.springframework.stereotype.Component;
import test.rental.domain.MovieType;

@Component
public class ClassicMovieRentalStatementCalculator implements StatementCalculator {
    @Override
    public double calculateRent(int daysRented) {
        return daysRented;
    }

    @Override
    public int calculateFrequentRentalPoints(int daysRented) {
        return daysRented;
    }

    @Override
    public MovieType getMovieType() {
        return MovieType.CLASSIC;
    }
}
