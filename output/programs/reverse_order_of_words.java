
public class Main {
    public static void main(String[] args) {
        // Test the function
        List<Character> lst = Arrays.asList('p', 'r', 'a', 'c', 't', 'i', 'c', 'e', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'y', 'o', 'u');
        System.out.println(reverseOrderOfWords(lst));
    }

    public static List<Character> reverseOrderOfWords(List<Character> lst) {
        // Runtime: O(n)

        List<String> reversedArr = new ArrayList<>();
        StringBuilder words = new StringBuilder();
        for (char c : lst) {
            words.append(c);
        }
        // 'practice makes perfect you'
        String[] wordsArr = words.toString().split(" ");
        // ['practice', 'makes', 'perfect', 'you']
        for (int i = wordsArr.length - 1; i >= 0; i--) {
            reversedArr.add(wordsArr[i]);
        }
        // ['you', 'perfect', 'makes', 'practice']
        String reversedWords = String.join(" ", reversedArr);
        // 'you perfect makes practice'

        List<Character> result = new ArrayList<>();
        for (char c : reversedWords.toCharArray()) {
            result.add(c);
        }

        return result;
    }