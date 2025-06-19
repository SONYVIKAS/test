public static int[] getProductsOfAllIntsExceptAtIndex(int[] ints) {
    int[] products = new int[ints.length];

    for (int a = 0; a < ints.length; a++) {
        int product = 1;

        for (int b = 0; b < ints.length; b++) {
            if (a!= b) {
                product *= ints[b];
            }
        }

        products[a] = product;
    }

    return products;