package airport.selector;

import airport.model.Flight;
import airport.strategy.gate.*;

public class GateAllocationSelector {
    public GateAllocationStrategy selectStrategy(Flight flight) {
        if (flight.isDelayed()) {
            return new TurnaroundTimeOptimizationStrategy();
        } else if (flight.isWideBody()) {
            return new LargeAircraftMatchStrategy();
        } else {
            return new FirstAvailableGateStrategy();
        }
    }
}