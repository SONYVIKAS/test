import java.util.List;
import java.util.NoSuchElementException;

public class Stack {
    // List to store stack items
    private List<Integer> items;
    // List to keep track of minimum values
    private List<Integer> minStack;

    // Constructor to initialize the stack
    public Stack() {
        this.items = new ArrayList<>();
        this.minStack = new ArrayList<>();
    }

    // Method to represent the stack as a string
    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "<Stack (empty)>";
        } else {
            return "<Stack tail=" + items.get(items.size() - 1) + " length=" + items.size() + ">";
        }
    }

    // Method to push an item onto the stack
    public void push(int item) {
        items.add(item);

        if (minStack.isEmpty() || minStack.get(minStack.size() - 1) > item) {
            minStack.add(item);
        } else {
            minStack.add(minStack.get(minStack.size() - 1));
        }
    }

    // Method to pop an item from the stack
    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("pop from empty list");
        }

        minStack.remove(minStack.size() - 1);
        return items.remove(items.size() - 1);
    }

    // Method to get the length of the stack
    public int length() {
        return items.size();
    }

    // Method to empty the stack
    public void empty() {
        items.clear();
        minStack.clear();
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Method to find the minimum value in the stack
    public int findMin() {
        if (!isEmpty()) {
            return minStack.get(minStack.size() - 1);
        }
        throw new NoSuchElementException("Stack is empty");
    }

    // Main method to run tests
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(2);
        s.push(1);
        s.push(3);
        s.push(-1);
        System.out.println(s.findMin()); // -1

        Stack s2 = new Stack();
        s2.push(2);
        s2.push(1);
        s2.push(3);
        System.out.println(s2.findMin()); // 1

        Stack s3 = new Stack();
        s3.push(2);
        s3.push(1);
        s3.push(3);
        s3.push(3);
        s3.push(1);
        System.out.println(s3.findMin()); // 1

        s3.pop();
        System.out.println(s3.findMin()); // 1

        s3.pop();
        s3.pop();
        s3.pop();
        System.out.println(s3.findMin()); // 2

        System.out.println("ALL TESTS PASSED. GOOD WORK!");
    }