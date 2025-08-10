
public class RecursiveIndex {

    // Method to find the index of needle in haystack using recursion
    public static Integer recursiveIndex(String needle, List<String> haystack) {
        // Base case: if haystack is empty or needle is not in haystack, return null
        if (haystack.isEmpty() || !haystack.contains(needle)) {
            return null;
        }

        // If the first element is the needle, return 0
        if (needle.equals(haystack.get(0))) {
            return 0;
        }

        // Recursive call on the sublist excluding the first element
        Integer index = recursiveIndex(needle, haystack.subList(1, haystack.size()));

        // If index is not null, add 1 to account for the excluded element
        return (index == null) ? null : 1 + index;
    }

    // Another recursive method to find the index of needle in haystack
    public static Integer recursiveIndex2(String needle, List<String> haystack) {
        // Base case: if haystack is empty or needle is not in haystack, return null
        if (haystack.isEmpty() || !haystack.contains(needle)) {
            return null;
        }

        // If the first element is the needle, return 0
        if (needle.equals(haystack.get(0))) {
            return 0;
        }

        // Recursive call on the sublist excluding the first element
        Integer index = recursiveIndex2(needle, haystack.subList(1, haystack.size()));

        // If index is not null, add 1 to account for the excluded element
        return (index == null) ? null : 1 + index;
    }

    // Third recursive method using a helper function
    public static Integer recursiveIndex3(String needle, List<String> haystack) {
        return _recursiveIndex3(needle, haystack, 0);
    }

    // Helper method for recursiveIndex3
    private static Integer _recursiveIndex3(String needle, List<String> haystack, int count) {
        // Base case: if count reaches the size of haystack, return null
        if (count == haystack.size()) {
            return null;
        }

        // If the current element is the needle, return the current count
        if (needle.equals(haystack.get(count))) {
            return count;
        }

        // Recursive call with incremented count
        return _recursiveIndex3(needle, haystack, count + 1);
    }

    public static void main(String[] args) {
        // Test cases
        List<String> lst = List.of("hey", "there", "you");

        System.out.println(recursiveIndex("hey", lst)); // Output: 0
        System.out.println(recursiveIndex("you", lst)); // Output: 2
        System.out.println(recursiveIndex("porcupine", lst) == null); // Output: true

        System.out.println(recursiveIndex2("hey", lst)); // Output: 0
        System.out.println(recursiveIndex2("you", lst)); // Output: 2
        System.out.println(recursiveIndex2("porcupine", lst) == null); // Output: true

        System.out.println(recursiveIndex3("hey", lst)); // Output: 0
        System.out.println(recursiveIndex3("you", lst)); // Output: 2
        System.out.println(recursiveIndex3("porcupine", lst) == null); // Output: true
    }