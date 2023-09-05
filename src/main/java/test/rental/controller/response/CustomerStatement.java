package test.rental.controller.response;

public class CustomerStatement {
    private final String name;

    private final Amount rentAmount;

    private final int frequentRentalPoints;

    public CustomerStatement(String name, Amount rentAmount, int frequentRentalPoints) {
        this.name = name;
        this.rentAmount = rentAmount;
        this.frequentRentalPoints = frequentRentalPoints;
    }

    public Amount getRentAmount() {
        return rentAmount;
    }

    public int getFrequentRentalPoints() {
        return frequentRentalPoints;
    }

    public String getName() {
        return name;
    }
}
