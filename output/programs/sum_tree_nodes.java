public class Node {
    int data;
    List<Node> children;

    public Node(int data) {
        this.data = data;
        this.children = new ArrayList<Node>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}

public int numNodes(Node tree) {
    int nodes = 1;

    if (tree == null) {
        return 0;
    }

    for (Node child : tree.children) {
        nodes += numNodes(child);
    }

    return nodes;