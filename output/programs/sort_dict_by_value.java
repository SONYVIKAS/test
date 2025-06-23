import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class DictionarySort {
    public static void main(String[] args) {
        Map<String, Integer> data = new HashMap<>();
        data.put("one", 1);
        data.put("two", 2);
        data.put("four", 4);
        data.put("three", 3);

        Map<String, Integer> sort_dict = new LinkedHashMap<>();
        data.entrySet()
           .stream()
           .sorted(Map.Entry.comparingByValue())
           .forEachOrdered(x -> sort_dict.put(x.getKey(), x.getValue()));

        System.out.println(sort_dict);
    }