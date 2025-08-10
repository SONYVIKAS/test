
public class StackWithMin {
    // Java Stack class is used to create a stack
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    // Method to add an item to the stack
    public void push(int item) {
        stack.push(item);
        // If the minStack is empty or the last item in minStack is greater than the new item
        if (minStack.isEmpty() || minStack.peek() > item) {
            minStack.push(item);
        } else {
            minStack.push(minStack.peek());
        }
    }

    // Method to remove an item from the stack and return it
    public int pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        minStack.pop();
        return stack.pop();
    }

    // Method to get the length of the stack
    public int length() {
        return stack.size();
    }

    // Method to empty the stack
    public void empty() {
        stack.clear();
        minStack.clear();
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Method to find the minimum value in the stack
    public int findMin() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return minStack.peek();
    }

    // Method to print the stack
    @Override
    public String toString() {
        return "StackWithMin{" +
                "stack=" + stack +
                ", minStack=" + minStack +
                '}';
    }

    public static void main(String[] args) {
        StackWithMin stack = new StackWithMin();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.findMin());  // Output: 1
        stack.pop();
        System.out.println(stack.findMin());  // Output: 2
    }