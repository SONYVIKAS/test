import java.util.List;
import java.util.ArrayList;

public class ReverseOrderOfWords {
    public static List<Character> reverseOrderOfWords(List<Character> lst) {
        List<Character> reversedArr = new ArrayList<>();
        String words = String.join("", lst);
        String[] wordsArr = words.split(" ");
        while (wordsArr.length > 0) {
            String word = wordsArr[wordsArr.length - 1];
            reversedArr.add(word.charAt(0));
            wordsArr = removeLastElement(wordsArr);
        }
        String reversedWords = String.join(" ", reversedArr);

        return new ArrayList<Character>(reversedWords.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
    }

    public static String[] removeLastElement(String[] arr) {
        String[] newArr = new String[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }