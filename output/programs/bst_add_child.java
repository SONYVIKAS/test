class Node {

    int data;
    Node left;
    Node right;

    // Constructor to create a node with data and optional left/right nodes
    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // Overloaded constructor to create a node with only data
    public Node(int data) {
        this(data, null, null);
    }

    // Method to insert a new node with 'newData' into the BST
    public void insert(int newData) {
        // If the new data is less than the current node's data, go left
        if (newData < this.data) {
            if (this.left == null) {
                this.left = new Node(newData); // Insert new node if left is null
            } else {
                this.left.insert(newData); // Recur to insert in the left subtree
            }
        } else { // If the new data is greater or equal, go right
            if (this.right == null) {
                this.right = new Node(newData); // Insert new node if right is null
            } else {
                this.right.insert(newData); // Recur to insert in the right subtree
            }
        }
    }

    // Main method to test the Node class
    public static void main(String[] args) {
        // Create a sample BST
        Node t = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(7, new Node(5), new Node(8)));

        // Insert new nodes and test the structure
        t.insert(0);
        assert t.left.left.left.data == 0;
        assert t.left.left.right == null;

        t.insert(9);
        assert t.right.right.right.data == 9;
        assert t.right.right.left == null;

        t.insert(6);
        assert t.right.left.right.data == 6;
        assert t.right.left.left == null;

        System.out.println("ALL TESTS PASSED!");
    }