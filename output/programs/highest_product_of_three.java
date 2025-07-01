public static int highestProductOfThree(int[] nums) {
    if (nums.length < 3) {
        throw new IllegalArgumentException("Input array must have at least 3 integers");
    }

    int highest = nums[0];
    int lowest = nums[0];
    int highestTwo = nums[0] * nums[1];
    int lowestTwo = nums[0] * nums[1];
    int highestProduct = nums[0] * nums[1] * nums[2];

    for (int i = 2; i < nums.length; i++) {
        int currentNum = nums[i];
        highestProduct = Math.max(highestProduct, Math.max(highestTwo * currentNum, lowestTwo * currentNum));
        highestTwo = Math.max(highestTwo, highest * currentNum);
        lowestTwo = Math.min(lowestTwo, lowest * currentNum);
        highest = Math.max(highest, currentNum);
        lowest = Math.min(lowest, currentNum);
    }

    return highestProduct;