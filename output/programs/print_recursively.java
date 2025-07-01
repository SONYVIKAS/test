  public class PrintRecursively {
      public static void printRecursively(int[] lst) {
          if (lst.length == 0) {
              return;
          }

          System.out.println(lst[0]);
          printRecursively(Arrays.copyOfRange(lst, 1, lst.length));
      }

      public static void main(String[] args) {
          int[] lst = {1, 2, 3};
          printRecursively(lst);
      }
  }