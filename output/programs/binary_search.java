public static int binarySearch(int val) {
    assert 0 < val && val < 101 : "Val must be between 1-100";

    int numGuesses = 0;
    int guess = 0;
    int low = 0;
    int high = 101;

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