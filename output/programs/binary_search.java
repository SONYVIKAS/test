import java.util.stream.IntStream;

public class Main {

    // Using binary search, find val in range 1-100. Return # of guesses.
    public static int binarySearch(int val) {
        // Runtime: O(nlogn)

        // Check if val is between 1-100
        if (val < 1 || val > 100) {
            throw new IllegalArgumentException("Val must be between 1-100");
        }

        int numGuesses = 0;

        double guess = -1;
        double low = 0;
        double high = 101;

        while (guess != val) {
            numGuesses += 1;
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
        System.out.println(binarySearch(50)); // Should print 1
        System.out.println(binarySearch(25)); // Should print 2
        System.out.println(binarySearch(75)); // Should print 2
        System.out.println(binarySearch(31)); // Should print <= 7

        // Test maximum number of guesses for all values in range 1-100
        int maxGuesses = IntStream.rangeClosed(1, 100)
                                  .map(Main::binarySearch)
                                  .max()
                                  .getAsInt();
        System.out.println(maxGuesses); // Should print 7
    }