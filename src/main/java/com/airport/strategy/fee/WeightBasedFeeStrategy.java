package airport.strategy.fee;

import airport.model.Flight;

public class WeightBasedFeeStrategy implements LandingFeeStrategy {
    private static final double RATE_PER_TON = 15.0;

    @Override
    public double calculateFee(Flight flight) {
        return flight.getWeightTons() * RATE_PER_TON;
    }
}