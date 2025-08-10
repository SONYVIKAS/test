import java.util.List;

class Node {
    int data;
    List<Node> children;

    // Constructor to initialize the node with data and an empty list of children
    public Node(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    // Method to add a child node
    public void addChild(Node child) {
        this.children.add(child);
    }
}

public class SumTreeNodes {

    // Method to count the number of nodes in a tree
    public static int numNodes(Node tree) {
        if (tree == null) {
            return 0; // Return 0 if the tree is null
        }

        int nodes = 1; // Start with the current node

        // Recursively count the nodes in each child
        for (Node child : tree.children) {
            nodes += numNodes(child);
        }

        return nodes; // Return the total number of nodes
    }

    public static void main(String[] args) {
        // Create nodes
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);

        // Build the tree structure
        one.addChild(two);
        one.addChild(three);
        two.addChild(four);
        two.addChild(five);
        three.addChild(six);

        // Test the numNodes function
        assert numNodes(one) == 6 : "Test failed";

        System.out.println("ALL TESTS PASSED");
    }