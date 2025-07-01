import java.util.Set;
import java.util.HashSet;

public class ChangeCombinations {
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