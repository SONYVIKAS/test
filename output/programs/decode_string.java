public static String decode(String s) {
    String decoded = "";

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Character.isDigit(c)) {
            int skip = Integer.parseInt(c + "");
            decoded += s.charAt(skip + i + 1);
        }
    }

    return decoded;