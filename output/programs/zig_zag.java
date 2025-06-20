  public int zigzag(int[] a) {
      int longest = 1;
      int currLength = 1;
      for (int i = 0; i < a.length - 2; i++) {
          int prev = a[i];
          int curr = a[i + 1];
          int next = a[i + 2];
          if ((prev < curr && curr > next) || (prev > curr && curr < next)) {
              if (next == a[a.length - 1]) {
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
  }