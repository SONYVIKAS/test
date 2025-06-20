import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class ReverseOrderOfWords {
    public static List<Character> reverseOrderOfWords(List<Character> lst) {
        String words = String.join("", lst);
        String[] wordsArray = words.split(" ");
        Stack<String> stack = new Stack<>();
        for (String word : wordsArray) {
            stack.push(word);
        }
        List<String> reversedWordsList = new ArrayList<>();
        while (!stack.isEmpty()) {
            reversedWordsList.add(stack.pop());
        }
        String reversedWords = String.join(" ", reversedWordsList);
        return new ArrayList<>(List.of(reversedWords.chars().mapToObj(c -> (char) c).toArray()));
    }