package test.rental.exception;

import test.rental.domain.MovieType;

public class NoRentalCalculatorFoundForMovieTypeException extends RuntimeException {
    public NoRentalCalculatorFoundForMovieTypeException(MovieType movieType) {
        super(String.format("No rental calculator found for movie type %s.", movieType));
    }
}
