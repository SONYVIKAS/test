import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IsUnique {
    public static boolean isUnique(String string) {
        Set<Character> strSet = new HashSet<Character>();

        for (char c : string.toCharArray()) {
            strSet.add(c);
        }

        return strSet.size() == string.length();
    }

    @Test
    public void testIsUnique() {
        assertEquals(isUnique("asdfghjkl"), true);
        assertEquals(isUnique("1234567asdf"), true);
        assertEquals(isUnique("!@#$%^&asdfg123"), true);
        assertEquals(isUnique("abcdABCD"), true);

        assertEquals(isUnique("asdfghjkll"), false);
        assertEquals(isUnique("1qwerty1"), false);
        assertEquals(isUnique("poiu$asdf$"), false);
    }