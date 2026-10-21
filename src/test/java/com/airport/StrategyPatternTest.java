package airport;

import airport.external.WeatherService;
import airport.model.*;
import airport.selector.GateAllocationSelector;
import airport.strategy.fee.*;
import airport.strategy.gate.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StrategyPatternTest {

    private List<Gate> gates;

    @BeforeEach
    void setUp() {
        gates = List.of(
                new Gate("Gate-A1", false, false, 500),
                new Gate("Gate-A2", false, true, 200),
                new Gate("Gate-A3", true, true, 100)
        );
    }

    @Test
    @DisplayName("FirstAvailableGateStrategy selects the first unassigned gate")
    void testFirstAvailableStrategy() {
        GateAllocationStrategy strategy = new FirstAvailableGateStrategy();
        Flight flight = new Flight("KC101", 50, false, false, false);

        Gate result = strategy.allocateGate(flight, gates);

        assertNotNull(result);
        assertEquals("Gate-A1", result.getId());
    }

    @Test
    @DisplayName("TurnaroundTimeOptimizationStrategy selects the closest available gate to runway")
    void testTurnaroundTimeOptimizationStrategy() {
        GateAllocationStrategy strategy = new TurnaroundTimeOptimizationStrategy();
        Flight flight = new Flight("KC102", 50, true, false, false);

        Gate result = strategy.allocateGate(flight, gates);

        assertNotNull(result);
        assertEquals("Gate-A2", result.getId());
    }

    @Test
    @DisplayName("LargeAircraftMatchStrategy requires double jet bridge for wide-body planes")
    void testLargeAircraftMatchStrategy() {
        GateAllocationStrategy strategy = new LargeAircraftMatchStrategy();
        Flight wideBodyFlight = new Flight("KC103", 150, false, true, false);

        Gate result = strategy.allocateGate(wideBodyFlight, gates);

        assertNotNull(result);
        assertEquals("Gate-A2", result.getId());
    }

    @Test
    @DisplayName("Selector selects Turnaround strategy for delayed flights")
    void testSelectorForDelayedFlight() {
        GateAllocationSelector selector = new GateAllocationSelector();
        Flight delayedFlight = new Flight("KC104", 60, true, false, false);

        GateAllocationStrategy selectedStrategy = selector.selectStrategy(delayedFlight);

        assertTrue(selectedStrategy instanceof TurnaroundTimeOptimizationStrategy);
    }

    @Test
    @DisplayName("EcoNoiseFeeStrategy correctly uses fake external WeatherService data")
    void testEcoNoiseFeeWithFakeWeather() {
        WeatherService fakeWeather = () -> 20.0;
        LandingFeeStrategy strategy = new EcoNoiseFeeStrategy(fakeWeather);
        Flight flight = new Flight("KC105", 100, false, false, false);

        double fee = strategy.calculateFee(flight);

        assertEquals(1400.0, fee);
    }

    static Stream<LandingFeeStrategy> feeStrategyProvider() {
        WeatherService fakeWeather = () -> 10.0;
        return Stream.of(
                new WeightBasedFeeStrategy(),
                new PeakHourFeeStrategy(),
                new EcoNoiseFeeStrategy(fakeWeather)
        );
    }

    @ParameterizedTest
    @MethodSource("feeStrategyProvider")
    @DisplayName("All landing fee strategies return non-negative fees")
    void testFeeIsAlwaysNonNegative(LandingFeeStrategy strategy) {
        Flight flight = new Flight("KC106", 80, false, false, true);

        double fee = strategy.calculateFee(flight);

        assertTrue(fee >= 0, "Fee should never be negative");
    }
}