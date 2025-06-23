import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Testing {
    public boolean isUnique(String string) {
        Set<Character> strSet = new HashSet<>();
        for (char c : string.toCharArray()) {
            strSet.add(c);
        }
        return strSet.size() == string.length();
    }

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