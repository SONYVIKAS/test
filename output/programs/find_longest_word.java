import java.util.List;

public class LongestWord {

    public static int findLongestWord(List<String> words) {
        int longest = 0;

        for (String word : words) {
            longest = Math.max(word.length(), longest);
        }

        return longest;
    }
