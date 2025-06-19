import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PalindromePermutation {

    public boolean isPalindromePermutation(String string) {
        int[] letCounts = new int[26];
        int odd = 0;

        for (char let : string.toCharArray()) {
            letCounts[let - 'a']++;
        }

        for (int val : letCounts) {
            if (val % 2!= 0) {
                odd++;
            }
        }

        return odd <= 1;
    }

    @Test
    public void testIsPalindromePermutation() {
        assertTrue(isPalindromePermutation("carereca"));
        assertTrue(isPalindromePermutation("a"));
        assertFalse(isPalindromePermutation("carelnreca"));
    }