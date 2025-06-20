import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BracketBalancing {
    public static boolean hasBalancedBrackets(String phrase) {
        String brackets = "(){}[]<>";
        String openBrackets = "([<{";
        String closeBrackets = ")]}>";

        String seen = "";

        for (char c : phrase.toCharArray()) {
            if (openBrackets.contains(c + "")) {
                seen += c;
            } else if (closeBrackets.contains(c + "")) {
                if (seen.length() > 0 && brackets.indexOf(c) == brackets.indexOf(seen.charAt(seen.length() - 1))) {
                    seen = seen.substring(0, seen.length() - 1);
                } else {
                    return false;
                }
            }
        }

        return seen.length() == 0;
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