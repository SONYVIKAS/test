import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MostFrequentWord {
    public static void mostFrequentWord(String str) {
        Map<String, Integer> words = new HashMap<>();
        List<String> mostFrequentWords = new ArrayList<>();

        String[] list_of_words = str.split(" ");

        for (String word : list_of_words) {
            words.put(word, words.getOrDefault(word, 0) + 1);
        }

        int max_value = Collections.max(words.values());

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (entry.getValue().equals(max_value)) {
                mostFrequentWords.add(entry.getKey());
            }
        }

        Collections.sort(mostFrequentWords);

        for (String word : mostFrequentWords) {
            System.out.print(word + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        mostFrequentWord("hello my name is hello joanne");
        mostFrequentWord("hello my name is hello joanne is");
        mostFrequentWord("hello my name is joanne");
    }