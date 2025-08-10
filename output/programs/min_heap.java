import java.util.List;

public class MinHeap {
    private List<Integer> storage;

    public MinHeap() {
        this.storage = new ArrayList<>();
    }

    private void swap(int a, int b) {
        int temp = this.storage.get(a);
        this.storage.set(a, this.storage.get(b));
        this.storage.set(b, temp);
    }

    public int size() {
        return this.storage.size();
    }

    public int peak() {
        return this.storage.get(0);
    }

    public void insert(int value) {
        this.storage.add(value);
        int index = this.size() - 1;
        this.bubbleUp(index);
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

        while (child > 0 && parent >= 0 && (this.storage.get(child) < this.storage.get(parent))) {
            this.swap(child, parent);
            child = parent;
            parent = this.getParent(child);
        }
    }

    public int removePeak() {
        this.swap(0, this.size() - 1);
        int minElem = this.storage.remove(this.size() - 1);
        this.bubbleDown(0);

        return minElem;
    }

    private Integer getChild(int parent) {
        int child1 = 2 * parent + 1;
        int child2 = 2 * parent + 2;

        if (child1 >= this.size()) {
            return null;
        } else if (child2 >= this.size()) {
            return child1;
        } else if (this.storage.get(child1) < this.storage.get(child2)) {
            return child1;
        } else {
            return child2;
        }
    }

    private void bubbleDown(int parent) {
        Integer child = this.getChild(parent);

        while (child != null && this.storage.get(parent) > this.storage.get(child)) {
            this.swap(child, parent);
            parent = child;
            child = this.getChild(parent);
        }
    }

    public int remove(int item) {
        int lastIndex = this.size() - 1;
        int swapIndex = 0;

        for (int i = 0; i < this.storage.size(); i++) {
            if (item == this.storage.get(i)) {
                swapIndex = i;
                int temp = this.storage.get(i);
                this.storage.set(i, this.storage.get(lastIndex));
                this.storage.set(lastIndex, temp);
            }
        }

        this.bubbleUp(swapIndex);
        this.bubbleDown(swapIndex);

        int removedItem = this.storage.remove(this.size() - 1);

        return removedItem;
    }

    @Override
    public String toString() {
        return "<storage = " + this.storage + ">";
    }