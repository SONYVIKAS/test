public class HighestProductOfThree {
    public static void main(String[] args) {
        int[] list1 = {1, 2, 3, 4, 5};
        int[] list2 = {1, 2, 3, 4, -5};
        int[] list3 = {-10, -10, 1, 3, 2};
        int[] list4 = {10, 2, 5};
        int[] list5 = {5, 4, 3, 2, 1};

        System.out.println(highestProductOfThree(list1));
        System.out.println(highestProductOfThree(list2));
        System.out.println(highestProductOfThree(list3));
        System.out.println(highestProductOfThree(list4));
        System.out.println(highestProductOfThree(list5));
    }

    public static int highestProductOfThree(int[] list) {
        int highestProduct = list[0] * list[1] * list[2];
        int highest = list[0];
        int lowest = list[0];
        int highestTwo = list[0] * list[1];
        int lowestTwo = list[0] * list[1];

        if (list.length == 3) {
            return highestProduct;
        }

        for (int i = 2; i < list.length - 1; i++) {
            int product = list[i] * list[i + 1];
            int currentNum = list[i];

            if (currentNum > highest) {
                highest = currentNum;
                if (product > highestTwo) {
                    highestTwo = product;
                }
            } else if (currentNum < lowest) {
                lowest = currentNum;
                if (product < lowestTwo) {
                    lowestTwo = product;
                }
            }
        }

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