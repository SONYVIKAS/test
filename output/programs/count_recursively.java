public class JavaConversion {
    public static int countRecursively(int[] lst) {
        if (lst.length == 0) {
            return 0;
        }

        return 1 + countRecursively(Arrays.copyOfRange(lst, 1, lst.length));
    }

    public static void main(String[] args) {
        int[] lst1 = {};
        int[] lst2 = {1, 2, 3};

        System.out.println(countRecursively(lst1));
        System.out.println(countRecursively(lst2));
    }