package test.rental.service.calculator;

import test.rental.domain.MovieType;

public interface StatementCalculator {

    double calculateRent(final int daysRented);

    int calculateFrequentRentalPoints(final int daysRented);

    MovieType getMovieType();
}
