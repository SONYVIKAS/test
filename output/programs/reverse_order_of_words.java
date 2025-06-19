  import java.util.List;
  import java.util.ArrayList;
  import java.util.Arrays;

  public class ReverseOrderOfWords {
      public static List<Character> reverseOrderOfWords(List<Character> lst) {
          List<Character> reversedArr = new ArrayList<>();
          String words = String.valueOf(lst);
          String[] wordsArr = words.split(" ");
          while (wordsArr.length > 0) {
              String word = wordsArr[wordsArr.length - 1];
              reversedArr.addAll(Arrays.asList(word.split("")));
              wordsArr = Arrays.copyOfRange(wordsArr, 0, wordsArr.length - 1);
          }
          String reversedWords = String.join(" ", reversedArr);
          return new ArrayList<>(Arrays.asList(reversedWords.split("")));
      }
  }