
public class Main {
    public static void main(String[] args) {
        // Create a HashMap
        HashMap<String, Integer> data = new HashMap<>();
        data.put("one", 1);
        data.put("two", 2);
        data.put("four", 4);
        data.put("three", 3);

        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<>(data.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Put data from sorted list to HashMap
        HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            sortedMap.put(aa.getKey(), aa.getValue());
        }

        // Print the sorted HashMap
        System.out.println(sortedMap);
    }