
public class SortDictByValue {
    public static void main(String[] args) {
        // Initialize the map with unsorted values
        Map<String, Integer> data = new HashMap<>();
        data.put("one", 1);
        data.put("two", 2);
        data.put("four", 4);
        data.put("three", 3);

        // Create a list from the map entries
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(data.entrySet());

        // Sort the list based on the values
        entries.sort(Map.Entry.comparingByValue());

        // Create a LinkedHashMap to maintain the order of insertion
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // Print the sorted map
        System.out.println(sortedMap);
    }