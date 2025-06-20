public class LongestWord {
    public static int findLongestWord(String[] words) {
        int longest = 0;

        for (String word : words) {
            longest = Math.max(word.length(), longest);
        }

        return longest;
    }

    public static void main(String[] args) {
        String[] words = {"hi", "hello"};
        int longest = findLongestWord(words);
        System.out.println("Longest word: " + longest);
    }