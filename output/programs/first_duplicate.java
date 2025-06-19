public class FirstDuplicateNumber {
    public static int firstDuplicate(int[] a) {
        int lowestIndex = a.length;
        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            for (int j = i + 1; j < a.length; j++) {
                if (num == a[j]) {
                    if (j < lowestIndex) {
                        lowestIndex = j;
                    }
                }
            }
        }
        if (lowestIndex < a.length) {
            return a[lowestIndex];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 3, 1, 5, 2};
        int firstDuplicateNumber = firstDuplicate(a);
        System.out.println("First duplicate number: " + firstDuplicateNumber);
    }