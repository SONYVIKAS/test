
public class Main {
    public static void main(String[] args) {
        // Test cases
        System.out.println(zigzag(new int[]{9, 8, 8, 5, 3, 5, 3, 2, 8, 6})); // 4
        System.out.println(zigzag(new int[]{2, 3, 1, 0, 2})); // 3
        System.out.println(zigzag(new int[]{1, 2, 3, 2, 1})); // 3
        System.out.println(zigzag(new int[]{2, 3, 1, 4, 2})); // 5
        System.out.println(zigzag(new int[]{1, 2, 0, 3, 2, 1, 3, 2, 4, 0})); // 6
        System.out.println(zigzag(new int[]{1, 2})); // 2
        System.out.println(zigzag(new int[]{1, 2, 1})); // 3
        System.out.println(zigzag(new int[]{1, 1})); // 1
    }

    // Iterative zigzag function
    public static int zigzag(int[] a) {
        int longest = 1;
        int curr_length = 1;

        if (a.length == 2 && a[0] != a[1]) {
            return a.length;
        }

        for (int i = 0; i < a.length - 2; i++) {
            int prev = a[i];
            int curr = a[i + 1];
            int nxt = a[i + 2];

            if ((prev < curr && curr > nxt) || (prev > curr && curr < nxt)) {
                if (nxt == a[a.length - 1]) {
                    curr_length += 2;
                } else {
                    curr_length += 1;
                }

                longest = Math.max(longest, curr_length);
            } else {
                curr_length += 1;
                longest = Math.max(longest, curr_length);
                curr_length = 1;
            }
        }

        return longest;
    }

    // Recursive zigzag function
    public static int zigzag_recursive(int[] a) {
        if (a.length < 2) {
            return a.length;
        }

        if (a.length == 2 && a[0] != a[1]) {
            return a.length;
        }

        int longest = 1;
        int i = 1;
        boolean good = true;

        while (good && i < a.length - 1) {
            int curr = a[i];
            int prev = a[i - 1];
            int nxt = a[i + 1];

            if ((prev < curr && curr > nxt) || (prev > curr && curr < nxt)) {
                i += 1;
                if (i == a.length - 1) {
                    longest += 1;
                }
            } else {
                good = false;
            }

            longest += 1;
        }

        return Math.max(longest, zigzag_recursive(Arrays.copyOfRange(a, i, a.length)));
    }