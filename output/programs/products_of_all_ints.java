
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 4};
        int[] result = getProductsOfAllIntsExceptAtIndex(arr);
        System.out.println(Arrays.toString(result)); // prints [84, 12, 28, 21]

        int[] arr2 = {1, 0, 3, 4};
        int[] result2 = getProductsOfAllIntsExceptAtIndexOptimized(arr2);
        System.out.println(Arrays.toString(result2)); // prints [0, 12, 0, 0]
    }

    // Takes a list of integers and returns a list of the products except the integer at that index. Do not use division.
    public static int[] getProductsOfAllIntsExceptAtIndex(int[] arr) {
        // Runtime: O(n^2)
        // Spacetime: O(n^2)

        int[] products = new int[arr.length];

        for (int a = 0; a < arr.length; a++) {
            int product = 1;

            for (int b = 0; b < arr.length; b++) {
                if (a != b) {
                    product *= arr[b];
                }
            }
            products[a] = product;
        }

        return products;
    }

    // Takes a list of integers and returns a list of the products except the integer at that index. Do not use division.
    public static int[] getProductsOfAllIntsExceptAtIndexOptimized(int[] arr) {
        // Runtime: O(n)
        // Spacetime: O(n)

        int[] products = new int[arr.length];
        int product = 1;
        int productReverse = 1;
        int[] productsBefore = new int[arr.length];
        int[] productsAfter = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            productsBefore[i] = product;
            product *= arr[i];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            productsAfter[i] = productReverse;
            productReverse *= arr[i];
        }

        for (int i = 0; i < productsBefore.length; i++) {
            products[i] = productsAfter[i] * productsBefore[i];
        }

        return products;
    }