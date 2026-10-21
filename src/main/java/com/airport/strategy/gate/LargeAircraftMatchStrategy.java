package airport.strategy.gate;

import java.util.List;
import airport.model.Gate;
import airport.model.Flight;

public class LargeAircraftMatchStrategy implements GateAllocationStrategy {
    @Override
    public Gate allocateGate(Flight flight, List<Gate> gates) {
        return gates.stream()
                .filter(g -> !g.isBusy())
                .filter(g -> !flight.isWideBody() || g.hasDoubleJetBridge())
                .findFirst()
                .orElse(null);
    }
}