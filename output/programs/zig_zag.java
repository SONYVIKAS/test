public int zigzag(int[] a) {
    int longest = 1;
    int currLength = 1;

    if (a.length == 2 && a[0]!= a[1]) {
        return a.length;
    }

    for (int i = 0; i < a.length - 2; i++) {
        int prev = a[i];
        int curr = a[i + 1];
        int nxt = a[i + 2];

        if ((prev < curr && curr > nxt) || (prev > curr && curr < nxt)) {
            if (nxt == a[a.length - 1]) {
                currLength += 2;
            } else {
                currLength += 1;
            }

            longest = Math.max(longest, currLength);
        } else {
            currLength += 1;
            longest = Math.max(longest, currLength);
            currLength = 1;
        }
    }

    return longest;