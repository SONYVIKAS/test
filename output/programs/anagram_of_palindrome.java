import java.util.HashMap;
import java.util.Map;

public class AnagramOfPalindrome {
    public static boolean isAnagramOfPalindrome(String word) {
        Map<Character, Integer> letterCounts = new HashMap<>();

        for (char letter : word.toCharArray()) {
            letterCounts.put(letter, letterCounts.getOrDefault(letter, 0) + 1);
        }

        boolean hasOddCount = false;

        for (Map.Entry<Character, Integer> entry : letterCounts.entrySet()) {
            if (entry.getValue() % 2!= 0) {
                if (hasOddCount) {
                    return false;
                } else {
                    hasOddCount = true;
                }
            }
        }

        return true;
    }