import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UniqueCharacters {
    public static boolean isUnique(String string) {
        boolean[] characterSet = new boolean[256];

        for (int i = 0; i < string.length(); i++) {
            int value = string.charAt(i);

            if (characterSet[value]) {
                return false;
            }

            characterSet[value] = true;
        }

        return true;
    }

    @Test
    public void testIsUnique() {
        assertTrue(isUnique("asdfghjkl"));
        assertTrue(isUnique("1234567asdf"));
        assertTrue(isUnique("!@#$%^&asdfg123"));
        assertTrue(isUnique("abcdABCD"));

        assertFalse(isUnique("asdfghjkll"));
        assertFalse(isUnique("1qwerty1"));
        assertFalse(isUnique("poiu$asdf$"));
    }