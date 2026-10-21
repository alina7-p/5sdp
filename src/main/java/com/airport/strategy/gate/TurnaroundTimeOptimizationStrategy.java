package airport.strategy.gate;

import java.util.Comparator;
import java.util.List;
import airport.model.Gate;
import airport.model.Flight;

public class TurnaroundTimeOptimizationStrategy implements GateAllocationStrategy {
    @Override
    public Gate allocateGate(Flight flight, List<Gate> gates) {
        return gates.stream()
                .filter(g -> !g.isBusy())
                .min(Comparator.comparingInt(Gate::getDistanceToRunwayMeters))
                .orElse(null);
    }
}