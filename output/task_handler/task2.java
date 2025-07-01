public class TruckLoadingContainer {
    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5};
        int capacity = 10;
        int currentWeight = 0;
        int currentContainer = 1;

        for (int i = 0; i < weights.length; i++) {
            if (currentWeight + weights[i] <= capacity) {
                currentWeight += weights[i];
            } else {
                currentContainer++;
                currentWeight = weights[i];
            }
        }

        System.out.println("Number of containers required: " + currentContainer);
    }