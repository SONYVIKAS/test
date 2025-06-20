Map<String, Integer> data = new HashMap<String, Integer>();
data.put("one", 1);
data.put("two", 2);
data.put("four", 4);
data.put("three", 3);

Map<String, Integer> sort_dict = new HashMap<String, Integer>();
for (Map.Entry<String, Integer> entry : data.entrySet()) {
    sort_dict.put(entry.getKey(), entry.getValue());
}

for (Map.Entry<String, Integer> entry : sort_dict.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());