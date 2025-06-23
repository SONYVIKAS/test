
  ```java
  import org.junit.Test;
  import static org.junit.Assert.assertEquals;

  public class MinHeap {

    private int[] storage;

    public MinHeap() {
      this.storage = new int[0];
    }

    private void swap(int a, int b) {
      int temp = this.storage[a];
      this.storage[a] = this.storage[b];
      this.storage[b] = temp;
    }

    public int size() {
      return this.storage.length;
    }

    public int peak() {
      return this.storage[0];
    }

    public void insert(int value) {
      int[] newStorage = new int[this.storage.length + 1];
      System.arraycopy(this.storage, 0, newStorage, 0, this.storage.length);
      newStorage[this.storage.length] = value;
      this.storage = newStorage;
      this.bubbleUp(this.size() - 1);
    }

    private int getParent(int child) {
      if (child % 2 == 0) {
        return (child - 2) / 2;
      } else {
        return (child - 1) / 2;
      }
    }

    private void bubbleUp(int child) {
      int parent = this.getParent(child);
      while (child > 0 && parent >= 0 && this.storage[child] < this.storage[parent]) {
        this.swap(child, parent);
        child = parent;
        parent = this.getParent(child);
      }
    }

    public int removePeak() {
      this.swap(0, this.size() - 1);
      int minElem = this.storage[this.size() - 1];
      this.storage = Arrays.copyOfRange(this.storage, 0, this.size() - 1);
      this.bubbleDown(0);
      return minElem;
    }

    private int getChild(int parent) {
      int child1 = 2 * parent + 1;
      int child2 = 2 * parent + 2;
      if (child1 >= this.size()) {
        return -1;
      } else if (child2 >= this.size()) {
        return child1;
      } else if (this.storage[child1] < this.storage[child2]) {
        return child1;
      } else {
        return child2;
      }
    }

    private void bubbleDown(int parent) {
      int child = this.getChild(parent);
      while (child >= 0 && this.storage[parent] > this.storage[child]) {
        this.swap(child, parent);
        parent = child;
        child = this.getChild(parent);
      }
    }

    public int remove(int item) {
      int lastIndex = this.size() - 1;
      int swapIndex = 0;
      for (int i = 0; i < this.size(); i++) {
        if (this.storage[i] == item) {
          swapIndex = i;
          this.storage[i] = this.storage[lastIndex];
        }
      }
      this.bubbleUp(swapIndex);
      this.bubbleDown(swapIndex);
      int removedItem = this.storage[lastIndex];
      this.storage = Arrays.copyOfRange(this.storage, 0, lastIndex);
      return removedItem;
    }

    @Override
    public String toString() {
      return "<storage = " + Arrays.toString(this.storage) + ">";
    }
  }

  public class Testing {
    @Test
    public void testSwap() {
      MinHeap test = new MinHeap();
      test.storage = new int[] {4, 5, 6, 7, 8};
      test.swap(0, 3);
      assertEquals("<storage = [7, 5, 6, 4, 8]>", test.toString(), "swap is incorrect");
    }

    @Test
    public void testSize() {
      MinHeap test = new MinHeap();
      test.storage = new int[] {4, 5, 6, 7, 8};
      assertEquals(5, test.size(), "size is incorrect");
    }

    @Test
    public void testPeak() {
      MinHeap test = new MinHeap();
      test.storage = new int[] {4, 5, 6, 7, 8};
      assertEquals(4, test.peak(), "peak is the wrong value");
    }

    @Test
    public void testInsert() {
      MinHeap test = new MinHeap();
      test.insert(7);