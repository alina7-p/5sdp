package airport.model;

public class Gate {
    private String id;
    private boolean isBusy;
    private boolean hasDoubleJetBridge;
    private int distanceToRunwayMeters;

    public Gate(String id, boolean isBusy, boolean hasDoubleJetBridge, int distanceToRunwayMeters) {
        this.id = id;
        this.isBusy = isBusy;
        this.hasDoubleJetBridge = hasDoubleJetBridge;
        this.distanceToRunwayMeters = distanceToRunwayMeters;
    }

    public String getId() { return id; }
    public boolean isBusy() { return isBusy; }
    public boolean hasDoubleJetBridge() { return hasDoubleJetBridge; }
    public int getDistanceToRunwayMeters() { return distanceToRunwayMeters; }
}