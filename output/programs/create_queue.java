import java.util.ArrayList;
import java.util.List;

public class Queue {
    private List<Object> items;

    public Queue() {
        this.items = new ArrayList<Object>();
    }

    @Override
    public String toString() {
        if (this.items.isEmpty()) {
            return "<Queue (empty)>";
        } else {
            return "<Queue " + this.items + ">";
        }
    }

    public int length() {
        return this.items.size();
    }

    public Object dequeue() {
        if (this.items.size() > 0) {
            return this.items.remove(0);
        } else {
            throw new IndexOutOfBoundsException("Queue is empty.");
        }
    }

    public void enqueue(Object item) {
        this.items.add(item);
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public Object peek() {
        return this.items.get(0);
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue("buy flight");
        q.enqueue("pack");
        q.enqueue("enjoy vacation");

        System.out.println(q);
        System.out.println("Length: " + q.length());

        System.out.println("Dequeue: " + q.dequeue());
        System.out.println(q);
        System.out.println("Peek: " + q.peek());
    }