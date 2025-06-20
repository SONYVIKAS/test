import org.junit.Test;

public class TestSolution {

    public int findLongestWord(String[] words) {
        int longest = 0;

        for (String word : words) {
            longest = Math.max(word.length(), longest);
        }

        return longest;
    }

    @Test
    public void testFindLongestWord() {
        assertEquals(5, findLongestWord(new String[] {"hi", "hello"}));
        assertEquals(12, findLongestWord(new String[] {"Balloonicorn", "Hackbright"}));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestSolution.class);
    }