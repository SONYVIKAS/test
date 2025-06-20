  public int firstDuplicate(int[] a) {
      int lowestIndex = a.length;
      for (int i = 0; i < a.length; i++) {
          int num = a[i];
          for (int j = i + 1; j < a.length; j++) {
              if (a[j] == num) {
                  if (j < lowestIndex) {
                      lowestIndex = j;
                  }
                  break;
              }
          }
      }
      if (lowestIndex < a.length) {
          return a[lowestIndex];
      }
      return -1;
  }