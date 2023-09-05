package test.rental.service.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.rental.BaseUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularMovieRentalStatementCalculatorTest extends BaseUnitTest {

    private RegularMovieRentalStatementCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new RegularMovieRentalStatementCalculator();
    }

    @Test
    public void calculateRent_for_regular_movie_on_rented_days_less_than_equal_to_2_should_add_base_rent() {
        var rentAmount = calculator.calculateRent(2);

        assertEquals(2, rentAmount);
    }

    @Test
    public void calculateRent_for_regular_movie_on_rented_days_greater_than_2_should_add_additional_surcharge_per_day_on_top() {
        var rentAmount = calculator.calculateRent(4);

        assertEquals(5, rentAmount);
    }

    @Test
    public void calculateFrequentRentalPoint_for_regular_movie_should_not_add_frequent_rental_points() {
        var frequentRentalPoints = calculator.calculateFrequentRentalPoints(2);

        assertEquals(0, frequentRentalPoints);
    }
}