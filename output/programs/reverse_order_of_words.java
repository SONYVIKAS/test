import java.util.List;
import java.util.ArrayList;

public class ReverseOrderOfWords {
    public static List<Character> reverseOrderOfWords(List<Character> lst) {
        List<Character> reversedArr = new ArrayList<>();
        String words = new String(lst.toArray(new Character[0]));
        String[] wordsArr = words.split(" ");
        while (wordsArr.length > 0) {
            String word = wordsArr[wordsArr.length - 1];
            reversedArr.add(word.charAt(0));
            wordsArr = Arrays.copyOfRange(wordsArr, 0, wordsArr.length - 1);
        }
        String reversedWords = String.join(" ", reversedArr);

        return new ArrayList<Character>(Arrays.asList(reversedWords.toCharArray()));
    }