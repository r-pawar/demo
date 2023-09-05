package test.rental.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super(String.format("No customer found for name %s.", customerId));
    }
}
