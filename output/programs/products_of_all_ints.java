  import java.util.List;
  import java.util.ArrayList;

  public class ProductExceptIndex {
      public static List<Integer> getProductsOfAllIntsExceptAtIndex(List<Integer> ints) {
          List<Integer> products = new ArrayList<>();

          for (int i = 0; i < ints.size(); i++) {
              int product = 1;
              for (int j = 0; j < ints.size(); j++) {
                  if (j!= i) {
                      product *= ints.get(j);
                  }
              }
              products.add(product);
          }

          return products;
      }
  }