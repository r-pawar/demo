package test.rental.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.rental.controller.response.Amount;
import test.rental.controller.response.CustomerStatement;
import test.rental.domain.Customer;
import test.rental.domain.Rental;
import test.rental.exception.CustomerNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerRentalService {

    @Autowired
    private MovieRentalStatementCalculatorService statementCalculator;

    private Map<String, Customer> customers = new HashMap<>();

    public Customer addRentalsForCustomer(final String customerName, final List<Rental> rentals) {
        final var customer = new Customer(customerName);
        rentals.forEach(customer::addRental);
        customers.put(customer.getName(), customer);
        log.info("Customer {} created with rentals.", customerName);
        return customer;
    }

    public CustomerStatement getStatement(final String name) {
        if (!customers.containsKey(name)) {
            throw new CustomerNotFoundException(name);
        }

        var customer = customers.get(name);
        var statement = statementCalculator.calculate(customer.getRentals());
        customer.setFrequentRentalPoints(statement.getFrequentRentalPoints());
        var customerStatement = new CustomerStatement(customer.getName(), new Amount(statement.getRentAmount()), statement.getFrequentRentalPoints());
        log.debug("Statement calculated for customer {}, rent amount: {}, points: {}",
                customerStatement.getName(),
                customerStatement.getRentAmount(),
                customerStatement.getFrequentRentalPoints()
        );
        return customerStatement;
    }
}
