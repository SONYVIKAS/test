public class TruckLoadingContainer {
    private final int maxWeight;
    private int currentWeight;
    private final List<String> items;

    public TruckLoadingContainer(int maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.items = new ArrayList<>();
    }

    public boolean addItem(String item, int weight) {
        if (currentWeight + weight <= maxWeight) {
            items.add(item);
            currentWeight += weight;
            return true;
        } else {
            return false;
        }
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public List<String> getItems() {
        return items;
    }