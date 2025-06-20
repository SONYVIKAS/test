  import org.junit.Test;
  import static org.junit.Assert.assertFalse;
  import static org.junit.Assert.assertTrue;

  class Node {
      int data;
      Node left;
      Node right;
      Node(int data, Node left, Node right) {
          this.data = data;
          this.left = left;
          this.right = right;
      }
  }

  class BinarySearchTree {
      Node root;
      BinarySearchTree(Node root) {
          this.root = root;
      }
      boolean isBST() {
          return isBST(root, null, null);
      }
      private boolean isBST(Node root, Integer min, Integer max) {
          if (root == null) {
              return true;
          }
          if ((min!= null && root.data <= min) || (max!= null && root.data > max)) {
              return false;
          }
          return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
      }
  }

  public class Main {
      @Test
      public void testIsBST() {
          Node root1 = new Node(5, new Node(3, new Node(1), new Node(9)), new Node(6));
          BinarySearchTree tree1 = new BinarySearchTree(root1);
          assertFalse(tree1.isBST());
          Node root2 = new Node(5, new Node(3, new Node(1), new Node(4)), new Node(7, new Node(6), new Node(8)));
          BinarySearchTree tree2 = new BinarySearchTree(root2);
          assertTrue(tree2.isBST());
      }
  }