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
        Node curr = head;
        int length = 0;
        while (curr!= null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    void enqueue(Object item) {
        Node new_node = new Node(item);
        if (head == null && tail == null) {
            head = new_node;
            tail = new_node;
        } else {
            tail.next = new_node;
            tail = new_node;
        }
    }

    Object dequeue() {
        if (head == null) {
            return null;
        } else {
            Object dequeued = head.data;
            head = head.next;
            return dequeued;
        }
    }

    boolean isEmpty() {
        return head == null;
    }

    Object peek() {
        return head.data;
    }

    void printQueue() {
        Node curr = head;
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