package airport;

import airport.context.GateManagerContext;
import airport.external.*;
import airport.model.*;
import airport.selector.GateAllocationSelector;
import airport.strategy.fee.*;
import airport.strategy.gate.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Gate> gates = List.of(
                new Gate("Gate-A1", false, false, 500),
                new Gate("Gate-B2", false, true, 150)
        );

        Flight flight = new Flight("KC-991", 120.0, true, true, true);

        System.out.println("=== 1. GATE ALLOCATION FAMILY ===");

        GateManagerContext gateContext = new GateManagerContext(new FirstAvailableGateStrategy());
        Gate selectedGate1 = gateContext.assignGate(flight, gates);
        System.out.println("First Available Strategy selected: " + (selectedGate1 != null ? selectedGate1.getId() : "None"));

        gateContext.setStrategy(new TurnaroundTimeOptimizationStrategy());
        Gate selectedGate2 = gateContext.assignGate(flight, gates);
        System.out.println("Turnaround Optimization Strategy selected: " + (selectedGate2 != null ? selectedGate2.getId() : "None"));

        GateAllocationSelector selector = new GateAllocationSelector();
        GateAllocationStrategy autoStrategy = selector.selectStrategy(flight);
        gateContext.setStrategy(autoStrategy);
        Gate autoGate = gateContext.assignGate(flight, gates);
        System.out.println("Selector automatically assigned: " + (autoGate != null ? autoGate.getId() : "None"));


        System.out.println("\n=== 2. LANDING FEE FAMILY ===");

        WeatherService weatherService = new SimulatedWeatherService();

        LandingFeeStrategy weightFee = new WeightBasedFeeStrategy();
        LandingFeeStrategy peakFee = new PeakHourFeeStrategy();
        LandingFeeStrategy ecoFee = new EcoNoiseFeeStrategy(weatherService);

        System.out.println("Standard Weight Fee: $" + weightFee.calculateFee(flight));
        System.out.println("Peak Hour Surcharge Fee: $" + peakFee.calculateFee(flight));
        System.out.println("Eco/Noise Wind Fee: $" + ecoFee.calculateFee(flight));
    }
}