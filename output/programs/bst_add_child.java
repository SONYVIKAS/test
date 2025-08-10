class Node {
    int data;
    Node left, right;

    // Constructor to create a new node with data and optional left/right nodes
    Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    Node(int data) {
        this(data, null, null);
    }

    // Method to insert a new node with 'newData' to BST tree rooted here
    void insert(int newData) {
        // If the new data is less than the current node's data
        if (newData < this.data) {
            // If the left child is null, create a new node and assign it to the left child
            if (this.left == null) {
                this.left = new Node(newData);
            } 
            // If the left child is not null, recursively call the insert method on the left child
            else {
                this.left.insert(newData);
            }
        } 
        // If the new data is greater than or equal to the current node's data
        else {
            // If the right child is null, create a new node and assign it to the right child
            if (this.right == null) {
                this.right = new Node(newData);
            } 
            // If the right child is not null, recursively call the insert method on the right child
            else {
                this.right.insert(newData);
            }
        }
    }
}

// Main class to test the Node class and its methods
public class Main {
    public static void main(String[] args) {
        // Create a new BST
        Node t = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(7, new Node(5), new Node(8)));

        // Insert new nodes and print the BST to check if the nodes are inserted correctly
        t.insert(0);
        t.insert(9);
        t.insert(6);
        // Print the BST or use a method to traverse and print the BST to check the result
    }