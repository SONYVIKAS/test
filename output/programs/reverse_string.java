public class StringReversal {
    public static String reverseString(String string) {
        if (string.length() == 0) {
            return string;
        }

        return string.charAt(string.length() - 1) + reverseString(string.substring(0, string.length() - 1));
    }