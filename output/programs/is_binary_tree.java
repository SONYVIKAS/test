import org.junit.Assert;
import org.junit.Test;

// Define the Node class
class Node {
    int data;
    Node left, right;

    // Node constructor
    public Node(int item) {
        data = item;
        left = right = null;
    }
}

// Define the BST class
public class BinarySearchTree {

    Node root;

    // Function to check if a tree is a BST
    boolean isBST()  {
        return isBSTUtil(root, Integer.MIN_VALUE,
                               Integer.MAX_VALUE);
    }

    // Utility function to check if a tree is a BST
    boolean isBSTUtil(Node node, int min, int max) {
        // An empty tree is a BST
        if (node == null)
            return true;

        // False if this node violates the min/max constraints
        if (node.data < min || node.data > max)
            return false;

        // Check the subtrees under the constraints
        // Allow only distinct values
        return (isBSTUtil(node.left, min, node.data-1) &&
                isBSTUtil(node.right, node.data+1, max));
    }

    // Test cases
    @Test
    public void testIsBST() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(9);
        Assert.assertFalse(tree.isBST());

        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(7);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(4);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(8);
        Assert.assertTrue(tree.isBST());
    }