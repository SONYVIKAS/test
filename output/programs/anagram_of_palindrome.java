public static boolean isAnagramOfPalindrome(String word) {
    Map<Character, Integer> wordDict = new HashMap<>();

    for (char l : word.toCharArray()) {
        wordDict.put(l, wordDict.getOrDefault(l, 0) + 1);
    }

    boolean oddFound = false;

    for (char letter : wordDict.keySet()) {
        if (wordDict.get(letter) % 2!= 0) {
            if (oddFound) {
                return false;
            } else {
                oddFound = true;
            }
        }
    }

    return true;