class Node {
    Object data;
    Node next;

    Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

class Queue {
    Node head;
    Node tail;

    Queue() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public String toString() {
        if (this.length() == 0) {
            return "<Queue (empty)>";
        } else {
            return "<Queue " + this.head + ">";
        }
    }

    int length() {
        Node curr = this.head;
        int length = 0;

        while (curr!= null) {
            length++;
            curr = curr.next;
        }

        return length;
    }

    void enqueue(Object item) {
        Node new_node = new Node(item);

        if (this.head == null && this.tail == null) {
            this.head = new_node;
            this.tail = new_node;
        } else {
            this.tail.next = new_node;
            this.tail = new_node;
        }
    }

    Object dequeue() {
        if (this.head == null) {
            return null;
        } else {
            Object dequeued = this.head.data;
            this.head = this.head.next;
            return dequeued;
        }
    }

    boolean isEmpty() {
        return this.head == null;
    }

    Object peek() {
        return this.head.data;
    }

    void printQueue() {
        Node curr = this.head;

        if (curr == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");

        while (curr!= null) {
            System.out.print(curr.data);

            if (curr.next!= null) {
                System.out.print(", ");
            }

            curr = curr.next;
        }

        System.out.println("]");
    }