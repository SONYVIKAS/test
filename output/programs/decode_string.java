public static String decode(String s) {
    String decoded = "";

    for (int i = 0; i < s.length(); i++) {
        char current = s.charAt(i);
        if (Character.isDigit(current)) {
            int skip = Integer.parseInt(current + "");
            decoded += s.charAt(skip + i + 1);
        }
    }

    return decoded;