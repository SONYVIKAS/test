public class CountRecursively {
    public static int countRecursively(int[] lst) {
        if (lst.length == 0) {
            return 0;
        }

        return 1 + countRecursively(lst);
    }

    public static void main(String[] args) {
        int[] lst = {1, 2, 3};
        System.out.println(countRecursively(lst));
    }