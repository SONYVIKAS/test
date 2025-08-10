import java.util.Arrays;
import java.util.Collections;

public class Main {
    // Method to find grant cap
    public static double findGrantCap(Double[] grantsArray, double newBudget) {
        // Sort the array in reverse order
        Arrays.sort(grantsArray, Collections.reverseOrder());
        int i = grantsArray.length - 1;
        boolean flag = false;
        double cap = newBudget / grantsArray.length;
        newBudget = newBudget;

        // Loop until flag is true
        while (!flag) {
            // Loop until cap is greater than current grant
            while (cap > grantsArray[i]) {
                newBudget -= grantsArray[i];
                i -= 1;
            }

            double newCap = newBudget / (i + 1);

            // If cap is equal to newCap, set flag to true
            if (cap == newCap) {
                flag = true;
            }

            cap = newCap;
        }

        return cap;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        System.out.println(findGrantCap(new Double[]{2.0, 100.0, 50.0, 120.0, 1000.0}, 190)); // Expected output: 47
        System.out.println(findGrantCap(new Double[]{2.0, 4.0}, 3)); // Expected output: 1.5
        System.out.println(findGrantCap(new Double[]{2.0, 4.0, 6.0}, 3)); // Expected output: 1
        System.out.println(findGrantCap(new Double[]{2.0, 100.0, 50.0, 120.0, 1000.0}, 190)); // Expected output: 47
        System.out.println(findGrantCap(new Double[]{2.0, 100.0, 50.0, 120.0, 167.0}, 400)); // Expected output: 128
        System.out.println(findGrantCap(new Double[]{21.0, 100.0, 50.0, 120.0, 130.0, 110.0}, 140)); // Expected output: 23.8
        System.out.println(findGrantCap(new Double[]{210.0, 200.0, 150.0, 193.0, 130.0, 110.0, 209.0, 342.0, 117.0}, 1530)); // Expected output: 211
    }