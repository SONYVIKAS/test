import java.util.Set;

public class Coins {

    // Method to find change from combinations of `numCoins` of dimes and pennies
    public static Set<Integer> coins(int numCoins) {
        Set<Integer> combos = new HashSet<>();
        int[] coins = {1, 10};

        // Helper method to recursively calculate combinations
        _coins(numCoins, combos, 0, coins);
        return combos;
    }

    private static void _coins(int coinsLeft, Set<Integer> combos, int total, int[] coins) {
        if (coinsLeft == 0) {
            combos.add(total);
            return;
        }

        for (int coin : coins) {
            _coins(coinsLeft - 1, combos, total + coin, coins);
        }
    }

    // Another method to find change from combinations of `numCoins` of dimes and pennies
    public static Set<Integer> coins_2(int numCoins) {
        Set<Integer> combos = new HashSet<>();
        int dimes = 10;
        int pennies = 1;

        // Helper method to recursively calculate combinations
        _coins_2(numCoins, combos, 0, dimes, pennies);
        return combos;
    }

    private static void _coins_2(int coinsLeft, Set<Integer> combos, int total, int dimes, int pennies) {
        if (coinsLeft == 0) {
            combos.add(total);
            return;
        }

        _coins_2(coinsLeft - 1, combos, total + dimes, dimes, pennies);
        _coins_2(coinsLeft - 1, combos, total + pennies, dimes, pennies);
    }

    public static void main(String[] args) {
        // Test cases
        assert coins(1).equals(Set.of(1, 10));
        assert coins(2).equals(Set.of(2, 11, 20));
        assert coins(3).equals(Set.of(3, 12, 21, 30));
        assert coins(4).equals(Set.of(4, 13, 22, 31, 40));
        assert coins(10).equals(Set.of(10, 19, 28, 37, 46, 55, 64, 73, 82, 91, 100));

        assert coins_2(1).equals(Set.of(1, 10));
        assert coins_2(2).equals(Set.of(2, 11, 20));
        assert coins_2(3).equals(Set.of(3, 12, 21, 30));
        assert coins_2(4).equals(Set.of(4, 13, 22, 31, 40));
        assert coins_2(10).equals(Set.of(10, 19, 28, 37, 46, 55, 64, 73, 82, 91, 100));

        System.out.println("ALL TESTS PASSED!");
    }