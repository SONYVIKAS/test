public class MakeChange {
  public static int makeChange(int amount, int[] denominations, int index) {
    if (amount == 0) {
      return 1;
    }
    if (amount < 0 || index == denominations.length) {
      return 0;
    }
    int currentCoin = denominations[index];
    int combinations = 0;
    while (amount >= 0) {
      combinations += makeChange(amount, denominations, index + 1);
      amount -= currentCoin;
    }
    return combinations;
  }