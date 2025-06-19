import java.util.HashMap;
import java.util.Map;

public class AnagramOfPalindrome {
    public static boolean isAnagramOfPalindrome(String word) {
        Map<Character, Integer> wordDict = new HashMap<>();

        for (char l : word.toCharArray()) {
            wordDict.put(l, wordDict.getOrDefault(l, 0) + 1);
        }

        boolean hasOddCount = false;

        for (char letter : wordDict.keySet()) {
            if (wordDict.get(letter) % 2!= 0) {
                if (hasOddCount) {
                    return false;
                }
                hasOddCount = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagramOfPalindrome("a"));
        System.out.println(isAnagramOfPalindrome("ab"));
        System.out.println(isAnagramOfPalindrome("aab"));
        System.out.println(isAnagramOfPalindrome("arceace"));
        System.out.println(isAnagramOfPalindrome("arceaceb"));
    }