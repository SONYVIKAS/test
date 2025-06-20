import java.util.List;
import java.util.ArrayList;

public class ProductOfAllIntegersExceptAtIndex {
    public static List<Integer> getProductsOfAllIntegersExceptAtIndex(List<Integer> integers) {
        List<Integer> products = new ArrayList<>();

        for (int i = 0; i < integers.size(); i++) {
            int product = 1;

            for (int j = 0; j < integers.size(); j++) {
                if (i!= j) {
                    product *= integers.get(j);
                }
            }

            products.add(product);
        }

        return products;
    }

    public static List<Integer> getProductsOfAllIntegersExceptAtIndexOptimized(List<Integer> integers) {
        List<Integer> products = new ArrayList<>();
        int product = 1;
        int productReverse = 1;
        List<Integer> productsBefore = new ArrayList<>();
        List<Integer> productsAfter = new ArrayList<>();

        for (int i = 0; i < integers.size(); i++) {
            productsBefore.add(product);
            product *= integers.get(i);
        }

        for (int i = integers.size() - 1; i >= 0; i--) {
            productsAfter.add(productReverse);
            productReverse *= integers.get(i);
        }

        for (int i = 0; i < productsBefore.size(); i++) {
            products.add(productsAfter.get(productsAfter.size() - i - 1) * productsBefore.get(i));
        }

        return products;
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(List.of(1, 7, 3, 4));
        List<Integer> expectedOutput = new ArrayList<>(List.of(84, 12, 28, 21));

        List<Integer> output = getProductsOfAllIntegersExceptAtIndex(integers);
        if (output.equals(expectedOutput)) {
            System.out.println("Test 1: Pass");
        } else {
            System.out.println("Test 1: Fail");
        }

        integers = new ArrayList<>(List.of(1, 0, 3, 4));
        expectedOutput = new ArrayList<>(List.of(0, 12, 0, 0));

        output = getProductsOfAllIntegersExceptAtIndex(integers);
        if (output.equals(expectedOutput)) {
            System.out.println("Test 2: Pass");
        } else {
            System.out.println("Test 2: Fail");
        }

        integers = new ArrayList<>(List.of(1, 7, 3, 4));
        expectedOutput = new ArrayList<>(List.of(84, 12, 28, 21));

        output = getProductsOfAllIntegersExceptAtIndexOptimized(integers);
        if (output.equals(expectedOutput)) {
            System.out.println("Test 3: Pass");
        } else {
            System.out.println("Test 3: Fail");
        }

        integers = new ArrayList<>(List.of(1, 0, 3, 4));
        expectedOutput = new ArrayList<>(List.of(0, 12, 0, 0));

        output = getProductsOfAllIntegersExceptAtIndexOptimized(integers);
        if (output.equals(expectedOutput)) {
            System.out.println("Test 4: Pass");
        } else {
            System.out.println("Test 4: Fail");
        }
    }