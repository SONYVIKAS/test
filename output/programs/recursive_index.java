  public static int recursiveIndex(String needle, String[] haystack) {
      if (haystack.length == 0) {
          return -1;
      }
      if (haystack[0].equals(needle)) {
          return 0;
      }
      int index = recursiveIndex(needle, Arrays.copyOfRange(haystack, 1, haystack.length));
      if (index == -1) {
          return -1;
      }
      return index + 1;
  }