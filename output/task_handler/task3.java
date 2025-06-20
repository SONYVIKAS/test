public class CustomsCheck {
    public static void main(String[] args) {
        int[] itemWeights = {10, 20, 30, 40};
        int[] itemTypes = {1, 2, 3, 1};
        int totalWeight = calculateTotalWeight(itemWeights, itemTypes);

        if (totalWeight > 100) {
            System.out.println("Customs check failed: Total weight exceeds 100kg");
        } else {
            System.out.println("Customs check passed: Total weight is " + totalWeight + "kg");
        }
    }

    public static int calculateTotalWeight(int[] itemWeights, int[] itemTypes) {
        int totalWeight = 0;

        for (int i = 0; i < itemWeights.length; i++) {
            int weight = itemWeights[i];
            int type = itemTypes[i];

            if (type == 1) {
                weight *= 2;
            } else if (type == 2) {
                weight *= 3;
            } else if (type == 3) {
                weight *= 4;
            }

            totalWeight += weight;
        }

        return totalWeight;
    }