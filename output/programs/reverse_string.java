import java.util.List;

public class ReverseString {

    // Given a string, return the same string, reversed.
    // Can't use built-in reverse methods.

    public static String revStr(String string) {
        // Initialize an empty string to store the reversed string
        String reversedStr = "";
        // Convert the input string to a list of characters
        List<Character> listStr = new ArrayList<>();
        for (char c : string.toCharArray()) {
            listStr.add(c);
        }
        // Iterate over the list and build the reversed string
        for (int i = 0; i < listStr.size(); i++) {
            char letter = listStr.remove(listStr.size() - 1);
            reversedStr += letter;
        }
        return reversedStr;
    }

    public static String revStr2(String string) {
        // Use StringBuilder to reverse the string
        return new StringBuilder(string).reverse().toString();
    }

    public static String revStr3(String string) {
        // Base case: if the string is empty, return it
        if (string.length() == 0) {
            return string;
        }
        // Recursive case: return the last character plus the reverse of the rest
        return string.charAt(string.length() - 1) + revStr3(string.substring(0, string.length() - 1));
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(revStr("hello").equals("olleh")); // true
        System.out.println(revStr("1234h").equals("h4321")); // true
        System.out.println(revStr("").equals("")); // true

        System.out.println(revStr2("hello").equals("olleh")); // true
        System.out.println(revStr2("1234h").equals("h4321")); // true
        System.out.println(revStr2("").equals("")); // true

        System.out.println(revStr3("hello").equals("olleh")); // true
        System.out.println(revStr3("1234h").equals("h4321")); // true
        System.out.println(revStr3("").equals("")); // true

        System.out.println("ALL TESTS PASSED!");
    }