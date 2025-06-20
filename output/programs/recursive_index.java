public class RecursiveIndex {
    public static Integer recursiveIndex(String needle, List<String> haystack) {
        if (haystack.isEmpty() ||!haystack.contains(needle)) {
            return null;
        }

        int count = 0;

        if (needle.equals(haystack.get(0))) {
            return count;
        }

        count += 1 + recursiveIndex(needle, haystack.subList(1, haystack.size()));

        return count;
    }

    public static Integer recursiveIndex2(String needle, List<String> haystack) {
        if (haystack.isEmpty() ||!haystack.contains(needle)) {
            return null;
        }

        if (needle.equals(haystack.get(0))) {
            return 0;
        }

        return 1 + recursiveIndex2(needle, haystack.subList(1, haystack.size()));
    }

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