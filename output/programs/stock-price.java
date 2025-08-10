
public class StockPrice {

    // Method to find the maximum profit using a brute-force approach
    public static int getMaxProfit(List<Integer> prices) {
        // Initialize max profit with the first possible transaction
        int maxProfit = prices.get(1) - prices.get(0);

        // Iterate over all pairs of prices
        for (int a = 0; a < prices.size(); a++) {
            for (int b = a + 1; b < prices.size(); b++) {
                int gainLoss = prices.get(b) - prices.get(a);
                if (gainLoss > maxProfit) {
                    maxProfit = gainLoss;
                }
            }
        }
        System.out.println(maxProfit);
        return maxProfit;
    }

    // Optimized method to find the maximum profit
    public static int getMaxProfitOptimized(List<Integer> prices) {
        // Initialize max profit and the lowest price seen so far
        int maxProfit = prices.get(1) - prices.get(0);
        int low = prices.get(0);

        // Iterate over the prices
        for (int i = 1; i < prices.size(); i++) {
            int currentPrice = prices.get(i);

            // Calculate potential profit
            int potentialProfit = currentPrice - low;
            maxProfit = Math.max(maxProfit, potentialProfit);

            // Update the lowest price seen so far
            low = Math.min(low, currentPrice);
        }
        System.out.println(maxProfit);
        return maxProfit;
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> stockPricesYesterday1 = List.of(10, 7, 5, 8, 11, 9);
        List<Integer> stockPricesYesterday2 = List.of(10, 3);
        List<Integer> stockPricesYesterday3 = List.of(1, 10, 7, 14, 2, 11);
        List<Integer> stockPricesYesterday4 = List.of(11, 10, 9, 8, 2, 1);
        List<Integer> stockPricesYesterday5 = List.of(11, 9, 5, 2, 2, 0);
        List<Integer> stockPricesYesterday6 = List.of(1, 1, 1, 1, 1, 1);

        // Run tests
        assert getMaxProfit(stockPricesYesterday1) == 6;
        assert getMaxProfit(stockPricesYesterday2) == -7;
        assert getMaxProfit(stockPricesYesterday3) == 13;
        assert getMaxProfit(stockPricesYesterday4) == -1;
        assert getMaxProfit(stockPricesYesterday5) == 0;
        assert getMaxProfit(stockPricesYesterday6) == 0;

        assert getMaxProfitOptimized(stockPricesYesterday1) == 6;
        assert getMaxProfitOptimized(stockPricesYesterday2) == -7;
        assert getMaxProfitOptimized(stockPricesYesterday3) == 13;
        assert getMaxProfitOptimized(stockPricesYesterday4) == -1;
        assert getMaxProfitOptimized(stockPricesYesterday5) == 0;
        assert getMaxProfitOptimized(stockPricesYesterday6) == 0;

        System.out.println("ALL TESTS PASSED");
    }