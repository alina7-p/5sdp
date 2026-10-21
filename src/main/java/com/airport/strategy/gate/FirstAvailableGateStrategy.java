package airport.strategy.gate;

import java.util.List;
import airport.model.Gate;
import airport.model.Flight;

public class FirstAvailableGateStrategy implements GateAllocationStrategy {
    @Override
    public Gate allocateGate(Flight flight, List<Gate> gates) {
        for (Gate gate : gates) {
            if (!gate.isBusy()) {
                return gate;
            }
        }
        return null;
    }
}