import java.util.Set;

public class Main {
    // Define the coins
    private static final int[] COINS = {1, 10};

    public static void main(String[] args) {
        // Test the functions
        System.out.println(coins(1).equals(Set.of(1, 10)));
        System.out.println(coins(2).equals(Set.of(2, 11, 20)));
        System.out.println(coins(3).equals(Set.of(3, 12, 21, 30)));
        System.out.println(coins(4).equals(Set.of(4, 13, 22, 31, 40)));
        System.out.println(coins(10).equals(Set.of(10, 19, 28, 37, 46, 55, 64, 73, 82, 91, 100)));
        System.out.println(coins2(1).equals(Set.of(1, 10)));
        System.out.println(coins2(2).equals(Set.of(2, 11, 20)));
        System.out.println(coins2(3).equals(Set.of(3, 12, 21, 30)));
        System.out.println(coins2(4).equals(Set.of(4, 13, 22, 31, 40)));
        System.out.println(coins2(10).equals(Set.of(10, 19, 28, 37, 46, 55, 64, 73, 82, 91, 100)));
    }

    // Function to find change from combinations of `num_coins` of dimes and pennies
    public static Set<Integer> coins(int numCoins) {
        Set<Integer> combos = new HashSet<>();
        coinsHelper(numCoins, combos, 0);
        return combos;
    }

    // Helper function to recursively find combinations
    private static void coinsHelper(int coinsLeft, Set<Integer> combos, int total) {
        if (coinsLeft == 0) {
            combos.add(total);
            return;
        }

        for (int coin : COINS) {
            coinsHelper(coinsLeft - 1, combos, total + coin);
        }
    }

    // Function to find change from combinations of `num_coins` of dimes and pennies
    public static Set<Integer> coins2(int numCoins) {
        Set<Integer> combos = new HashSet<>();
        coins2Helper(numCoins, combos, 0, 10, 1);
        return combos;
    }

    // Helper function to recursively find combinations
    private static void coins2Helper(int coinsLeft, Set<Integer> combos, int total, int dimes, int pennies) {
        if (coinsLeft == 0) {
            combos.add(total);
            return;
        }

        coins2Helper(coinsLeft - 1, combos, total + dimes, dimes, pennies);
        coins2Helper(coinsLeft - 1, combos, total + pennies, dimes, pennies);
    }