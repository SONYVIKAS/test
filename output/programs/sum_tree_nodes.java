public class Node {
    private int data;
    private List<Node> children;

    public Node(int data) {
        this.data = data;
        this.children = new ArrayList<Node>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}

public class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public int numNodes() {
        return numNodes(this.root);
    }

    private int numNodes(Node node) {
        int nodes = 1;

        for (Node child : node.children) {
            nodes += numNodes(child);
        }

        return nodes;
    }