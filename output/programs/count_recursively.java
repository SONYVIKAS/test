public class ListCounter {
    public static int countRecursively(List<Object> lst) {
        if (lst.isEmpty()) {
            return 0;
        }

        return 1 + countRecursively(lst.subList(1, lst.size()));
    }

    public static void main(String[] args) {
        List<Object> lst = Arrays.asList(1, 2, 3);
        System.out.println(countRecursively(lst));
    }