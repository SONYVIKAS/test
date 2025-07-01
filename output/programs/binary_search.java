public class BinarySearch {
    public static int binarySearch(int val) {
        if (val < 1 || val > 100) {
            throw new IllegalArgumentException("Value must be between 1 and 100");
        }

        int numGuesses = 0;
        int guess = 0;
        int low = 1;
        int high = 100;

        while (guess!= val) {
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