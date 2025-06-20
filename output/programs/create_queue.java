public class Queue {
    private List<Object> items;

    public Queue() {
        this.items = new ArrayList<Object>();
    }

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
            throw new IndexOutOfBoundsException("queue is empty.");
        }
    }

    public void enqueue(Object item) {
        this.items.add(item);
    }

    public boolean is_empty() {
        return this.items.isEmpty();
    }

    public Object peek() {
        return this.items.get(0);
    }