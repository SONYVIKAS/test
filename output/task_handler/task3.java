  public class CustomsCheck {
      public static void main(String[] args) {
          String[] items = {"book", "pencil", "paper", "pencil", "book", "paper", "book"};
          int[] weights = {50, 20, 10, 20, 50, 10, 50};
          int[] values = {100, 50, 20, 50, 100, 20, 100};
          int totalWeight = 0;
          int totalValue = 0;
          for (int i = 0; i < items.length; i++) {
              totalWeight += weights[i];
              totalValue += values[i];
          }
          if (totalWeight <= 100 && totalValue <= 200) {
              System.out.println("Customs clearance allowed");
          } else {
              System.out.println("Customs clearance denied");
          }
      }
  }