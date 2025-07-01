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

    boolean is_empty() {
        return this.head == null;
    }

    Object peek() {
        return this.head.data;
    }

    void print_queue() {
        Node curr = this.head;
        if (curr == null) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            while (curr!= null) {
                System.out.print(curr.data);
                curr = curr.next;
                if (curr!= null) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }