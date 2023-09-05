package test.rental.service.model;

public class RentalStatement {
    private final double rentAmount;

    private final int frequentRentalPoints;

    public RentalStatement(double rentAmount, int frequentRentalPoints) {
        this.rentAmount = rentAmount;
        this.frequentRentalPoints = frequentRentalPoints;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public int getFrequentRentalPoints() {
        return frequentRentalPoints;
    }
}
