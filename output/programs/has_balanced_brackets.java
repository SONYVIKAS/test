import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BalancedBrackets {
    public static boolean hasBalancedBrackets(String phrase) {
        String[] brackets = {"]", "}", "[", ">", ")"};
        String[] openBrackets = {"[", "{", "[", "<", "("};
        String[] closeBrackets = {"]", "}", "]", ">", ")"};
        java.util.Stack<String> seen = new java.util.Stack<String>();

        for (char let : phrase.toCharArray()) {
            if (new java.util.ArrayList<String>(java.util.Arrays.asList(openBrackets)).contains(let)) {
                seen.push(let);
            }
            if (new java.util.ArrayList<String>(java.util.Arrays.asList(closeBrackets)).contains(let)) {
                if (!seen.isEmpty() && brackets[new java.util.ArrayList<String>(java.util.Arrays.asList(closeBrackets)).indexOf(let)] == seen.peek()) {
                    seen.pop();
                } else {
                    return false;
                }
            }
        }

        return seen.isEmpty();
    }

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