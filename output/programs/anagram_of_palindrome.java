public static boolean isAnagramOfPalindrome(String word) {
    Map<Character, Integer> wordDict = new HashMap<>();

    for (char l : word.toCharArray()) {
        wordDict.put(l, wordDict.getOrDefault(l, 0) + 1);
    }

    int oddCount = 0;
    for (char letter : wordDict.keySet()) {
        if (wordDict.get(letter) % 2!= 0) {
            oddCount++;
        }
    }

    return oddCount <= 1;