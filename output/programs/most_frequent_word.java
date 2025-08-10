
public class Main {
    public static void main(String[] args) {
        mostFrequentWord("hello my name is hello joanne");
        mostFrequentWord("hello my name is hello joanne is");
        mostFrequentWord("hello my name is joanne");
    }

    public static void mostFrequentWord(String str) {
        // Split the string into words
        String[] list_of_words = str.split(" ");

        // Create a map to store the frequency of each word
        Map<String, Integer> words = new HashMap<>();

        // Iterate over the words and update their count in the map
        for (String word : list_of_words) {
            words.put(word, words.getOrDefault(word, 0) + 1);
        }

        // Find the maximum frequency
        int max_value = Collections.max(words.values());

        // Create a list to store the most frequent words
        List<String> most_frequent_words = new ArrayList<>();

        // Iterate over the map and add the most frequent words to the list
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (entry.getValue() == max_value) {
                most_frequent_words.add(entry.getKey());
            }
        }

        // Sort the list of most frequent words
        Collections.sort(most_frequent_words);

        // Print the most frequent words
        for (String word : most_frequent_words) {
            System.out.print(word + " ");
        }

        // Print a newline for readability
        System.out.println();
    }