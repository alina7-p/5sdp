package airport.strategy.gate;

import java.util.List;
import airport.model.Gate;
import airport.model.Flight;

public interface GateAllocationStrategy {
    Gate allocateGate(Flight flight, List<Gate> gates);
}