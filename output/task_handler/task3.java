  public class CustomsCheck {
      public static void main(String[] args) {
          int weight = 30;
          String country = "United States";
          boolean isOverweight = weight > 20;
          boolean isFromChina = country.equals("China");
          if (isOverweight) {
              System.out.println("You are overweight and will be fined $25.");
          } else if (isFromChina) {
              System.out.println("You are from China and will be fined $10.");
          } else {
              System.out.println("You are not overweight and not from China. You can proceed.");
          }
      }
  }