import java.util.List;
import java.util.ArrayList;

public class ReverseOrderOfWords {
    public static List<Character> reverseOrderOfWords(List<Character> lst) {
        List<Character> reversedArr = new ArrayList<Character>();
        String words = String.valueOf(lst);
        String[] wordsArr = words.split(" ");
        while (wordsArr.length > 0) {
            String word = wordsArr[wordsArr.length - 1];
            wordsArr = Arrays.copyOf(wordsArr, wordsArr.length - 1);
            reversedArr.add(word);
        }
        String reversedWords = String.join(" ", reversedArr);

        return new ArrayList<Character>(reversedWords.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
    }