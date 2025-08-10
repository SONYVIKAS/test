    // Class representing a node in a linked list
    int data;
    Node next;

    // Constructor for Node
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    // Method to represent data for this node and its successors as a string
    public String asString() {
        StringBuilder out = new StringBuilder();
        Node n = this;

        while (n != null) {
            out.append(n.data);
            n = n.next;
        }

        return out.toString();
    }
}

public class ReverseLinkedList {

    // Method to reverse a singly linked list
    public static Node reverseLinkedList(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    // Main method to run tests
    public static void main(String[] args) {
        // Test case 1
        Node ll = new Node(1, new Node(2, new Node(3, null)));
        assert reverseLinkedList(ll).asString().equals("321");

        // Test case 2
        ll = new Node(1, new Node(2, new Node(3, null)));
        Node newLl = reverseLinkedList(ll);
        assert newLl.asString().equals("321");

        System.out.println("ALL TESTS PASSED!");
    }