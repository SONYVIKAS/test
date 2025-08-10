import java.util.Arrays;
import java.util.List;

public class ReverseOrderOfWords {

    public static List<Character> reverseOrderOfWords(List<Character> lst) {
        // Convert the list of characters to a string
        StringBuilder words = new StringBuilder();
        for (Character ch : lst) {
            words.append(ch);
        }
        // Split the string into words
        String[] wordsArr = words.toString().split(" ");
        // Create a list to hold the reversed words
        List<String> reversedArr = new ArrayList<>();
        // Reverse the order of words
        for (int i = wordsArr.length - 1; i >= 0; i--) {
            reversedArr.add(wordsArr[i]);
        }
        // Join the reversed words into a single string
        String reversedWords = String.join(" ", reversedArr);
        // Convert the string back to a list of characters
        List<Character> result = new ArrayList<>();
        for (char ch : reversedWords.toCharArray()) {
            result.add(ch);
        }
        return result;
    }

    public static void main(String[] args) {
        // Test the function
        List<Character> input = Arrays.asList('p', 'r', 'a', 'c', 't', 'i', 'c', 'e', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'y', 'o', 'u');
        List<Character> expectedOutput = Arrays.asList('y', 'o', 'u', ' ', 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e');
        List<Character> output = reverseOrderOfWords(input);
        
        // Check if the output matches the expected output
        if (output.equals(expectedOutput)) {
            System.out.println("ALL TESTS PASSED");
        } else {
            System.out.println("TESTS FAILED");
        }
    }