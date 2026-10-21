package airport.external;

public class SimulatedWeatherService implements WeatherService {
    @Override
    public double getWindSpeedKnots() {
        return 18.5;
    }
}