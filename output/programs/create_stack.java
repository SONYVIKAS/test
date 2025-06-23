public class Stack {
    private List<Integer> items;
    private List<Integer> minStack;

    public Stack() {
        items = new ArrayList<Integer>();
        minStack = new ArrayList<Integer>();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "<Stack (empty)>";
        } else {
            return "<Stack tail=" + items.get(items.size() - 1) + ", length=" + items.size() + ">";
        }
    }

    public void push(int item) {
        items.add(item);

        if (minStack.isEmpty() || minStack.get(minStack.size() - 1) > item) {
            minStack.add(item);
        } else {
            minStack.add(minStack.get(minStack.size() - 1));
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("pop from empty list");
        }

        minStack.remove(minStack.size() - 1);

        return items.remove(items.size() - 1);
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
            return minStack.get(minStack.size() - 1);
        } else {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
    }