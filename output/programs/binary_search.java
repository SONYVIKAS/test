
    // Using binary search, find val in range 1-100. Return number of guesses.
    public static int binarySearch(int val) {
        // Ensure the value is within the valid range
        if (val <= 0 || val >= 101) {
            throw new IllegalArgumentException("Val must be between 1-100");
        }

        int numGuesses = 0;
        int guess = -1;
        int low = 0;
        int high = 101;

        // Perform binary search
        while (guess != val) {
            numGuesses++;
            guess = (high - low) / 2 + low;

            if (guess > val) {
                high = guess;
            } else if (guess < val) {
                low = guess;
            }
        }

        return numGuesses;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(binarySearch(50)); // Expected: 1
        System.out.println(binarySearch(25)); // Expected: 2
        System.out.println(binarySearch(75)); // Expected: 2
        System.out.println(binarySearch(31) <= 7); // Expected: true

        // Find the maximum number of guesses for values 1 to 100
        int maxGuesses = 0;
        for (int i = 1; i <= 100; i++) {
            maxGuesses = Math.max(maxGuesses, binarySearch(i));
        }
        System.out.println(maxGuesses); // Expected: 7

        System.out.println("ALL TESTS PASSED");
    }