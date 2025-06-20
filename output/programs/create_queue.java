public class Queue {
    private List<Object> items;

    public Queue() {
        this.items = new ArrayList<>();
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
        if (this.items.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty.");
        } else {
            return this.items.remove(0);
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