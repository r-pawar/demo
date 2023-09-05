package test.rental.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import test.rental.BaseUnitTest;
import test.rental.domain.Movie;
import test.rental.domain.MovieType;
import test.rental.domain.Rental;
import test.rental.exception.NoRentalCalculatorFoundForMovieTypeException;
import test.rental.service.calculator.StatementCalculator;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class MovieRentalStatementCalculatorServiceTest extends BaseUnitTest {

    private MovieRentalStatementCalculatorService service;

    @Mock
    private StatementCalculator newReleaseMovieStatementCalculator;

    @Mock
    private StatementCalculator regularMovieStatementCalculator;

    @BeforeEach
    public void setUp() {
        when(newReleaseMovieStatementCalculator.getMovieType()).thenReturn(MovieType.NEW_RELEASE);
        when(regularMovieStatementCalculator.getMovieType()).thenReturn(MovieType.REGULAR);
        service = new MovieRentalStatementCalculatorService(Arrays.asList(newReleaseMovieStatementCalculator, regularMovieStatementCalculator));
    }

    @Test
    public void calculatorService_without_statement_calculator_for_movie_type_should_throw_exception() {
        var rental = new Rental(new Movie("Wheels on the Bus", MovieType.CHILDREN), 2);

        var exception = assertThrows(
                NoRentalCalculatorFoundForMovieTypeException.class,
                () -> service.calculate(Collections.singletonList(rental)),
                "Calculate statement should throw exception"
        );

        assertEquals("No rental calculator found for movie type CHILDREN.", exception.getMessage());
    }

    @Test
    public void calculatorService_on_customer_rentals_should_return_customer_statement() {
        var newReleaseMovieRental = new Rental(new Movie("Harry Potter 10", MovieType.NEW_RELEASE), 3);
        var regularMovieRental = new Rental(new Movie("Old gold", MovieType.REGULAR), 5);

        when(newReleaseMovieStatementCalculator.calculateRent(3)).thenReturn(6.0);
        when(newReleaseMovieStatementCalculator.calculateFrequentRentalPoints(3)).thenReturn(1);
        when(regularMovieStatementCalculator.calculateRent(5)).thenReturn(7.5);
        when(regularMovieStatementCalculator.calculateFrequentRentalPoints(5)).thenReturn(0);

        var statement = service.calculate(Arrays.asList(newReleaseMovieRental, regularMovieRental));

        assertEquals(13.5, statement.getRentAmount());
        assertEquals(1, statement.getFrequentRentalPoints());
    }
}