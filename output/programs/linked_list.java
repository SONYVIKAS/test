
// Node class to create a node
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// LinkedList class to create a linked list
class LinkedList {
    private Node head;
    private Node tail;

    LinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Method to insert a node at a given position
    public void insert(int data, int position) {
        Node newNode = new Node(data);

        if (position == 0) {
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
            return;
        }

        if (position == size()) {
            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
            if (head == null) {
                head = newNode;
            }
            return;
        }

        Node prev = null;
        Node curr = head;
        int index = 0;

        while (curr != null) {
            if (position == index) {
                prev.next = newNode;
                newNode.next = curr;
                return;
            }
            index++;
            prev = curr;
            curr = curr.next;
        }
    }

    // Method to return the size of the linked list
    public int size() {
        int size = 0;
        Node curr = head;

        while (curr != null) {
            size++;
            curr = curr.next;
        }

        return size;
    }

    // Method to search for a node with specific data
    public Node search(int data) {
        Node curr = head;

        while (curr != null) {
            if (curr.data == data) {
                return curr;
            }
            curr = curr.next;
        }

        throw new IllegalArgumentException("Data not in linked list.");
    }

    // Method to delete a node with specific data
    public void delete(int data) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            if (curr.data == data) {
                if (curr == head) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                if (curr == tail) {
                    tail = prev;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        throw new IllegalArgumentException("Data not in linked list.");
    }