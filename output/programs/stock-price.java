public static int get_max_profit(int[] prices) {
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