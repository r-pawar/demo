package test.rental.service.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.rental.BaseUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewReleaseMovieRentalStatementCalculatorTest extends BaseUnitTest {

    private NewReleaseMovieRentalStatementCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new NewReleaseMovieRentalStatementCalculator();
    }

    @Test
    public void calculateRent_for_new_released_movie_should_return_calculated_rent() {
        var rentAmount = calculator.calculateRent(3);

        assertEquals(9, rentAmount);
    }

    @Test
    public void calculateFrequentRentalPoint_for_new_released_movie_rented_equal_to_1_day_should_not_add_frequent_rental_points() {
        var frequentRentalPoints = calculator.calculateFrequentRentalPoints(1);

        assertEquals(0, frequentRentalPoints);
    }

    @Test
    public void calculateFrequentRentalPoint_for_new_released_movie_rented_more_than_1_day_should_add_1_frequent_rental_points() {
        var frequentRentalPoints = calculator.calculateFrequentRentalPoints(2);

        assertEquals(1, frequentRentalPoints);
    }
}