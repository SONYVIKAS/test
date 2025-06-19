public static int getMaximumProfit(int[] stockPrices) {
    if (stockPrices.length < 2) {
        throw new IllegalArgumentException("At least two prices are required");
    }
    int maxProfit = stockPrices[1] - stockPrices[0];
    for (int i = 1; i < stockPrices.length; i++) {
        for (int j = 0; j < i; j++) {
            int profit = stockPrices[i] - stockPrices[j];
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
    }
    return maxProfit;