import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinarySearchTree {
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

    @Test
    public void testIsBST() {
        Node bst1 = new Node(5, new Node(3, new Node(1), new Node(9)), new Node(6));
        Node bst2 = new Node(5, new Node(3, new Node(1), new Node(4)), new Node(7, new Node(6), new Node(8)));

        assertFalse(isBST(bst1));
        assertTrue(isBST(bst2));
    }