import java.util.Set;
import java.util.HashSet;

public class Coins {
    public static Set<Integer> coins(int numCoins) {
        Set<Integer> combos = new HashSet<Integer>();
        int[] coins = {1, 10};

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

    public static Set<Integer> coins2(int numCoins) {
        Set<Integer> combos = new HashSet<Integer>();
        int dimes = 10;
        int pennies = 1;

        _coins2(numCoins, combos, 0, dimes, pennies);

        return combos;
    }

    private static void _coins2(int coinsLeft, Set<Integer> combos, int total, int dimes, int pennies) {
        if (coinsLeft == 0) {
            combos.add(total);
            return;
        }

        _coins2(coinsLeft - 1, combos, total + dimes, dimes, pennies);
        _coins2(coinsLeft - 1, combos, total + pennies, dimes, pennies);
    }