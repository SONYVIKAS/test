public static int binarySearch(int target, int[] array) {
    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
        int mid = (low + high) / 2;

        if (target == array[mid]) {
            return mid;
        } else if (target < array[mid]) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return -1;