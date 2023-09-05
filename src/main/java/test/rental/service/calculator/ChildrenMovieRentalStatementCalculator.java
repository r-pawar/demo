package test.rental.service.calculator;

import org.springframework.stereotype.Component;
import test.rental.domain.MovieType;

@Component
public class ChildrenMovieRentalStatementCalculator implements StatementCalculator {
    @Override
    public double calculateRent(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented- 3) * 1.5;
        return amount;
    }

    @Override
    public int calculateFrequentRentalPoints(int daysRented) {
        return 0;
    }

    @Override
    public MovieType getMovieType() {
        return MovieType.CHILDREN;
    }
}
