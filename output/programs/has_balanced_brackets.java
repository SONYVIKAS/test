import java.util.HashSet;
import java.util.Stack;
import org.junit.Test;
import static org.junit.Assert.*;

public class BalancedBrackets {

    // Method to check if the given string has balanced brackets
    public static boolean hasBalancedBrackets(String phrase) {
        // Map to hold matching pairs of brackets
        HashMap<Character, Character> brackets = new HashMap<>();
        brackets.put(']', '[');
        brackets.put('}', '{');
        brackets.put('>', '<');
        brackets.put(')', '(');

        // Sets to hold open and close brackets
        HashSet<Character> openBrackets = new HashSet<>();
        openBrackets.add('[');
        openBrackets.add('{');
        openBrackets.add('<');
        openBrackets.add('(');

        HashSet<Character> closeBrackets = new HashSet<>();
        closeBrackets.add(']');
        closeBrackets.add('}');
        closeBrackets.add('>');
        closeBrackets.add(')');

        // Stack to keep track of seen open brackets
        Stack<Character> seen = new Stack<>();

        // Iterate through each character in the phrase
        for (char let : phrase.toCharArray()) {
            if (openBrackets.contains(let)) {
                seen.push(let); // Add open bracket to stack
            }
            if (closeBrackets.contains(let)) {
                // Check if the stack is not empty and the top matches the current closing bracket
                if (!seen.isEmpty() && brackets.get(let) == seen.peek()) {
                    seen.pop(); // Remove the matched open bracket
                } else {
                    return false; // Unmatched closing bracket
                }
            }
        }

        // If stack is empty, all brackets are balanced
        return seen.isEmpty();
    }

    // Unit tests for the hasBalancedBrackets method
    public static class TestSolutions {

        @Test
        public void testHasBalancedBrackets() {
            assertTrue(hasBalancedBrackets("<ok>"));
            assertTrue(hasBalancedBrackets("<[ok]>"));
            assertTrue(hasBalancedBrackets("<[{(yay)}]>"));
            assertTrue(hasBalancedBrackets("No brackets here!"));

            assertFalse(hasBalancedBrackets("(Oops!){"));
            assertFalse(hasBalancedBrackets("{[[This has too many open square brackets.]}"));
            assertFalse(hasBalancedBrackets(">"));
            assertFalse(hasBalancedBrackets("(This has {too many} ) closers. )"));
            assertFalse(hasBalancedBrackets("<{Not Ok>}"));
        }
    }

    // Main method to run the tests
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("BalancedBrackets$TestSolutions");
    }