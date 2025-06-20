public class TruckLoadingContainer {
    public static void main(String[] args) {
        int[] weights = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int capacity = 200;

        int totalWeight = 0;
        int containers = 0;

        for (int i = 0; i < weights.length; i++) {
            if (totalWeight + weights[i] <= capacity) {
                totalWeight += weights[i];
            } else {
                containers++;
                totalWeight = weights[i];
            }
        }

        containers++;

        System.out.println("Number of containers required: " + containers);
    }