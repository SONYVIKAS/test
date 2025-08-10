import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class Main {

    // Function to find the longest word in a list of words
    public static int findLongestWord(List<String> words) {
        int longest = 0;

        // Iterate through each word in the list
        for (String word : words) {
            // Update the longest word length if current word length is greater
            longest = Math.max(word.length(), longest);
        }

        // Return the length of the longest word
        return longest;
    }

    // Unit test class
    public static class TestSolution {

        @Test
        // Test case for findLongestWord function
        public void testFindLongestWord() {
            assertEquals(5, Main.findLongestWord(List.of("hi", "hello")));
            assertEquals(12, Main.findLongestWord(List.of("Balloonicorn", "Hackbright")));
        }
    }

    public static void main(String[] args) {
        // Run the tests
        org.junit.jupiter.api.Assertions.run(TestSolution.class);
    }