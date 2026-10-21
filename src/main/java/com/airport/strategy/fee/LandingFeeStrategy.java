package airport.strategy.fee;

import airport.model.Flight;

public interface LandingFeeStrategy {
    double calculateFee(Flight flight);
}