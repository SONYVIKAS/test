import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BracketBalance {
    public static boolean hasBalancedBrackets(String phrase) {
        String[] brackets = {"]", "}", "[", ">", ")"};
        String[] openBrackets = {"[", "{", "[", "<", "("};
        String[] closeBrackets = {"]", "}", "]", ">", ")"};
        String[] seen = new String[phrase.length()];
        int seenIndex = 0;

        for (int i = 0; i < phrase.length(); i++) {
            char let = phrase.charAt(i);
            for (int j = 0; j < openBrackets.length; j++) {
                if (let == openBrackets[j].charAt(0)) {
                    seen[seenIndex] = openBrackets[j];
                    seenIndex++;
                }
            }
            for (int j = 0; j < closeBrackets.length; j++) {
                if (let == closeBrackets[j].charAt(0)) {
                    if (seenIndex > 0 && brackets[j].equals(seen[seenIndex - 1])) {
                        seenIndex--;
                    } else {
                        return false;
                    }
                }
            }
        }

        return seenIndex == 0;
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