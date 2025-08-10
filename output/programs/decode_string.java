
public class DecodeString {

    // Method to decode the string using an iterative approach
    public static String decode(String s) {
        // Initialize an empty string to store the decoded result
        String decoded = "";

        // Iterate over each character in the string
        for (int l = 0; l < s.length(); l++) {
            try {
                // Try to parse the current character as an integer
                int num = Integer.parseInt(String.valueOf(s.charAt(l)));
                // Append the character at the calculated position to the decoded string
                decoded += s.charAt(num + l + 1);
            } catch (NumberFormatException e) {
                // Continue if the character is not a number
                continue;
            }
        }

        return decoded;
    }

    // Method to decode the string using a recursive approach
    public static String decode_2(String s) {
        // Call the recursive helper function starting with an empty decoded string and index 0
        return _decode_2(s, "", 0);
    }

    // Recursive helper function for decode_2
    private static String _decode_2(String s, String decoded, int i) {
        // Base case: if the index is out of bounds, return the decoded string
        if (i >= s.length()) {
            return decoded;
        }

        // Get the current character
        char current = s.charAt(i);

        // Check if the current character is a digit
        if (Character.isDigit(current)) {
            // Append the character at the calculated position to the decoded string
            decoded += s.charAt(Character.getNumericValue(current) + i + 1);
        }

        // Recursive call with the next index
        return _decode_2(s, decoded, i + 1);
    }

    public static void main(String[] args) {
        // Test cases to verify the decode methods
        System.out.println(decode("0h")); // Output: 'h'
        System.out.println(decode("2abh")); // Output: 'h'
        System.out.println(decode("0h1ae2bcy")); // Output: 'hey'

        System.out.println(decode_2("0h")); // Output: 'h'
        System.out.println(decode_2("2abh")); // Output: 'h'
        System.out.println(decode_2("0h1ae2bcy")); // Output: 'hey'
    }