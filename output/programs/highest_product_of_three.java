
public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(highestProductOfThree(new int[]{1,2,3,4,5})); // 60
        System.out.println(highestProductOfThree(new int[]{1,2,3,4,-5})); // 24
        System.out.println(highestProductOfThree(new int[]{-10,-10,1,3,2})); // 300
        System.out.println(highestProductOfThree(new int[]{10,2,5})); // 100
        System.out.println(highestProductOfThree(new int[]{5,4,3,2,1})); // 60
    }

    public static int highestProductOfThree(int[] list_of_ints) {
        // Runtime: O(n)
        // Spacetime: O(1)

        int highest_product = list_of_ints[0] * list_of_ints[1] * list_of_ints[2];
        int highest = list_of_ints[0];
        int lowest = list_of_ints[0];
        int highest_two = list_of_ints[0] * list_of_ints[1];
        int lowest_two = list_of_ints[0] * list_of_ints[1];

        if (list_of_ints.length == 3) {
            return highest_product;
        }

        for (int i = 2; i < list_of_ints.length - 1; i++) {
            int product = list_of_ints[i] * list_of_ints[i + 1];
            int current_num = list_of_ints[i];

            if (current_num > highest) {
                highest = current_num;
                if (product > highest_two) {
                    highest_two = product;
                }
            } else if (current_num < lowest) {
                lowest = current_num;
                if (product < lowest_two) {
                    lowest_two = product;
                }
            }
        }

        if (highest_two * highest > lowest_two * lowest) {
            highest_product = highest_two * highest;
        } else if (highest_two * highest < lowest_two * lowest) {
            highest_product = lowest_two * lowest;
        } else if (highest_two * lowest < lowest_two * highest) {
            highest_product = lowest_two * highest;
        } else {
            highest_product = highest_two * lowest;
        }

        return highest_product;
    }