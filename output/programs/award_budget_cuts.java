import java.util.Collections;

public class Main {

    public static double findGrantCap(Double[] grantsArray, double newBudget) {
        // Sort the array in reverse order
        Arrays.sort(grantsArray, Collections.reverseOrder());
        int i = grantsArray.length - 1;
        boolean flag = false;
        double cap = newBudget / grantsArray.length;
        newBudget = newBudget;

        // Loop until the flag is set to true
        while (!flag) {

            // If the cap is greater than the current grant, subtract the grant from the budget and decrement i
            while (cap > grantsArray[i]) {
                newBudget -= grantsArray[i];
                i -= 1;
            }

            // Calculate the new cap
            double newCap = newBudget / (i + 1);

            // If the cap is equal to the new cap, set the flag to true
            if (cap == newCap) {
                flag = true;
            }

            // Update the cap
            cap = newCap;
        }

        // Return the cap
        return cap;
    }

    public static void main(String[] args) {
        System.out.println(findGrantCap(new Double[]{2.0, 100.0, 50.0, 120.0, 1000.0}, 190.0)); // Expected output: 47.0
        System.out.println(findGrantCap(new Double[]{2.0, 4.0}, 3.0)); // Expected output: 1.5
        System.out.println(findGrantCap(new Double[]{2.0, 4.0, 6.0}, 3.0)); // Expected output: 1.0
        System.out.println(findGrantCap(new Double[]{2.0, 100.0, 50.0, 120.0, 167.0}, 400.0)); // Expected output: 128.0
        System.out.println(findGrantCap(new Double[]{21.0, 100.0, 50.0, 120.0, 130.0, 110.0}, 140.0)); // Expected output: 23.8
        System.out.println(findGrantCap(new Double[]{210.0, 200.0, 150.0, 193.0, 130.0, 110.0, 209.0, 342.0, 117.0}, 1530.0)); // Expected output: 211.0
    }