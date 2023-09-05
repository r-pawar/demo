package test.rental.service.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.rental.BaseUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassicMovieRentalStatementCalculatorTest extends BaseUnitTest {

    private ClassicMovieRentalStatementCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new ClassicMovieRentalStatementCalculator();
    }

    @Test
    public void calculateRent_for_classic_movie_should_return_calculated_rent() {
        var rentAmount = calculator.calculateRent(3);

        assertEquals(3, rentAmount);
    }

    @Test
    public void calculateFrequentRentalPoint_for_classic_movie_should_frequent_rental_points_equal_to_no_of_Days() {
        var frequentRentalPoints = calculator.calculateFrequentRentalPoints(2);

        assertEquals(2, frequentRentalPoints);
    }
}