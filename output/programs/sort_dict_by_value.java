import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class SortDictionary {
    public static void main(String[] args) {
        Map<String, Integer> data = new HashMap<>();
        data.put("one", 1);
        data.put("two", 2);
        data.put("four", 4);
        data.put("three", 3);

        Map<String, Integer> sortDict = new LinkedHashMap<>();
        data.entrySet().stream()
           .sorted(Comparator.comparingInt(Map.Entry::getValue))
           .forEach(entry -> sortDict.put(entry.getKey(), entry.getValue()));

        System.out.println(sortDict);
    }