package test.rental.service.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import test.rental.BaseUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChildrenMovieRentalStatementCalculatorTest extends BaseUnitTest {

    private ChildrenMovieRentalStatementCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new ChildrenMovieRentalStatementCalculator();
    }

    @Test
    public void calculateRent_for_children_movie_on_rented_days_less_than_3_should_return_calculated_rent() {
        var rentAmount = calculator.calculateRent(3);

        assertEquals(1.5, rentAmount);
    }

    @Test
    public void calculateRent_for_children_movie_on_rented_days_greater_than_3_should_add_additional_surcharge_per_day() {
        var rentAmount = calculator.calculateRent(4);

        assertEquals(3, rentAmount);
    }

    @Test
    public void calculateFrequentRentalPoint_on_rented_days_should_not_add_frequent_rental_points() {
        var frequentRentalPoints = calculator.calculateFrequentRentalPoints(5);

        assertEquals(0, frequentRentalPoints);
    }
}