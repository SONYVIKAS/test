public static int highestProductOfThree(int[] ints) {
    int highestProduct = ints[0] * ints[1] * ints[2];
    int highest = ints[0];
    int lowest = ints[0];
    int highestTwo = ints[0] * ints[1];
    int lowestTwo = ints[0] * ints[1];

    if (ints.length == 3) {
        return highestProduct;
    }

    for (int i = 2; i < ints.length - 1; i++) {
        int product = ints[i] * ints[i + 1];
        int currentNum = ints[i];

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