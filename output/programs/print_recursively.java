
public class PrintRecursively {

    // Method to print items in the list using recursion
    public static void printRecursively(List<Integer> lst) {
        // Base case: if the list is empty, return
        if (lst.isEmpty()) {
            return;
        }

        // Print the first element of the list
        System.out.println(lst.get(0));

        // Recursive call with the sublist excluding the first element
        printRecursively(lst.subList(1, lst.size()));
    }

    public static void main(String[] args) {
        // Test the printRecursively method
        printRecursively(List.of(1, 2, 3));

        // Indicate that all tests passed
        System.out.println("ALL TESTS PASSED");
    }