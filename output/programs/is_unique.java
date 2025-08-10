import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;

public class Main {

    // Method to check if a string has all unique characters
    public static boolean isUnique(String string) {
        Set<Character> strSet = new HashSet<Character>();
        for (char c : string.toCharArray()) {
            strSet.add(c);
        }
        return strSet.size() == string.length();
    }

    // Test cases for isUnique method
    public static class Testing {

        @Test
        public void testIsUnique() {
            assertEquals(true, isUnique("asdfghjkl"));
            assertEquals(true, isUnique("1234567asdf"));
            assertEquals(true, isUnique("!@#$%^&asdfg123"));
            assertEquals(true, isUnique("abcdABCD"));

            assertEquals(false, isUnique("asdfghjkll"));
            assertEquals(false, isUnique("1qwerty1"));
            assertEquals(false, isUnique("poiu$asdf$"));
        }
    }

    // Main method to run tests
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("Main.Testing");
    }