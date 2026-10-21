package airport.strategy.fee;

import airport.external.WeatherService;
import airport.model.Flight;

public class EcoNoiseFeeStrategy implements LandingFeeStrategy {
    private final WeatherService weatherService;

    public EcoNoiseFeeStrategy(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public double calculateFee(Flight flight) {
        double baseFee = flight.getWeightTons() * 12.0;
        double windSpeed = weatherService.getWindSpeedKnots();

        // Корректировка сбора при сильном ветре (высокий шумовой след)
        if (windSpeed > 15.0) {
            baseFee += 200.0;
        }
        return baseFee;
    }
}