
public class Main {

    // Method to decode a string
    public static String decode(String s) {
        // Runtime: O(n)
        // Spacetime: O(n)

        StringBuilder decoded = new StringBuilder();

        for (int l = 0; l < s.length(); l++) {
            if (Character.isDigit(s.charAt(l))) {
                decoded.append(s.charAt(Character.getNumericValue(s.charAt(l)) + l + 1));
            }
        }

        return decoded.toString();
    }

    // Recursive method to decode a string
    public static String decode_2(String s) {
        // Runtime: O(n)
        // Spacetime: O(n)

        return _decode_2(s, new StringBuilder(), 0);
    }

    // Helper method for recursive decoding
    private static String _decode_2(String s, StringBuilder decoded, int i) {

        if (i >= s.length()) {
            return decoded.toString();
        }

        char current = s.charAt(i);

        if (Character.isDigit(current)) {
            decoded.append(s.charAt(Character.getNumericValue(current) + i + 1));
        }

        return _decode_2(s, decoded, i + 1);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        System.out.println(decode("0h").equals("h") ? "PASS" : "FAIL");
        System.out.println(decode("2abh").equals("h") ? "PASS" : "FAIL");
        System.out.println(decode("0h1ae2bcy").equals("hey") ? "PASS" : "FAIL");

        System.out.println(decode_2("0h").equals("h") ? "PASS" : "FAIL");
        System.out.println(decode_2("2abh").equals("h") ? "PASS" : "FAIL");
        System.out.println(decode_2("0h1ae2bcy").equals("hey") ? "PASS" : "FAIL");
    }