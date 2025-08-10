
import java.util.ArrayList;
import java.util.List;

public class HashMap {

    private List<List<KeyValuePair>> hash;

    // Constructor to create hash list of lists
    public HashMap() {
        // In a 64-bit system, creates 64 lists in a list
        hash = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            hash.add(new ArrayList<>());
        }
    }

    // Hashes a key and returns the hashed index
    private int hashing(String key) {
        int value = 0;

        // Gets the total ASCII value of the key
        for (char c : key.toCharArray()) {
            value += (int) c;
        }

        // Returns index of range(0, 64) for each of the 64 slots
        return value % 64;
    }

    // Finds the key and returns the value in hashmap, if none returns KeyError
    public String findVal(String key) throws KeyError {
        int index = hashing(key);
        List<KeyValuePair> position = hash.get(index);

        if (!position.isEmpty()) {
            // Loops through items at hashed index to return tuple value
            for (KeyValuePair item : position) {
                if (item.key.equals(key)) {
                    return item.value;
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    // Updates or adds a new key value pair
    public void updateOrAdd(String key, String val) {
        int index = hashing(key);
        List<KeyValuePair> position = hash.get(index);

        // Loops through items at hashed index
        // If the position is not empty
        if (!position.isEmpty()) {
            // Update the value if the key exists
            for (KeyValuePair item : position) {
                if (item.key.equals(key)) {
                    item.value = val;
                    return;
                }
            }
            // If no key exists
            position.add(new KeyValuePair(key, val));
        } else {
            // If list is empty
            position.add(new KeyValuePair(key, val));
        }
    }

    // Takes a key and deletes the key and value from the hashmap
    public void delete(String key) throws KeyError {
        int index = hashing(key);
        List<KeyValuePair> position = hash.get(index);

        if (!position.isEmpty()) {
            for (int i = 0; i < position.size(); i++) {
                if (position.get(i).key.equals(key)) {
                    position.remove(i);
                    return;
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    // Inner class to represent key-value pairs
    private static class KeyValuePair {
        String key;
        String value;

        KeyValuePair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Custom exception class for key errors
    public static class KeyError extends Exception {
        public KeyError(String message) {
            super(message);
        }
    }