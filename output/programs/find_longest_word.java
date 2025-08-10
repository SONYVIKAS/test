import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Define the class
public class Main {

    // Method to find the longest word in an array of words
    public static int findLongestWord(String[] words) {
        int longest = 0; // Initialize the longest word length to 0

        // Iterate through each word in the array
        for (String word : words) {
            longest = Math.max(word.length(), longest); // Update the longest word length if current word is longer
        }

        return longest; // Return the length of the longest word
    }

    // Define the test class
    public static class TestSolution {

        // Test method for findLongestWord
        @Test
        public void testFindLongestWord() {
            assertEquals(5, Main.findLongestWord(new String[]{"hi", "hello"})); // Test case 1
            assertEquals(12, Main.findLongestWord(new String[]{"Balloonicorn", "Hackbright"})); // Test case 2
        }
    }

    // Main method to run the tests
    public static void main(String[] args) {
        TestSolution testSolution = new TestSolution();
        testSolution.testFindLongestWord();
    }