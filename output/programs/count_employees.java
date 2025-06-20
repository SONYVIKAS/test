public class Node {
    private String name;
    private List<Node> children;

    public Node(String name, List<Node> children) {
        this.name = name;
        this.children = children;
    }

    public int countEmployees() {
        if (children == null || children.isEmpty()) {
            return 0;
        }

        int count = 0;

        for (Node child : children) {
            count += 1 + child.countEmployees();
        }

        return count;
    }