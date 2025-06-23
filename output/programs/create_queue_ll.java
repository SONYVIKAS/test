public class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

public class Queue {
    Node head;
    Node tail;

    public Queue() {
        this.head = null;
        this.tail = null;
    }

    public String toString() {
        if (this.length() == 0) {
            return "<Queue (empty)>";
        } else {
            return "<Queue " + this.head + ">";
        }
    }

    public int length() {
        Node curr = this.head;
        int length = 0;
        while (curr!= null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    public void enqueue(Object item) {
        Node new_node = new Node(item);
        if (this.head == null && this.tail == null) {
            this.head = new_node;
            this.tail = new_node;
        } else {
            this.tail.next = new_node;
            this.tail = new_node;
        }
    }

    public Object dequeue() {
        if (this.head == null) {
            return null;
        } else {
            Object dequeued = this.head.data;
            this.head = this.head.next;
            return dequeued;
        }
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public Object peek() {
        return this.head.data;
    }

    public void printQueue() {
        Node curr = this.head;
        if (curr == null) {
            System.out.println("[]");
            return;
        }
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