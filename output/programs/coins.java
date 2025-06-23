import java.util.Set;
import java.util.HashSet;

public class Coins {
    public static Set<Integer> coins(int numCoins) {
        Set<Integer> combos = new HashSet<>();
        int[] coins = {1, 10};

        _coins(numCoins, combos, 0);

        return combos;
    }

    public static void _coins(int coinsLeft, Set<Integer> combos, int total) {
        if (coinsLeft == 0) {
            combos.add(total);
            return;
        }

        for (int coin : coins) {
            _coins(coinsLeft - 1, combos, total + coin);
        }
    }