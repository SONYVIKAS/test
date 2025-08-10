import java.util.*;

public class Main {

    // Function to compute the number of ways to make the amount of money with coins of the available denominations
    public static int makeChange(int amount, int[] denominations, int index) {
        // If amount is 0, return 1
        if (amount == 0) {
            return 1;
        }

        // If amount is less than 0, return 0
        if (amount < 0) {
            return 0;
        }

        // If index is equal to the length of denominations array, return 0
        if (index == denominations.length) {
            return 0;
        }

        // Get the current coin from the denominations array
        int currentCoin = denominations[index];

        // Initialize combos to 0
        int combos = 0;

        // While amount is greater than or equal to 0
        while (amount >= 0) {
            // Add the result of makeChange function to combos
            combos += makeChange(amount, denominations, index + 1);
            // Subtract currentCoin from amount
            amount -= currentCoin;
        }

        // Return combos
        return combos;
    }

    public static void main(String[] args) {
        // Test cases
        int[] denominations1 = {1, 2, 3};
        System.out.println(makeChange(4, denominations1, 0)); // Expected output: 4

        int[] denominations2 = {5, 10};
        System.out.println(makeChange(20, denominations2, 0)); // Expected output: 3
    }