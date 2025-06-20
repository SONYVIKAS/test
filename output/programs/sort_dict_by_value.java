Map<String, Integer> data = new HashMap<>();
data.put("one", 1);
data.put("two", 2);
data.put("four", 4);
data.put("three", 3);

Map<String, Integer> sortDict = new HashMap<>();
List<Map.Entry<String, Integer>> entries = new ArrayList<>(data.entrySet());
Collections.sort(entries, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
for (Map.Entry<String, Integer> entry : entries) {
    sortDict.put(entry.getKey(), entry.getValue());
}
