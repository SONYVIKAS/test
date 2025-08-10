
public class Main {

    // Using recursion, return the sum of numbers in a list.
    public static int sumList(List<Integer> nums) {
        // Runtime: O(n)
        // Spacetime: O(1)

        if (nums.isEmpty()) {
            return 0;
        }

        return nums.get(0) + sumList(nums.subList(1, nums.size()));
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(sumList(List.of(5, 5))); // Expected output: 10
        System.out.println(sumList(List.of(-5, 10, 4))); // Expected output: 9
        System.out.println(sumList(List.of(20))); // Expected output: 20
        System.out.println(sumList(List.of())); // Expected output: 0
    }