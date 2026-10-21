package airport.strategy.fee;

import airport.model.Flight;

public class PeakHourFeeStrategy implements LandingFeeStrategy {
    private static final double BASE_RATE = 15.0;
    private static final double PEAK_SURCHARGE = 500.0;

    @Override
    public double calculateFee(Flight flight) {
        double fee = flight.getWeightTons() * BASE_RATE;
        if (flight.isPeakHour()) {
            fee += PEAK_SURCHARGE;
        }
        return fee;
    }
}