package airport;

import airport.model.Flight;
import airport.model.Gate;
import java.util.List;

public class BadGateManager {
    public Gate allocateGate(String type, Flight flight, List<Gate> gates) {
        switch (type) {
            case "FIRST":
                for (Gate g : gates) {
                    if (!g.isBusy()) return g;
                }
                return null;
            case "TURNAROUND":
                Gate best = null;
                for (Gate g : gates) {
                    if (!g.isBusy() && (best == null || g.getDistanceToRunwayMeters() < best.getDistanceToRunwayMeters())) {
                        best = g;
                    }
                }
                return best;
            default:
                return null;
        }
    }
}