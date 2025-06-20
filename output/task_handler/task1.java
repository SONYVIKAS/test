public class Vessel {
    private double capacity;
    private double currentLoad;

    public Vessel(double capacity) {
        this.capacity = capacity;
        this.currentLoad = 0;
    }

    public void unload(double amount) {
        if (amount > capacity - currentLoad) {
            throw new IllegalArgumentException("Amount to unload exceeds capacity");
        }
        currentLoad -= amount;
    }