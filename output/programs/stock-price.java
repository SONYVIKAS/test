
public class Main {

    // Method to find the maximum profit for buying and selling stock within a day
    public static int getMaxProfit(int[] prices) {
        // Initialize maxProfit with the first possible profit
        int maxProfit = prices[1] - prices[0];

        // Iterate over all prices
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

    // Optimized method to find the maximum profit for buying and selling stock within a day
    public static int getMaxProfitOptimized(int[] prices) {
        // Initialize maxProfit with the first possible profit
        int maxProfit = prices[1] - prices[0];
        int low = prices[0];

        // Iterate over all prices
        for (int i : prices) {
            // Skip the first price in list
            if (prices[0] == i) {
                continue;
            }
            int potentialProfit = i - low;
            maxProfit = Math.max(maxProfit, potentialProfit);
            low = Math.min(low, i);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        // Test cases
        int[] stockPricesYesterday1 = {10, 7, 5, 8, 11, 9};
        int[] stockPricesYesterday2 = {10, 3};
        int[] stockPricesYesterday3 = {1, 10, 7, 14, 2, 11};
        int[] stockPricesYesterday4 = {11, 10, 9, 8, 2, 1};
        int[] stockPricesYesterday5 = {11, 9, 5, 2, 2, 0};
        int[] stockPricesYesterday6 = {1, 1, 1, 1, 1, 1};

        // Print the results of the test cases
        System.out.println(getMaxProfit(stockPricesYesterday1));
        System.out.println(getMaxProfit(stockPricesYesterday2));
        System.out.println(getMaxProfit(stockPricesYesterday3));
        System.out.println(getMaxProfit(stockPricesYesterday4));
        System.out.println(getMaxProfit(stockPricesYesterday5));
        System.out.println(getMaxProfit(stockPricesYesterday6));

        System.out.println(getMaxProfitOptimized(stockPricesYesterday1));
        System.out.println(getMaxProfitOptimized(stockPricesYesterday2));
        System.out.println(getMaxProfitOptimized(stockPricesYesterday3));
        System.out.println(getMaxProfitOptimized(stockPricesYesterday4));
        System.out.println(getMaxProfitOptimized(stockPricesYesterday5));
        System.out.println(getMaxProfitOptimized(stockPricesYesterday6));
    }