
public class Main {
    public static void main(String[] args) {
        System.out.println(isAnagramOfPalindrome("a")); // True
        System.out.println(isAnagramOfPalindrome("ab")); // False
        System.out.println(isAnagramOfPalindrome("aab")); // True
        System.out.println(isAnagramOfPalindrome("arceace")); // True
        System.out.println(isAnagramOfPalindrome("arceaceb")); // False
    }

    public static boolean isAnagramOfPalindrome(String word) {
        // Runtime: O(n)

        // Create a HashMap to store the frequency of each character
        HashMap<Character, Integer> wordDict = new HashMap<>();

        // Iterate over each character in the word
        for (char l : word.toCharArray()) {
            // If the character is already in the HashMap, increment its count
            // If it's not in the HashMap, add it with a count of 1
            wordDict.put(l, wordDict.getOrDefault(l, 0) + 1);
        }

        // Check if the word can be a palindrome
        // A word can be a palindrome if the count of all characters is even, or if only one character count is odd
        int oddCount = 0;
        for (int count : wordDict.values()) {
            if (count % 2 != 0) {
                oddCount++;
            }
            if (oddCount > 1) {
                return false;
            }
        }
        return true;
    }