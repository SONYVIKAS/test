public class DecodeString {
    public static String decode(String s) {
        String decoded = "";

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (Character.isDigit(current)) {
                int skip = Integer.parseInt(String.valueOf(current));
                decoded += s.charAt(skip + i + 1);
            }
        }

        return decoded;
    }

    public static String decodeRecursive(String s) {
        return decodeRecursiveHelper(s, "", 0);
    }

    private static String decodeRecursiveHelper(String s, String decoded, int i) {
        if (i >= s.length()) {
            return decoded;
        }

        char current = s.charAt(i);

        if (Character.isDigit(current)) {
            int skip = Integer.parseInt(String.valueOf(current));
            decoded += s.charAt(skip + i + 1);
        }

        return decodeRecursiveHelper(s, decoded, i + 1);
    }