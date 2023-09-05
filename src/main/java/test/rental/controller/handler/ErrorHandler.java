package test.rental.controller.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import test.rental.exception.CustomerNotFoundException;
import test.rental.exception.NoRentalCalculatorFoundForMovieTypeException;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(NoRentalCalculatorFoundForMovieTypeException.class)
    public void handleNoRentalCalculatorFoundForMovieTypeException() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public void handleCustomerNotFoundException() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException() {
    }
}
