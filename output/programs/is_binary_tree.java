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
          return isBSTRecursive(root, null, null);
      }

      private boolean isBSTRecursive(Node root, Integer smallest, Integer largest) {
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
  }

  public class Tests {
      Node createBST1() {
          return new Node(5, new Node(3, new Node(1), new Node(9)), new Node(6));
      }

      Node createBST2() {
          return new Node(5, new Node(3, new Node(1), new Node(4)), new Node(7, new Node(6), new Node(8)));
      }

      @Test
      public void testIsBST() {
          BinarySearchTree bst = new BinarySearchTree(createBST1());
          assertFalse(bst.isBST());
          bst = new BinarySearchTree(createBST2());
          assertTrue(bst.isBST());
      }
  }