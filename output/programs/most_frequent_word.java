
public class MostFrequentWord {

    // Method to find and print the most frequent words in alphabetical order
    public static void mostFrequentWord(String str) {
        // Split the input string into words
        String[] listOfWords = str.split(" ");
        
        // Use a HashMap to count occurrences of each word
        Map<String, Integer> words = new HashMap<>();
        for (String word : listOfWords) {
            words.put(word, words.getOrDefault(word, 0) + 1);
        }

        // Find the maximum frequency of any word
        int maxValue = Collections.max(words.values());

        // List to store words with the maximum frequency
        List<String> mostFrequentWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (entry.getValue() == maxValue) {
                mostFrequentWords.add(entry.getKey());
            }
        }

        // Sort the most frequent words alphabetically
        Collections.sort(mostFrequentWords);

        // Print the sorted words
        for (String word : mostFrequentWords) {
            System.out.print(word + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test cases
        mostFrequentWord("hello my name is hello joanne");
        mostFrequentWord("hello my name is hello joanne is");
        mostFrequentWord("hello my name is joanne");
    }