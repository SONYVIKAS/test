  public class RecursiveIndex {
      public static Integer recursiveIndex(String needle, String[] haystack) {
          if (haystack.length == 0 ||!contains(needle, haystack)) {
              return null;
          }
          return recursiveIndexHelper(needle, haystack, 0);
      }
      private static Integer recursiveIndexHelper(String needle, String[] haystack, Integer count) {
          if (haystack.length == count) {
              return null;
          }
          if (needle.equals(haystack[count])) {
              return count;
          }
          return recursiveIndexHelper(needle, haystack, count + 1);
      }
      private static boolean contains(String needle, String[] haystack) {
          for (String item : haystack) {
              if (item.equals(needle)) {
                  return true;
              }
          }
          return false;
      }
  }