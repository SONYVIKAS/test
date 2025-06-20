import org.junit.Test;
import org.junit.Assert;

public class Testing {
    public static boolean isUnique(String string) {
        Set<Character> strSet = new HashSet<Character>();
        for (char c : string.toCharArray()) {
            strSet.add(c);
        }
        return strSet.size() == string.length();
    }

    @Test
    public void testIsUnique() {
        Assert.assertTrue(isUnique("asdfghjkl"));
        Assert.assertTrue(isUnique("1234567asdf"));
        Assert.assertTrue(isUnique("!@#$%^&asdfg123"));
        Assert.assertTrue(isUnique("abcdABCD"));
        Assert.assertFalse(isUnique("asdfghjkll"));
        Assert.assertFalse(isUnique("1qwerty1"));
        Assert.assertFalse(isUnique("poiu$asdf$"));
    }