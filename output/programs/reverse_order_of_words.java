import java.util.List;
import java.util.ArrayList;

public class ReverseOrderOfWords {
    public static void main(String[] args) {
        List<Character> lst = List.of('p', 'r', 'a', 'c', 't', 'i', 'c', 'e','','m', 'a', 'k', 'e','s','', 'p', 'e', 'r', 'f', 'e', 'c', 't','', 'y', 'o', 'u');
        List<Character> reversedList = reverseOrderOfWords(lst);
        System.out.println(reversedList);
    }

    public static List<Character> reverseOrderOfWords(List<Character> lst) {
        String words = new String(lst.toArray(new Character[lst.size()]));
        String reversedWords = new StringBuilder(words).reverse().toString();
        return new ArrayList<Character>(List.of(reversedWords.toCharArray()));
    }