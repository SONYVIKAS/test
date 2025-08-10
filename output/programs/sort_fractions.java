import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortFractions {
    public static void main(String[] args) {
        Random random = new Random();
        List<Double> nums = new ArrayList<>();

        // Generate 100 random numbers between 0 and 1000, rounded to 3 decimal places
        for (int i = 0; i < 100; i++) {
            double num = Math.round((random.nextDouble() * 1000) * 1000.0) / 1000.0;
            nums.add(num);
        }

        // Sort nums using only the fractional portion of each number
        List<String> sortedFractions = sortFractional(nums);
        System.out.println(sortedFractions);
    }

    private static List<String> sortFractional(List<Double> nums) {
        List<String> fractionalParts = new ArrayList<>();

        // Extract the fractional part of each number
        for (Double num : nums) {
            String numStr = num.toString();
            int index = numStr.indexOf('.');
            fractionalParts.add(numStr.substring(index + 1));
        }

        // Sort the fractional parts
        Collections.sort(fractionalParts);
        return fractionalParts;
    }