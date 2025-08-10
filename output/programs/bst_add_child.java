class Node {
    int data;
    Node left, right;

    // Node constructor
    public Node(int item) {
        data = item;
        left = right = null;
    }

    // Function to insert a new node in the BST
    void insert(int new_data) {
        // If the tree is empty, assign a new node address to the root
        if (new_data < this.data) {
            if (this.left == null) {
                this.left = new Node(new_data);
            } else {
                this.left.insert(new_data);
            }
        } else {
            if (this.right == null) {
                this.right = new Node(new_data);
            } else {
                this.right.insert(new_data);
            }
        }
    }
}

// Driver class
public class Main {
    public static void main(String[] args) {
        // Create a new BST
        Node t = new Node(4);
        t.left = new Node(2);
        t.right = new Node(7);
        t.left.left = new Node(1);
        t.left.right = new Node(3);
        t.right.left = new Node(5);
        t.right.right = new Node(8);

        // Insert new nodes
        t.insert(0);
        t.insert(9);
        t.insert(6);

        // Print the BST
        printInorder(t);
    }

    // Function to do inorder traversal of BST
    static void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }