 2. We can use the `import` statement in Java to import the `junit` library, which is commonly used for unit testing in Java.
 3. We can use the `@Test` annotation to mark methods as test cases, and the `assert` statement to verify the expected results.
 4. We can use the `assertEquals` method from the `junit` library to compare the actual and expected results.
 5. We can use the `setup` method to initialize the test data and the `teardown` method to clean up after each test case.

Here is the Java code that implements the above steps:

```java
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MinHeapTest {
    @Test
    public void testSwap() {
        MinHeap heap = new MinHeap();
        heap.storage = new int[] {4, 5, 6, 7, 8};
        heap.swap(0, 3);
        assertEquals("[7, 5, 6, 4, 8]", heap.storage.toString());
    }

    @Test
    public void testSize() {
        MinHeap heap = new MinHeap();
        heap.storage = new int[] {4, 5, 6, 7, 8};
        assertEquals(5, heap.size());
    }

    @Test
    public void testPeak() {
        MinHeap heap = new MinHeap();
        heap.storage = new int[] {4, 5, 6, 7, 8};
        assertEquals(4, heap.peak());
    }

    @Test
    public void testInsert() {
        MinHeap heap = new MinHeap();
        heap.insert(7);
        heap.insert(4);
        heap.insert(10);
        heap.insert(1);
        heap.insert(8);
        heap.insert(11);
        heap.insert(9);
        heap.insert(2);
        assertEquals("[1, 2, 9, 4, 8, 11, 10, 7]", heap.storage.toString());
    }

    @Test
    public void testGetParent() {
        MinHeap heap = new MinHeap();
        heap.storage = new int[] {4, 5, 6, 7, 8};
        assertEquals(3, heap.getParent(7));
    }

    @Test
    public void testBubbleUp() {
        MinHeap heap = new MinHeap();
        heap.storage = new int[] {};
        heap.bubbleUp(0);
        assertEquals("[7]", heap.storage.toString());
        heap.bubbleUp(1);
        assertEquals("[7, 5]", heap.storage.toString());
        heap.bubbleUp(2);
        assertEquals("[4, 5, 7]", heap.storage.toString());
        heap.bubbleUp(3);
        assertEquals("[4, 5, 7, 10]", heap.storage.toString());
        heap.bubbleUp(4);
        assertEquals("[1, 5, 7, 10, 8]", heap.storage.toString());
        heap.bubbleUp(5);
        assertEquals("[1, 5, 7, 10, 8, 11]", heap.storage.toString());
        heap.bubbleUp(6);
        assertEquals("[1, 2, 7, 10, 8, 11, 9]", heap.storage.toString());
        heap.bubbleUp(7);
        assertEquals("[1, 2, 4, 10, 8, 11, 9, 7]", heap.storage.toString());
    }

    @Test
    public void testRemovePeak() {
        MinHeap heap = new MinHeap();
        heap.storage = new int[] {1, 2, 4, 11};
        assertEquals(1, heap.removePeak());
        assertEquals("[2, 11, 4]", heap.storage.toString());
        assertEquals(2, heap.removePeak());
        assertEquals("[4, 11]", heap.storage.toString());
        assertEquals(4, heap.removePeak());
        assertEquals("[11]", heap.storage.toString());
        assertEquals(11, heap.removePeak());
        assertEquals("[]", heap.storage.toString());
    }

    @Test
    public void testGetChild() {
        MinHeap heap = new MinHeap();
        heap.storage = new int[] {4, 5, 6, 7, 8};
        assertEquals(1, heap.getChild(0));
    }

    @Test
    public void testBubbleDown() {