
// Create a hash map.
public class HashMap {

    // In a 64-bit system, creates 64 lists in a list
    private List<List<Pair<String, Integer>>> hash;

    public HashMap() {
        // Creates hash list of lists.
        this.hash = new ArrayList<>(Collections.nCopies(64, new ArrayList<>()));
    }

    // Hashes a key and returns the hashed index.
    private int hashing(String key) {
        int value = 0;

        // Gets the total ASCII value of the key
        for (char c : key.toCharArray()) {
            value += (int) c;
        }

        // Returns index of range(0, 64) for each of the 64 slots
        return value % 64;
    }

    // Finds the key and returns the value in hashmap, if none returns keyerror.
    public int findVal(String key) {
        int index = this.hashing(key);
        List<Pair<String, Integer>> position = this.hash.get(index);

        if (!position.isEmpty()) {
            // Loops through items at hashed index to return tuple value
            for (Pair<String, Integer> item : position) {
                if (item.getKey().equals(key)) {
                    return item.getValue();
                }
            }
        }

        throw new NoSuchElementException("Key does not exist.");
    }

    // Updates or adds a new key value pair.
    public void updateOrAdd(String key, int val) {
        int index = this.hashing(key);
        List<Pair<String, Integer>> position = this.hash.get(index);

        // Loops through items at hashed index
        // If the position is not empty
        if (!position.isEmpty()) {
            // Update the value if the key exists
            for (Pair<String, Integer> item : position) {
                if (item.getKey().equals(key)) {
                    item.setValue(val);
                    return;
                }
            }
        }

        // If no key exists or list is empty
        position.add(new Pair<>(key, val));
    }

    // Takes a key and deletes the key and value from the hashmap.
    public void delete(String key) {
        int index = this.hashing(key);
        List<Pair<String, Integer>> position = this.hash.get(index);

        if (!position.isEmpty()) {
            for (int i = 0; i < position.size(); i++) {
                if (position.get(i).getKey().equals(key)) {
                    position.remove(i);
                    return;
                }
            }
        }

        throw new NoSuchElementException("Key does not exist.");
    }
}

// Pair class to store key-value pairs
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }