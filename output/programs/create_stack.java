import java.util.List;
import java.util.ArrayList;

public class Stack {
    private List<Object> items;
    private List<Object> minStack;

    public Stack() {
        this.items = new ArrayList<Object>();
        this.minStack = new ArrayList<Object>();
    }

    public String toString() {
        if (this.items.isEmpty()) {
            return "<Stack (empty)>";
        } else {
            return "<Stack tail=" + this.items.get(this.items.size() - 1) + " length=" + this.items.size() + ">";
        }
    }

    public void push(Object item) {
        this.items.add(item);

        if (this.minStack.isEmpty() || (Integer) this.minStack.get(this.minStack.size() - 1) > (Integer) item) {
            this.minStack.add(item);
        } else {
            this.minStack.add(this.minStack.get(this.minStack.size() - 1));
        }
    }

    public Object pop() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("pop from empty list");
        }

        this.minStack.remove(this.minStack.size() - 1);

        return this.items.remove(this.items.size() - 1);
    }

    public int length() {
        return this.items.size();
    }

    public void empty() {
        this.items = new ArrayList<Object>();
        this.minStack = new ArrayList<Object>();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public Object findMin() {
        if (!this.isEmpty()) {
            return this.minStack.get(this.minStack.size() - 1);
        }
    }