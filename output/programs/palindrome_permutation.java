import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalindromePermutation {

    // Method to check if a string is a permutation of a palindrome
    public static boolean isPalPerm(String string) {
        Map<Character, Integer> letCounts = new HashMap<>();
        int odd = 0;

        // Count occurrences of each character
        for (char let : string.toCharArray()) {
            letCounts.put(let, letCounts.getOrDefault(let, 0) + 1);
        }

        // Count how many characters have an odd count
        for (int val : letCounts.values()) {
            if (val % 2 != 0) {
                odd++;
            }
        }

        // A string can be a permutation of a palindrome if it has at most one odd character count
        return odd <= 1;
    }

    public static class Testing {

        @Test
        public void testIsPalPerm() {
            assertTrue(isPalPerm("carereca")); // Test case where the string is a permutation of a palindrome
            assertTrue(isPalPerm("a"));        // Single character string is always a palindrome
            assertFalse(isPalPerm("carelnreca")); // Test case where the string is not a permutation of a palindrome
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("PalindromePermutation$Testing");
    }