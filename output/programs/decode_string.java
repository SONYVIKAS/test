public class StringDecoder {
    public static String decode(String s) {
        String decoded = "";

        for (int i = 0; i < s.length(); i++) {
            try {
                int skip = Integer.parseInt(s.substring(i, i + 1));
                decoded += s.charAt(skip + i + 1);
            } catch (NumberFormatException e) {
                continue;
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

        try {
            int skip = Integer.parseInt(s.substring(i, i + 1));
            decoded += s.charAt(skip + i + 1);
        } catch (NumberFormatException e) {
            // Ignore non-numeric characters
        }

        return decodeRecursiveHelper(s, decoded, i + 1);
    }