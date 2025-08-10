import java.util.List;

public class ProductsOfAllInts {

    // Method to get products of all integers except at index without using division
    public static List<Integer> getProductsOfAllIntsExceptAtIndex(List<Integer> lst) {
        List<Integer> products = new ArrayList<>();

        for (int a = 0; a < lst.size(); a++) {
            int product = 1;

            for (int b = 0; b < lst.size(); b++) {
                if (a != b) {
                    product *= lst.get(b);
                }
            }
            products.add(product);
        }

        return products;
    }

    // Optimized method to get products of all integers except at index without using division
    public static List<Integer> getProductsOfAllIntsExceptAtIndexOptimized(List<Integer> lst) {
        List<Integer> products = new ArrayList<>();
        int product = 1;
        int productReverse = 1;
        List<Integer> productsBefore = new ArrayList<>();
        List<Integer> productsAfter = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            productsBefore.add(product);
            product *= lst.get(i);
        }

        for (int i = lst.size() - 1; i >= 0; i--) {
            productsAfter.add(productReverse);
            productReverse *= lst.get(i);
        }

        for (int i = 0; i < productsBefore.size(); i++) {
            products.add(productsAfter.get(productsAfter.size() - i - 1) * productsBefore.get(i));
        }

        return products;
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> test1 = List.of(1, 7, 3, 4);
        List<Integer> test2 = List.of(1, 0, 3, 4);

        // Testing the non-optimized method
        System.out.println(getProductsOfAllIntsExceptAtIndex(test1)); // [84, 12, 28, 21]
        System.out.println(getProductsOfAllIntsExceptAtIndex(test2)); // [0, 12, 0, 0]

        // Testing the optimized method
        System.out.println(getProductsOfAllIntsExceptAtIndexOptimized(test1)); // [84, 12, 28, 21]
        System.out.println(getProductsOfAllIntsExceptAtIndexOptimized(test2)); // [0, 12, 0, 0]
    }