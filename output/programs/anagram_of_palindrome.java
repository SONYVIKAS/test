public class AnagramOfPalindrome {
    public static boolean isAnagramOfPalindrome(String word) {
        int[] charCounts = new int[26];

        for (char c : word.toCharArray()) {
            charCounts[c - 'a']++;
        }

        int oddCount = 0;
        for (int count : charCounts) {
            if (count % 2!= 0) {
                oddCount++;
            }
        }

        return oddCount <= 1;
    }

    public static void main(String[] args) {
        System.out.println(isAnagramOfPalindrome("a")); // true
        System.out.println(isAnagramOfPalindrome("ab")); // false
        System.out.println(isAnagramOfPalindrome("aab")); // true
        System.out.println(isAnagramOfPalindrome("arceace")); // true
        System.out.println(isAnagramOfPalindrome("arceaceb")); // false
    }