import java.util.NoSuchElementException;

// Class to create a queue
public class Queue {
    private LinkedList<String> items;

    // Constructor
    public Queue() {
        this.items = new LinkedList<String>();
    }

    // Method to get the string representation of the queue
    @Override
    public String toString() {
        if (this.items.isEmpty()) {
            return "<Queue (empty)>";
        } else {
            return "<Queue " + this.items.toString() + ">";
        }
    }

    // Method to get the length of the queue
    public int length() {
        return this.items.size();
    }

    // Method to remove item from the front of the queue
    public String dequeue() {
        if (!this.items.isEmpty()) {
            return this.items.removeFirst();
        } else {
            throw new NoSuchElementException("Queue is empty.");
        }
    }

    // Method to add item to the end of the queue
    public void enqueue(String item) {
        this.items.add(item);
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    // Method to return but not remove the first item in the queue
    public String peek() {
        if (!this.items.isEmpty()) {
            return this.items.getFirst();
        } else {
            throw new NoSuchElementException("Queue is empty.");
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue("buy flight");
        q.enqueue("pack");
        q.enqueue("enjoy vacation");

        System.out.println(q); // Output: <Queue [buy flight, pack, enjoy vacation]>
        System.out.println(q.length()); // Output: 3
        System.out.println(q.peek()); // Output: buy flight
        System.out.println(q); // Output: <Queue [buy flight, pack, enjoy vacation]>
    }