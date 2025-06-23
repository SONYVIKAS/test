import org.junit.Test;
import static org.junit.Assert.*;

public class BinarySearchTree {

    public static boolean isBST(Node root) {
        return isBSTRecursive(root, null, null);
    }

    private static boolean isBSTRecursive(Node root, Integer smallest, Integer largest) {
        if (root == null) {
            return true;
        }
        if (largest!= null && root.data > largest) {
            return false;
        }
        if (smallest!= null && root.data < smallest) {
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

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void testIsBST() {
        Node root1 = new Node(5, new Node(3, new Node(1, null, null), new Node(9, null, null)), new Node(6, null, null));
        Node root2 = new Node(5, new Node(3, new Node(1, null, null), new Node(4, null, null)), new Node(7, new Node(6, null, null), new Node(8, null, null)));

        assertFalse(isBST(root1));
        assertTrue(isBST(root2));
    }