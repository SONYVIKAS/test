import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Test the printRecursively function
        printRecursively(Arrays.asList(1, 2, 3));
    }

    /**
     * Print items in the list, using recursion.
     *
     * @param list The list to print
     */
    public static void printRecursively(List<Integer> list) {
        // If the list is empty, return
        if (list.isEmpty()) {
            return;
        }

        // Print the first item in the list
        System.out.println(list.get(0));

        // Recursively call the function with the rest of the list
        printRecursively(list.subList(1, list.size()));
    }