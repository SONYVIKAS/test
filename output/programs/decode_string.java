public class DecodeString {
    public static String decode(String s) {
        String decoded = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int skip = Integer.parseInt(String.valueOf(c));
                decoded += s.charAt(skip + i + 1);
            }
        }

        return decoded;
    }

    public static String decodeRecursive(String s) {
        return decodeRecursive(s, "", 0);
    }

    private static String decodeRecursive(String s, String decoded, int i) {
        if (i >= s.length()) {
            return decoded;
        }

        char c = s.charAt(i);

        if (Character.isDigit(c)) {
            int skip = Integer.parseInt(String.valueOf(c));
            decoded += s.charAt(skip + i + 1);
        }

        return decodeRecursive(s, decoded, i + 1);
    }