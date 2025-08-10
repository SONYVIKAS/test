class Node {
    int data;
    Node next;

    // Node constructor
    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    // Node constructor for single node
    Node(int data) {
        this(data, null);
    }

    // Method to represent data for this node and its successors as a string
    String asString() {
        StringBuilder out = new StringBuilder();
        Node n = this;

        while (n != null) {
            out.append(n.data);
            n = n.next;
        }

        return out.toString();
    }
}

// Main class
public class Main {
    // Method to reverse a linked list
    static Node reverseLinkedList(Node head) {
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

    // Main method for testing
    public static void main(String[] args) {
        Node ll = new Node(1, new Node(2, new Node(3)));
        System.out.println(reverseLinkedList(ll).asString()); // Should print "321"

        ll = new Node(1, new Node(2, new Node(3)));
        Node newLl = reverseLinkedList(ll);
        System.out.println(newLl.asString()); // Should print "321"
    }