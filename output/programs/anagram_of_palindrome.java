
public class Main {
    public static void main(String[] args) {
        System.out.println(isAnagramOfPalindrome("a")); // true
        System.out.println(isAnagramOfPalindrome("ab")); // false
        System.out.println(isAnagramOfPalindrome("aab")); // true
        System.out.println(isAnagramOfPalindrome("arceace")); // true
        System.out.println(isAnagramOfPalindrome("arceaceb")); // false
    }

    public static boolean isAnagramOfPalindrome(String word) {
        // Runtime: O(n)

        HashMap<Character, Integer> wordDict = new HashMap<>();

        for (char l : word.toCharArray()) {
            wordDict.put(l, wordDict.getOrDefault(l, 0) + 1);
        }

        int oddCount = 0;
        for (int count : wordDict.values()) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        // If the word length is even, there should be no letters with odd counts.
        // If the word length is odd, there should be exactly one letter with an odd count.
        return word.length() % 2 == 0 ? oddCount == 0 : oddCount == 1;
    }