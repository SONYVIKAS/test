  import java.util.Arrays;

  public class FirstDuplicate {
      public static int firstDuplicate(int[] a) {
          int lowestIndex = a.length;
          for (int i = 0; i < a.length; i++) {
              int num = a[i];
              for (int j = i + 1; j < a.length; j++) {
                  if (a[j] == num) {
                      if (j < lowestIndex) {
                          lowestIndex = j;
                      }
                  }
              }
          }
          if (lowestIndex < a.length) {
              return a[lowestIndex];
          }
          return -1;
      }

      public static int firstDuplicateOptimized(int[] a) {
          for (int i = 0; i < a.length; i++) {
              int index = Math.abs(a[i]) - 1;
              if (a[index] > 0) {
                  a[index] = -a[index];
              } else {
                  return Math.abs(a[i]);
              }
          }
          return -1;
      }
  }