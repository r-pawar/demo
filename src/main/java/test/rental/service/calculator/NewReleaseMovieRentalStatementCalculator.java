package test.rental.service.calculator;

import org.springframework.stereotype.Component;
import test.rental.domain.MovieType;

@Component
public class NewReleaseMovieRentalStatementCalculator implements StatementCalculator {
    @Override
    public double calculateRent(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int calculateFrequentRentalPoints(int daysRented) {
        if (daysRented > 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public MovieType getMovieType() {
        return MovieType.NEW_RELEASE;
    }
}
