import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LongestWord {
    public static int findLongestWord(String[] words) {
        int longest = 0;

        for (String word : words) {
            longest = Math.max(word.length(), longest);
        }

        return longest;
    }

    @Test
    public void testFindLongestWord() {
        String[] words1 = {"hi", "hello"};
        String[] words2 = {"Balloonicorn", "Hackbright"};

        assertEquals(findLongestWord(words1), 5);
        assertEquals(findLongestWord(words2), 12);
    }