public class Queue {
    private List<String> items;

    public Queue() {
        this.items = new ArrayList<String>();
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

    public String dequeue() {
        if (this.items.isEmpty()) {
            throw new IndexOutOfBoundsException("queue is empty.");
        } else {
            return this.items.remove(0);
        }
    }

    public void enqueue(String item) {
        this.items.add(item);
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public String peek() {
        return this.items.get(0);
    }