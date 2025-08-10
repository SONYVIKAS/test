import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AwardBudgetCuts {

    // Method to find the grant cap
    public static double findGrantCap(double[] grantsArray, double newBudget) {
        // Sort the grants array in descending order
        Arrays.sort(grantsArray);
        int n = grantsArray.length;
        double cap = newBudget / n;
        newBudget = (double) newBudget;
        int i = n - 1;
        boolean flag = false;

        // Loop until the cap stabilizes
        while (!flag) {
            // Adjust the budget and index for grants greater than the current cap
            while (i >= 0 && cap > grantsArray[i]) {
                newBudget -= grantsArray[i];
                i--;
            }

            // Calculate the new cap
            double newCap = newBudget / (i + 1);

            // Check if the cap has stabilized
            if (cap == newCap) {
                flag = true;
            }

            cap = newCap;
        }

        return cap;
    }

    // Unit tests
    public static class Testing {
        @Test
        public void testFindGrantCap() {
            assertEquals(47, findGrantCap(new double[]{2, 100, 50, 120, 1000}, 190), 0.1);
            assertEquals(1.5, findGrantCap(new double[]{2, 4}, 3), 0.1);
            assertEquals(1, findGrantCap(new double[]{2, 4, 6}, 3), 0.1);
            assertEquals(47, findGrantCap(new double[]{2, 100, 50, 120, 1000}, 190), 0.1);
            assertEquals(128, findGrantCap(new double[]{2, 100, 50, 120, 167}, 400), 0.1);
            assertEquals(23.8, findGrantCap(new double[]{21, 100, 50, 120, 130, 110}, 140), 0.1);
            assertEquals(211, findGrantCap(new double[]{210, 200, 150, 193, 130, 110, 209, 342, 117}, 1530), 0.1);
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("AwardBudgetCuts$Testing");
    }