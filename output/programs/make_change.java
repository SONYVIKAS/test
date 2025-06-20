public class MakeChange {
    public static int makeChange(int amount, int[] denominations, int index) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || index == denominations.length) {
            return 0;
        }
        int currentCoin = denominations[index];
        int combos = 0;
        while (amount >= 0) {
            combos += makeChange(amount, denominations, index + 1);
            amount -= currentCoin;
        }
        return combos;
    }

    public static void main(String[] args) {
        int amount = 4;
        int[] denominations = {1, 2, 3};
        System.out.println(makeChange(amount, denominations, 0));

        amount = 20;
        denominations = new int[] {5, 10};
        System.out.println(makeChange(amount, denominations, 0));
    }