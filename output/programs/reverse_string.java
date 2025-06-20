  public static String reverseString(String string) {
      if (string.isEmpty()) {
          return string;
      }
      return reverseString(string.substring(1)) + string.charAt(0);
  }