import java.util.Map;

public class AnagramOfPalindrome {

    // Check if the word is an anagram of a palindrome
    public static boolean isAnagramOfPalindrome(String word) {
        // Create a map to count occurrences of each character
        Map<Character, Integer> wordMap = new HashMap<>();

        // Count each character in the word
        for (char l : word.toCharArray()) {
            wordMap.put(l, wordMap.getOrDefault(l, 0) + 1);
        }

        // Check the number of characters with odd counts
        int oddCount = 0;
        for (int count : wordMap.values()) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        // A word can be an anagram of a palindrome if it has at most one odd count
        return oddCount <= 1;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isAnagramOfPalindrome("a")); // true
        System.out.println(isAnagramOfPalindrome("ab")); // false
        System.out.println(isAnagramOfPalindrome("aab")); // true
        System.out.println(isAnagramOfPalindrome("arceace")); // true
        System.out.println(isAnagramOfPalindrome("arceaceb")); // false

        // Print test results
        System.out.println("ALL TESTS PASSED!");
    }