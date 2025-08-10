import org.junit.Test;
import static org.junit.Assert.*;

// Node class representing each node in the binary tree
class Node {
    int data;
    Node left, right;

    // Constructor for Node
    Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

// Main class containing the method to check if a tree is a BST
public class BinaryTree {

    // Method to check if a binary tree is a binary search tree
    public static boolean isBST(Node root) {
        return isBSTRecursive(root, null, null);
    }

    // Recursive helper method to validate the BST property
    private static boolean isBSTRecursive(Node root, Integer smallest, Integer largest) {
        if (root == null) {
            return true;
        }

        if (largest != null && root.data > largest) {
            return false;
        }

        if (smallest != null && root.data < smallest) {
            return false;
        }

        if (!isBSTRecursive(root.left, smallest, root.data)) {
            return false;
        }

        if (!isBSTRecursive(root.right, root.data, largest)) {
            return false;
        }

        return true;
    }

    // Unit test class
    public static class Tests {

        // Helper method to create a binary tree that is not a BST
        private Node createBST1() {
            return new Node(5, new Node(3, new Node(1), new Node(9)), new Node(6));
        }

        // Helper method to create a binary tree that is a BST
        private Node createBST2() {
            return new Node(5, new Node(3, new Node(1), new Node(4)), new Node(7, new Node(6), new Node(8)));
        }

        // Test method to check the isBST function
        @Test
        public void testIsBST() {
            assertFalse(BinaryTree.isBST(createBST1()));
            assertTrue(BinaryTree.isBST(createBST2()));
        }
    }