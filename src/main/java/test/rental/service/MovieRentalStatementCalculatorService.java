package test.rental.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.rental.domain.MovieType;
import test.rental.domain.Rental;
import test.rental.exception.NoRentalCalculatorFoundForMovieTypeException;
import test.rental.service.calculator.StatementCalculator;
import test.rental.service.model.RentalStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MovieRentalStatementCalculatorService {

    private final Map<MovieType, StatementCalculator> calculatorsForMovieType = new HashMap<>();

    @Autowired
    public MovieRentalStatementCalculatorService(List<StatementCalculator> calculatorList) {
        calculatorList.forEach(
                calculator -> calculatorsForMovieType.put(calculator.getMovieType(), calculator)
        );
    }

    public RentalStatement calculate(final List<Rental> rentals) {
        var rentAmount = 0.0;
        var frequentRentalPoints = 0;

        for (Rental rental : rentals) {
            if (!calculatorsForMovieType.containsKey(rental.getMovie().getMovieType())) {
                throw new NoRentalCalculatorFoundForMovieTypeException(rental.getMovie().getMovieType());
            }

            var calculator = calculatorsForMovieType.get(rental.getMovie().getMovieType());
            rentAmount += calculator.calculateRent(rental.getDaysRented());
            frequentRentalPoints += calculator.calculateFrequentRentalPoints(rental.getDaysRented());
        }

        return new RentalStatement(rentAmount, frequentRentalPoints);
    }
}
