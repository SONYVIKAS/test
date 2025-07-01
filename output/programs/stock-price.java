  public class StockPrice {
      public static int getMaximumProfit(int[] prices) {
          int maxProfit = prices[1] - prices[0];
          for (int a = 0; a < prices.length; a++) {
              for (int b = a + 1; b < prices.length; b++) {
                  int gainLoss = prices[b] - prices[a];
                  if (gainLoss > maxProfit) {
                      maxProfit = gainLoss;
                  }
              }
          }
          return maxProfit;
      }

      public static int getMaximumProfitOptimized(int[] prices) {
          int maxProfit = prices[1] - prices[0];
          int low = prices[0];
          for (int i = 1; i < prices.length; i++) {
              int potentialProfit = prices[i] - low;
              maxProfit = Math.max(maxProfit, potentialProfit);
              low = Math.min(low, prices[i]);
          }
          return maxProfit;
      }
  }