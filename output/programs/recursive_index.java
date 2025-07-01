public class RecursiveIndex {
    public static int recursiveIndex(String needle, String[] haystack) {
        if (haystack.length == 0 ||!contains(needle, haystack)) {
            return -1;
        }

        return recursiveIndexHelper(needle, haystack, 0);
    }

    private static int recursiveIndexHelper(String needle, String[] haystack, int index) {
        if (index == haystack.length) {
            return -1;
        }

        if (needle.equals(haystack[index])) {
            return index;
        }

        return recursiveIndexHelper(needle, haystack, index + 1);
    }

    private static boolean contains(String needle, String[] haystack) {
        for (String item : haystack) {
            if (item.equals(needle)) {
                return true;
            }
        }
        return false;
    }