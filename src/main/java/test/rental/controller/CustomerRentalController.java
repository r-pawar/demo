package test.rental.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.rental.controller.response.CustomerStatement;
import test.rental.domain.Customer;
import test.rental.domain.Rental;
import test.rental.service.CustomerRentalService;

import java.util.List;

@Validated
@RestController
@Slf4j
public class CustomerRentalController {

    @Autowired
    private CustomerRentalService customerRentalService;

    @PostMapping(value = "/customers/{name}/rentals", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Customer addRentals(
            @NotBlank @PathVariable("name") String customerName,
            @Valid @RequestBody List<Rental> rentals
    ) {
        return customerRentalService.addRentalsForCustomer(customerName, rentals);
    }

    @GetMapping(value = "/customers/{name}/rentals/statements", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CustomerStatement getRentalStatement(
            @NotBlank @PathVariable("name") String customerName
    ) {
        return customerRentalService.getStatement(customerName);
    }
}
