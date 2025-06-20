public static int get_max_profit(int[] prices) {
    int max_profit = prices[1] - prices[0];

    for (int a = 0; a < prices.length; a++) {
        for (int b = a + 1; b < prices.length; b++) {
            int gain_loss = prices[b] - prices[a];
            if (gain_loss > max_profit) {
                max_profit = gain_loss;
            }
        }
    }
    return max_profit;
}

public static int get_max_profit_optimized(int[] prices) {
    int max_profit = prices[1] - prices[0];
    int low = prices[0];

    for (int i = 1; i < prices.length; i++) {
        int potential_profit = prices[i] - low;
        max_profit = Math.max(max_profit, potential_profit);
        low = Math.min(low, prices[i]);
    }
    return max_profit;