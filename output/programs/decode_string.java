import java.util.*;

public class Main {
    // Method to decode a string
    public static String decode(String s) {
        // Initialize an empty string to store the decoded string
        String decoded = "";

        // Loop through the string
        for (int l = 0; l < s.length(); l++) {
            // Try to parse the current character as an integer
            try {
                int num = Integer.parseInt(String.valueOf(s.charAt(l)));
                // If successful, add the character at the index obtained by adding the parsed integer and the current index to the decoded string
                decoded += s.charAt(num + l + 1);
            } catch (NumberFormatException e) {
                // If the current character cannot be parsed as an integer, continue to the next character
                continue;
            }
        }

        // Return the decoded string
        return decoded;
    }

    // Recursive helper method for the second decode method
    private static String _decode_2(String s, String decoded, int i) {
        // If the current index is greater than or equal to the length of the string, return the decoded string
        if (i >= s.length()) {
            return decoded;
        }

        // Get the current character
        char current = s.charAt(i);

        // If the current character is a digit
        if (Character.isDigit(current)) {
            // Add the character at the index obtained by adding the integer value of the current character and the current index to the decoded string
            decoded += s.charAt(Character.getNumericValue(current) + i + 1);
        }

        // Recursively call the helper method with the next index
        return _decode_2(s, decoded, i + 1);
    }

    // Second method to decode a string
    public static String decode_2(String s) {
        // Call the recursive helper method with an empty string and an index of 0
        return _decode_2(s, "", 0);
    }

    // Main method
    public static void main(String[] args) {
        // Test the decode methods
        System.out.println(decode("0h")); // Should print "h"
        System.out.println(decode("2abh")); // Should print "h"
        System.out.println(decode("0h1ae2bcy")); // Should print "hey"
        System.out.println(decode_2("0h")); // Should print "h"
        System.out.println(decode_2("2abh")); // Should print "h"
        System.out.println(decode_2("0h1ae2bcy")); // Should print "hey"
    }