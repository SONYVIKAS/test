
public class CountRecursively {

    // Method to count the number of items in a list using recursion
    public static int countRecursively(List<?> lst) {
        // Base case: if the list is empty, return 0
        if (lst.isEmpty()) {
            return 0;
        }
        // Recursive case: count the first element and recurse on the rest of the list
        return 1 + countRecursively(lst.subList(1, lst.size()));
    }

    public static void main(String[] args) {
        // Test cases
        assert countRecursively(List.of()) == 0 : "Test case 1 failed";
        assert countRecursively(List.of(1, 2, 3)) == 3 : "Test case 2 failed";

        // If no assertion errors, print success message
        System.out.println("ALL TESTS PASSED!");
    }