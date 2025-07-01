  public static int firstDuplicate(int[] a) {
      for (int i = 0; i < a.length; i++) {
          int num = a[i];
          for (int j = i + 1; j < a.length; j++) {
              if (a[j] == num) {
                  return num;
              }
          }
      }
      return -1;
  }