import java.util.HashMap;
import java.util.Map;

public class Main {

    // Function to check if a string is a permutation of a palindrome
    public static boolean isPalPerm(String string) {
        Map<Character, Integer> letCounts = new HashMap<>();
        int odd = 0;

        // Count the occurrences of each character
        for (char let : string.toCharArray()) {
            letCounts.put(let, letCounts.getOrDefault(let, 0) + 1);
        }

        // Check the counts of each character
        for (int val : letCounts.values()) {
            if (val % 2 != 0) {
                odd += 1;
            }
        }

        // A string can be a permutation of a palindrome if and only if
        // the number of characters with odd counts is less than or equal to 1
        return odd <= 1;
    }

    public static void main(String[] args) {
        // Test the function
        assert isPalPerm("carereca");
        assert isPalPerm("a");
        assert !isPalPerm("carelnreca");
    }