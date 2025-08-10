
public class HighestProductOfThree {

    // Method to find the highest product of three integers in a list
    public static int highestProductOfThree(List<Integer> listOfInts) {
        // Initialize variables for highest product, highest and lowest values
        int highestProduct = listOfInts.get(0) * listOfInts.get(1) * listOfInts.get(2);
        int highest = listOfInts.get(0);
        int lowest = listOfInts.get(0);
        int highestTwo = listOfInts.get(0) * listOfInts.get(1);
        int lowestTwo = listOfInts.get(0) * listOfInts.get(1);

        // If the list has exactly three integers, return the product
        if (listOfInts.size() == 3) {
            return highestProduct;
        }

        // Iterate through the list starting from the third element
        for (int i = 2; i < listOfInts.size() - 1; i++) {
            int product = listOfInts.get(i) * listOfInts.get(i + 1);
            int currentNum = listOfInts.get(i);

            // Update highest and highestTwo if currentNum is greater
            if (currentNum > highest) {
                highest = currentNum;
                if (product > highestTwo) {
                    highestTwo = product;
                }
            } 
            // Update lowest and lowestTwo if currentNum is smaller
            else if (currentNum < lowest) {
                lowest = currentNum;
                if (product < lowestTwo) {
                    lowestTwo = product;
                }
            }
        }

        // Determine the highest product based on calculated values
        if (highestTwo * highest > lowestTwo * lowest) {
            highestProduct = highestTwo * highest;
        } else if (highestTwo * highest < lowestTwo * lowest) {
            highestProduct = lowestTwo * lowest;
        } else if (highestTwo * lowest < lowestTwo * highest) {
            highestProduct = lowestTwo * highest;
        } else {
            highestProduct = highestTwo * lowest;
        }

        return highestProduct;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(highestProductOfThree(List.of(1, 2, 3, 4, 5))); // 60
        System.out.println(highestProductOfThree(List.of(1, 2, 3, 4, -5))); // 24
        System.out.println(highestProductOfThree(List.of(-10, -10, 1, 3, 2))); // 300
        System.out.println(highestProductOfThree(List.of(10, 2, 5))); // 100
        System.out.println(highestProductOfThree(List.of(5, 4, 3, 2, 1))); // 60
    }