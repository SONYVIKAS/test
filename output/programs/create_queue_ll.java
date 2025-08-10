    // Creates a node class
    Object data;
    Node next;

    Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

class Queue {
    // Creates a queue using a linked list
    private Node head;
    private Node tail;

    Queue() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public String toString() {
        if (this.length() == 0) {
            return "<Queue (empty)>";
        } else {
            return "<Queue " + this.head.data + ">";
        }
    }

    public int length() {
        // Gets length of queue
        Node curr = this.head;
        int length = 0;

        while (curr != null) {
            length++;
            curr = curr.next;
        }

        return length;
    }

    public void enqueue(Object item) {
        // Add item to end of queue
        Node newNode = new Node(item);

        if (this.head == null && this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }

    public Object dequeue() {
        // Remove item from front of queue
        if (this.head == null) {
            return null;
        } else {
            Object dequeued = this.head.data;
            this.head = this.head.next;
            return dequeued;
        }
    }

    public boolean isEmpty() {
        // True/false if queue is empty
        return this.head == null;
    }

    public Object peek() {
        // Return but don't remove the first item in the queue
        return this.head.data;
    }

    public Object[] printQueue() {
        // Prints items in queue in a list
        Node curr = this.head;
        if (curr == null) {
            return new Object[0];
        }

        java.util.List<Object> queue = new java.util.ArrayList<>();

        while (curr != null) {
            queue.add(curr.data);
            curr = curr.next;
        }

        return queue.toArray();
    }

    public static void main(String[] args) {
        // Test the Queue implementation
        Queue q = new Queue();
        q.enqueue("buy flight");
        q.enqueue("pack");
        q.enqueue("enjoy vacation");

        System.out.println(java.util.Arrays.toString(q.printQueue())); // ['buy flight', 'pack', 'enjoy vacation']
        System.out.println(q.length()); // 3
        System.out.println(q.dequeue()); // 'buy flight'
        System.out.println(java.util.Arrays.toString(q.printQueue())); // ['pack', 'enjoy vacation']
        System.out.println(q.isEmpty()); // false
        System.out.println(q.peek()); // 'pack'
    }