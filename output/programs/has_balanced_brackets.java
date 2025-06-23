import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BracketsTest {
    public static boolean hasBalancedBrackets(String phrase) {
        // Implementation of the has_balanced_brackets function in Java
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