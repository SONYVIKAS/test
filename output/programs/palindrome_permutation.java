import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PalindromePermutation {
    public static boolean isPalindromePermutation(String string) {
        int[] letterCounts = new int[26];
        int oddCount = 0;

        for (char letter : string.toCharArray()) {
            int index = letter - 'a';
            letterCounts[index]++;
        }

        for (int count : letterCounts) {
            if (count % 2!= 0) {
                oddCount++;
            }
        }

        return oddCount <= 1;
    }

    @Test
    public void testIsPalindromePermutation() {
        assertTrue(isPalindromePermutation("carereca"));
        assertTrue(isPalindromePermutation("a"));
        assertFalse(isPalindromePermutation("carelnreca"));
    }