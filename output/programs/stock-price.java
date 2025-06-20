public static int getMaxProfit(int[] prices) {
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

public static int getMaxProfitOptimized(int[] prices) {
    int maxProfit = prices[1] - prices[0];
    int low = prices[0];

    for (int i = 1; i < prices.length; i++) {
        int potentialProfit = prices[i] - low;
        maxProfit = Math.max(maxProfit, potentialProfit);
        low = Math.min(low, prices[i]);
    }
    return maxProfit;
}

@Test
public void testMaxProfit() {
    int[] stockPricesYesterday = {10, 7, 5, 8, 11, 9};
    assertEquals(6, getMaxProfit(stockPricesYesterday));
    assertEquals(6, getMaxProfitOptimized(stockPricesYesterday));
    stockPricesYesterday = new int[] {10, 3};
    assertEquals(-7, getMaxProfit(stockPricesYesterday));
    assertEquals(-7, getMaxProfitOptimized(stockPricesYesterday));
    stockPricesYesterday = new int[] {1, 10, 7, 14, 2, 11};
    assertEquals(13, getMaxProfit(stockPricesYesterday));
    assertEquals(13, getMaxProfitOptimized(stockPricesYesterday));
    stockPricesYesterday = new int[] {11, 10, 9, 8, 2, 1};
    assertEquals(-1, getMaxProfit(stockPricesYesterday));
    assertEquals(-1, getMaxProfitOptimized(stockPricesYesterday));
    stockPricesYesterday = new int[] {11, 9, 5, 2, 2, 0};
    assertEquals(0, getMaxProfit(stockPricesYesterday));
    assertEquals(0, getMaxProfitOptimized(stockPricesYesterday));
    stockPricesYesterday = new int[] {1, 1, 1, 1, 1, 1};
    assertEquals(0, getMaxProfit(stockPricesYesterday));
    assertEquals(0, getMaxProfitOptimized(stockPricesYesterday));