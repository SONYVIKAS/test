import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lst = Arrays.asList("hey", "there", "you");

        System.out.println(recursiveIndex("hey", lst)); // 0
        System.out.println(recursiveIndex("you", lst)); // 2
        System.out.println(recursiveIndex("porcupine", lst) == null); // true

        System.out.println(recursiveIndex2("hey", lst)); // 0
        System.out.println(recursiveIndex2("you", lst)); // 2
        System.out.println(recursiveIndex2("porcupine", lst) == null); // true

        System.out.println(recursiveIndex3("hey", lst)); // 0
        System.out.println(recursiveIndex3("you", lst)); // 2
        System.out.println(recursiveIndex3("porcupine", lst) == null); // true
    }

    // Given list (haystack), return index (0-based) of needle in the list.
    // Return null if needle is not in haystack.
    // Do this with recursion. You MAY NOT USE A `for` OR `while` LOOP.
    public static Integer recursiveIndex(String needle, List<String> haystack) {
        if (haystack.isEmpty() || !haystack.contains(needle)) {
            return null;
        }

        if (needle.equals(haystack.get(0))) {
            return 0;
        }

        Integer count = recursiveIndex(needle, haystack.subList(1, haystack.size()));
        return count == null ? null : 1 + count;
    }

    // Given list (haystack), return index (0-based) of needle in the list.
    // Return null if needle is not in haystack.
    // Do this with recursion. You MAY NOT USE A `for` OR `while` LOOP.
    public static Integer recursiveIndex2(String needle, List<String> haystack) {
        if (haystack.isEmpty() || !haystack.contains(needle)) {
            return null;
        }

        if (needle.equals(haystack.get(0))) {
            return 0;
        }

        return 1 + recursiveIndex2(needle, haystack.subList(1, haystack.size()));
    }

    // Given list (haystack), return index (0-based) of needle in the list.
    // Return null if needle is not in haystack.
    // Do this with recursion. You MAY NOT USE A `for` OR `while` LOOP.
    public static Integer recursiveIndex3(String needle, List<String> haystack) {
        return recursiveIndex3Helper(needle, haystack, 0);
    }

    private static Integer recursiveIndex3Helper(String needle, List<String> haystack, int count) {
        if (haystack.size() == count) {
            return null;
        }

        if (needle.equals(haystack.get(count))) {
            return count;
        }

        return recursiveIndex3Helper(needle, haystack, count + 1);
    }