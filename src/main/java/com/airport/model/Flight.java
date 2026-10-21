package airport.model;

public class Flight {
    private String flightNumber;
    private double weightTons;
    private boolean isDelayed;
    private boolean isWideBody; // Требует двухэтажный телетрап
    private boolean isPeakHour;

    public Flight(String flightNumber, double weightTons, boolean isDelayed, boolean isWideBody, boolean isPeakHour) {
        this.flightNumber = flightNumber;
        this.weightTons = weightTons;
        this.isDelayed = isDelayed;
        this.isWideBody = isWideBody;
        this.isPeakHour = isPeakHour;
    }

    public String getFlightNumber() { return flightNumber; }
    public double getWeightTons() { return weightTons; }
    public boolean isDelayed() { return isDelayed; }
    public boolean isWideBody() { return isWideBody; }
    public boolean isPeakHour() { return isPeakHour; }
}