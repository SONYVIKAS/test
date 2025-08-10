import java.util.EmptyStackException;

public class Stack {
    private ArrayList<Integer> items;
    private ArrayList<Integer> minStack;

    public Stack() {
        this.items = new ArrayList<>();
        this.minStack = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.items.isEmpty()) {
            return "<Stack (empty)>";
        } else {
            return "<Stack tail=" + this.items.get(this.items.size() - 1) + " length=" + this.items.size() + ">";
        }
    }

    public void push(int item) {
        // Add item to end of stack.
        this.items.add(item);

        if (this.minStack.isEmpty() || this.minStack.get(this.minStack.size() - 1) > item) {
            this.minStack.add(item);
        } else {
            this.minStack.add(this.minStack.get(this.minStack.size() - 1));
        }
    }

    public int pop() {
        // Remove item from end of stack and return it.
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        this.minStack.remove(this.minStack.size() - 1);

        return this.items.remove(this.items.size() - 1);
    }

    public int length() {
        // Return length of stack.
        return this.items.size();
    }

    public void empty() {
        // Empty stack.
        this.items.clear();
        this.minStack.clear();
    }

    public boolean isEmpty() {
        // Check if stack is empty.
        return this.items.isEmpty();
    }

    public int findMin() {
        // Returns the minimum value of a numerical stack.
        if (!this.isEmpty()) {
            return this.minStack.get(this.minStack.size() - 1);
        } else {
            throw new EmptyStackException();
        }
    }