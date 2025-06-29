class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    LinkedList() {
        this.head = null;
    }

    void insert(int data, int position) {
        Node new_node = new Node(data);

        if (position == 0) {
            new_node.next = head;
            head = new_node;
            return;
        }

        Node prev = null;
        Node curr = head;
        int index = 0;

        while (curr!= null) {
            if (position == index) {
                prev.next = new_node;
                new_node.next = curr;
                return;
            }
            index++;
            prev = curr;
            curr = curr.next;
        }
    }

    int size() {
        int size = 0;
        Node curr = head;

        while (curr!= null) {
            size++;
            curr = curr.next;
        }

        return size;
    }

    Node search(int data) {
        Node curr = head;

        while (curr!= null) {
            if (curr.data == data) {
                return curr;
            }
            curr = curr.next;
        }

        throw new IllegalArgumentException("Data not in linked list.");
    }

    void delete(int data) {
        Node prev = null;
        Node curr = head;

        while (curr!= null) {
            if (curr.data == data) {
                if (curr == head) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        throw new IllegalArgumentException("Data not in linked list.");
    }