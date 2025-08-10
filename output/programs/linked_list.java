
// Node class to create a node
class Node {
    Object data;
    Node next;

    // Node constructor
    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }
}

// LinkedList class to create a linked list
class LinkedList {
    Node head;
    Node tail;

    // LinkedList constructor
    public LinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    // Method to insert a node at a specific position
    public void insert(Object data, int position) {
        Node newNode = new Node(data, null);

        if (position == 0) {
            newNode.next = this.head;
            this.head = newNode;
        }

        if (position == this.size()) {
            this.tail.next = newNode;
            this.tail = newNode;
        }

        Node prev = null;
        Node curr = this.head;
        int index = 0;

        while (curr.next != null) {
            if (position == index) {
                prev.next = newNode;
                newNode.next = curr;
                return;
            }
            index++;
            prev = prev.next;
            curr = curr.next;
        }
    }

    // Method to get the size of the linked list
    public int size() {
        int size = 0;

        if (head == null && tail == null) {
            return size;
        }

        Node curr = this.head;

        while (curr != null) {
            size++;
            curr = curr.next;
        }

        return size;
    }

    // Method to search a node with specific data
    public Node search(Object data) {
        Node curr = this.head;

        while (curr != null) {
            if (curr.data.equals(data)) {
                return curr;
            }
            curr = curr.next;
        }

        throw new IllegalArgumentException("Data not in linked list.");
    }

    // Method to delete a node with specific data
    public void delete(Object data) {
        Node prev = null;
        Node curr = this.head;

        while (curr != null) {
            if (curr.data.equals(data)) {
                if (curr == this.head) {
                    this.head = curr.next;
                } else {
                    prev.next = curr.next;
                }
            }
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {
            throw new IllegalArgumentException("Data not in linked list.");
        }
    }