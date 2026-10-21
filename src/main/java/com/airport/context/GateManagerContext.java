package airport.context;

import java.util.List;
import airport.model.Flight;
import airport.model.Gate;
import airport.strategy.gate.GateAllocationStrategy;

public class GateManagerContext {
    private GateAllocationStrategy strategy;

    public GateManagerContext(GateAllocationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(GateAllocationStrategy strategy) {
        this.strategy = strategy;
    }

    public Gate assignGate(Flight flight, List<Gate> gates) {
        return strategy.allocateGate(flight, gates);
    }
}