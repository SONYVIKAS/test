public class ListCounter {
    public static int countRecursively(List<Object> lst) {
        if (lst.isEmpty()) {
            return 0;
        }

        return 1 + countRecursively(lst.subList(1, lst.size()));
    }

    public static void main(String[] args) {
        List<Object> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        int count = countRecursively(lst);
        System.out.println("Number of items in the list: " + count);
    }