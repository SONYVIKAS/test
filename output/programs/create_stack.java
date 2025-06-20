import java.util.Stack;

public class Stack {
    private Stack<Integer> items;
    private Stack<Integer> minStack;

    public Stack() {
        this.items = new Stack<>();
        this.minStack = new Stack<>();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "<Stack (empty)>";
        } else {
            return "<Stack tail=" + items.peek() + ", length=" + items.size() + ">";
        }
    }

    public void push(int item) {
        items.push(item);

        if (minStack.isEmpty() || minStack.peek() > item) {
            minStack.push(item);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("pop from empty list");
        }

        minStack.pop();

        return items.pop();
    }

    public int length() {
        return items.size();
    }

    public void empty() {
        items.clear();
        minStack.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int findMin() {
        if (!isEmpty()) {
            return minStack.peek();
        }
    }