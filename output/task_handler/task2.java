public class TruckLoadingContainer {
    private final int capacity;
    private int currentLoad;
    private final String name;

    public TruckLoadingContainer(int capacity, String name) {
        this.capacity = capacity;
        this.currentLoad = 0;
        this.name = name;
    }

    public void load(int amount) {
        if (amount > 0 && currentLoad + amount <= capacity) {
            currentLoad += amount;
        } else {
            throw new IllegalArgumentException("Invalid amount to load");
        }
    }

    public void unload(int amount) {
        if (amount > 0 && currentLoad - amount >= 0) {
            currentLoad -= amount;
        } else {
            throw new IllegalArgumentException("Invalid amount to unload");
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public String getName() {
        return name;
    }