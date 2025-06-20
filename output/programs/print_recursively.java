public class RecursivePrinter {
    public static void printRecursively(int[] lst) {
        if (lst.length == 0) {
            return;
        }

        System.out.println(lst[0]);

        if (lst.length > 1) {
            int[] subList = new int[lst.length - 1];
            System.arraycopy(lst, 1, subList, 0, lst.length - 1);
            printRecursively(subList);
        }
    }

    public static void main(String[] args) {
        int[] lst = {1, 2, 3};
        printRecursively(lst);
    }