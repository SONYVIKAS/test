import java.util.List;

class MinHeap {

    private List<Integer> storage;

    public MinHeap() {
        this.storage = new ArrayList<>();
    }

    private void swap(int a, int b) {
        int temp = storage.get(a);
        storage.set(a, storage.get(b));
        storage.set(b, temp);
    }

    public int size() {
        return storage.size();
    }

    public int peak() {
        return storage.get(0);
    }

    public void insert(int value) {
        storage.add(value);
        int index = size() - 1;
        bubbleUp(index);
    }

    private int getParent(int child) {
        if (child % 2 == 0) {
            return (child - 2) / 2;
        } else {
            return (child - 1) / 2;
        }
    }

    private void bubbleUp(int child) {
        int parent = getParent(child);

        while (child > 0 && parent >= 0 && storage.get(child) < storage.get(parent)) {
            swap(child, parent);
            child = parent;
            parent = getParent(child);
        }
    }

    public int removePeak() {
        swap(0, size() - 1);
        int minElem = storage.remove(size() - 1);
        bubbleDown(0);
        return minElem;
    }

    private Integer getChild(int parent) {
        int child1 = 2 * parent + 1;
        int child2 = 2 * parent + 2;

        if (child1 >= size()) {
            return null;
        } else if (child2 >= size()) {
            return child1;
        } else if (storage.get(child1) < storage.get(child2)) {
            return child1;
        } else {
            return child2;
        }
    }

    private void bubbleDown(int parent) {
        Integer child = getChild(parent);

        while (child != null && storage.get(parent) > storage.get(child)) {
            swap(child, parent);
            parent = child;
            child = getChild(parent);
        }
    }

    public int remove(int item) {
        int lastIndex = size() - 1;
        int swapIndex = 0;

        for (int i = 0; i < storage.size(); i++) {
            if (item == storage.get(i)) {
                swapIndex = i;
                swap(i, lastIndex);
            }
        }

        bubbleUp(swapIndex);
        bubbleDown(swapIndex);

        return storage.remove(size() - 1);
    }

    @Override
    public String toString() {
        return "<storage = " + storage + ">";
    }
}

// Test class using JUnit
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinHeapTest {

    private MinHeap test;

    @BeforeEach
    void setUp() {
        test = new MinHeap();
        test.insert(4);
        test.insert(5);
        test.insert(6);
        test.insert(7);
        test.insert(8);
    }

    @Test
    void testSwap() {
        test.swap(0, 3);
        assertEquals("<storage = [7, 5, 6, 4, 8]>", test.toString(), "swap is incorrect");
    }

    @Test
    void testSize() {
        assertEquals(5, test.size(), "size is incorrect");
    }

    @Test
    void testPeak() {
        assertEquals(4, test.peak(), "peak is the wrong value");
    }

    @Test
    void testInsert() {
        test = new MinHeap();
        test.insert(7);
        test.insert(4);
        test.insert(10);
        test.insert(1);
        test.insert(8);
        test.insert(11);
        test.insert(9);
        test.insert(2);
        assertEquals("<storage = [1, 2, 9, 4, 8, 11, 10, 7]>", test.toString(), "did not insert new value properly");
    }

    @Test
    void testGetParent() {
        assertEquals(3, test.getParent(7), "getting child's parent index is incorrect");
    }

    @Test
    void testBubbleUp() {
        test.insert(1);
        assertEquals("<storage = [1, 5, 4, 7, 8, 6]>", test.toString(), "did not bubble up correctly");
        test.insert(10);
        assertEquals("<storage = [1, 5, 4, 7, 8, 6, 10]>", test.toString(), "did not bubble up correctly");
        test.insert(2);
        assertEquals("<storage = [1, 2, 4, 5, 8, 6, 10, 7]>", test.toString(), "did not bubble up correctly");
        test.insert(9);
        assertEquals("<storage = [1, 2, 4, 5, 8, 6, 10, 7, 9]>", test.toString(), "did not bubble up correctly");
    }

    @Test
    void testRemovePeak() {
        test = new MinHeap();
        test.insert(1);
        test.insert(2);
        test.insert(4);
        test.insert(11);
        assertEquals(1, test.removePeak(), "did not remove correct value");
        assertEquals(2, test.removePeak(), "did not remove correct value");
        assertEquals(4, test.removePeak(), "did not remove correct value");
        assertEquals(11, test.removePeak(), "did not remove correct value");
    }

    @Test
    void testGetChild() {
        assertEquals(1, test.getChild(0), "did not select the right child index");
    }

    @Test
    void testBubbleDown() {
        test = new MinHeap();
        test.insert(1);
        test.insert(2);
        test.insert(4);
        test.insert(11);
        test.removePeak();
        assertEquals("<storage = [2, 11, 4]>", test.toString(), "did not bubble down correctly");
        test.removePeak();
        assertEquals("<storage = [4, 11]>", test.toString(), "did not bubble down correctly");
        test.removePeak();
        assertEquals("<storage = [11]>", test.toString(), "did not bubble down correctly");
        test.removePeak();
        assertEquals("<storage = []>", test.toString(), "did not bubble down correctly");
    }

    @Test
    void testRemove() {
        assertEquals(8, test.remove(8), "did not remove correct value");
        assertEquals("<storage = [4, 5, 6, 7]>", test.toString(), "did not bubble down correctly");
    }