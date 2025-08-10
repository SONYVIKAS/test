
public class Main {
    // Method to reverse a string using a stack-like approach
    public static String revStr(String string) {
        // Runtime: O(n)

        StringBuilder reversedStr = new StringBuilder();
        char[] charArray = string.toCharArray();
        // ['h', 'e', l, l, o]
        for (int i = charArray.length - 1; i >= 0; i--) {
            char letter = charArray[i];
            // l
            reversedStr.append(letter);
            // oll
        }
        return reversedStr.toString();
    }

    // Method to reverse a string using StringBuilder's reverse method
    public static String revStr2(String string) {
        // Runtime: O(n)

        return new StringBuilder(string).reverse().toString();
    }

    // Method to reverse a string using recursion
    public static String revStr3(String string) {
        // Runtime: O(n)

        if (string.length() == 0) {
            return string;
        }

        return string.charAt(string.length() - 1) + revStr3(string.substring(0, string.length() - 1));
    }

    public static void main(String[] args) {
        // Test the methods
        System.out.println(revStr("hello").equals("olleh") ? "Test passed!" : "Test failed!");
        System.out.println(revStr2("1234h").equals("h4321") ? "Test passed!" : "Test failed!");
        System.out.println(revStr3("").equals("") ? "Test passed!" : "Test failed!");
    }