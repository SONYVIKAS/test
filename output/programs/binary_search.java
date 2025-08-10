import java.lang.Math;

public class Main {
    // In this challenge, you'll make binary search for the classic children's guessing game of "pick a number from 1 to 100".

    // Using binary search, find val in range 1-100. Return # of guesses.
    public static int binarySearch(int val) {
        // Runtime: O(nlogn)

        // Check if the value is within the range
        if (val < 1 || val > 100) {
            throw new IllegalArgumentException("Val must be between 1-100");
        }

        int numGuesses = 0;

        double guess = -1;
        double low = 0;
        double high = 101;

        while (guess != val) {
            numGuesses += 1;
            guess = Math.floor((high - low) / 2 + low);

            if (guess > val) {
                high = guess;
            } else if (guess < val) {
                low = guess;
            }
        }

        return numGuesses;
    }

    public static void main(String[] args) {
        // Test the binary search function
        System.out.println(binarySearch(50)); // Should print 1
        System.out.println(binarySearch(25)); // Should print 2
        System.out.println(binarySearch(75)); // Should print 2
        System.out.println(binarySearch(31)); // Should print <= 7

        // Test the maximum number of guesses for all values in the range
        int maxGuesses = 0;
        for (int i = 1; i <= 100; i++) {
            maxGuesses = Math.max(maxGuesses, binarySearch(i));
        }
        System.out.println(maxGuesses); // Should print 7
    }