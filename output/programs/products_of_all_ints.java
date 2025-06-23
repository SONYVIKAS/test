public static int[] getProductsOfAllIntsExceptAtIndex(int[] arr) {
    int[] products = new int[arr.length];

    for (int a = 0; a < arr.length; a++) {
        int product = 1;

        for (int b = 0; b < arr.length; b++) {
            if (a!= b) {
                product *= arr[b];
            }
        }

        products[a] = product;
    }

    return products;