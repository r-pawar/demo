package test.rental.controller.response;

public class Amount {

    private final double amount;

    private final String currencyCode = "USD";

    public Amount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
