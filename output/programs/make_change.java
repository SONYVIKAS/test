
public class MakeChange {

    // Function to compute the number of ways to make the amount with given denominations
    public static int makeChange(int amount, List<Integer> denominations, int index) {
        // Base case: if amount is zero, there's one way to make change (using no coins)
        if (amount == 0) {
            return 1;
        }

        // If amount is negative, no solution exists
        if (amount < 0) {
            return 0;
        }

        // If we've considered all denominations and still have amount left, no solution exists
        if (index == denominations.size()) {
            return 0;
        }

        int currentCoin = denominations.get(index);
        int combos = 0;

        // Try using the current coin and reduce the amount accordingly
        while (amount >= 0) {
            combos += makeChange(amount, denominations, index + 1);
            amount -= currentCoin;
        }

        return combos;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(makeChange(4, List.of(1, 2, 3), 0)); // Output: 4
        System.out.println(makeChange(20, List.of(5, 10), 0)); // Output: 3
    }