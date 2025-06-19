import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class StringUnique {
    public static boolean isUnique(String string) {
        return string.chars().distinct().count() == string.length();
    }

    @Test
    public void testIsUnique() {
        assertTrue(isUnique("abcdefghijklmnopqrstuvwxyz"));
        assertTrue(isUnique("1234567890!@#$%^&*()_+"));
        assertTrue(isUnique("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        assertFalse(isUnique("abcdefghijklmnopqrstuvwxyza"));
        assertFalse(isUnique("1234567890!@#$%^&*()_+1"));
        assertFalse(isUnique("ABCDEFGHIJKLMNOPQRSTUVWXYZA"));
    }