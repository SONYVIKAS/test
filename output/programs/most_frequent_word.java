public static void mostFrequentWord(String str) {
    Map<String, Integer> words = new HashMap<>();
    String[] list_of_words = str.split(" ");

    for (String word : list_of_words) {
        words.put(word, words.getOrDefault(word, 0) + 1);
    }

    List<String> mostFrequentWords = new ArrayList<>();
    int max_value = 0;

    for (Map.Entry<String, Integer> entry : words.entrySet()) {
        if (entry.getValue() > max_value) {
            max_value = entry.getValue();
        }
    }

    for (Map.Entry<String, Integer> entry : words.entrySet()) {
        if (entry.getValue() == max_value) {
            mostFrequentWords.add(entry.getKey());
        }
    }

    Collections.sort(mostFrequentWords);

    for (String word : mostFrequentWords) {
        System.out.print(word + " ");
    }