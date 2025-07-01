import java.util.EmptyStackException;
import java.util.Stack;

public class StackImplementation {
    private Stack<Integer> items;
    private Stack<Integer> minStack;

    public StackImplementation() {
        this.items = new Stack<>();
        this.minStack = new Stack<>();
    }

    public String toString() {
        if (this.items.isEmpty()) {
            return "<Stack (empty)>";
        } else {
            return "<Stack tail=" + this.items.peek() + ", length=" + this.items.size() + ">";
        }
    }

    public void push(int item) {
        this.items.push(item);

        if (this.minStack.isEmpty() || this.minStack.peek() > item) {
            this.minStack.push(item);
        } else {
            this.minStack.push(this.minStack.peek());
        }
    }

    public int pop() {
        if (this.items.isEmpty()) {
            throw new EmptyStackException();
        }

        this.minStack.pop();

        return this.items.pop();
    }

    public int length() {
        return this.items.size();
    }

    public void empty() {
        this.items.clear();
        this.minStack.clear();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public int findMin() {
        if (!this.items.isEmpty()) {
            return this.minStack.peek();
        } else {
            throw new EmptyStackException();
        }
    }