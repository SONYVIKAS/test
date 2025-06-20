import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<Integer> storage;

    public MinHeap() {
        this.storage = new ArrayList<>();
    }

    public void swap(int a, int b) {
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

    public int get_parent(int child) {
        if (child % 2 == 0) {
            return (child - 2) / 2;
        } else {
            return (child - 1) / 2;
        }
    }

    public void bubbleUp(int child) {
        int parent = this.get_parent(child);

        while (child > 0 && parent >= 0 && this.storage.get(child) < this.storage.get(parent)) {
            this.swap(child, parent);
            child = parent;
            parent = this.get_parent(child);
        }
    }

    public int remove_peak() {
        this.swap(0, this.size() - 1);
        int min_elem = this.storage.remove(this.size() - 1);
        this.bubbleDown(0);

        return min_elem;
    }

    public int get_child(int parent) {
        int child1 = 2 * parent + 1;
        int child2 = 2 * parent + 2;

        if (child1 >= this.size()) {
            return -1;
        } else if (child2 >= this.size()) {
            return child1;
        } else if (this.storage.get(child1) < this.storage.get(child2)) {
            return child1;
        } else {
            return child2;
        }
    }

    public void bubbleDown(int parent) {
        int child = this.get_child(parent);

        while (child >= 0 && this.storage.get(parent) > this.storage.get(child)) {
            this.swap(child, parent);
            parent = child;
            child = this.get_child(parent);
        }
    }

    public int remove(int item) {
        int last_index = this.size() - 1;
        int swap_index = 0;

        for (int i = 0; i < this.storage.size(); i++) {
            if (item == this.storage.get(i)) {
                swap_index = i;
                this.storage.set(i, this.storage.get(last_index));
            }
        }

        this.bubbleUp(swap_index);
        this.bubbleDown(swap_index);

        int removed_item = this.storage.remove(last_index);

        return removed_item;
    }

    @Override
    public String toString() {
        return "<storage = " + this.storage + ">";
    }