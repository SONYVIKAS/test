import static org.junit.Assert.assertEquals;

public class FindLongestWord {

    // Method to find the longest word length in an array of words
    public static int findLongestWord(String[] words) {
        int longest = 0; // Initialize the longest length to 0

        // Iterate through each word in the array
        for (String word : words) {
            // Update longest with the maximum length found
            longest = Math.max(word.length(), longest);
        }

        return longest; // Return the length of the longest word
    }

    public static class TestSolution {

        @Test
        public void testFindLongestWord() {
            // Test case 1: Check if the longest word length is 5
            assertEquals(5, findLongestWord(new String[]{"hi", "hello"}));
            // Test case 2: Check if the longest word length is 12
            assertEquals(12, findLongestWord(new String[]{"Balloonicorn", "Hackbright"}));
        }
    }

    public static void main(String[] args) {
        // Run the tests
        org.junit.runner.JUnitCore.main("FindLongestWord$TestSolution");
    }