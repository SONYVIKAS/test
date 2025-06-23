import java.util.ArrayList;
import java.util.HashMap;

public class HashMapJava {
    private ArrayList<ArrayList<Tuple>> hash;

    public HashMapJava() {
        hash = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            hash.add(new ArrayList<>());
        }
    }

    private int hashing(String key) {
        int value = 0;
        for (char c : key.toCharArray()) {
            value += (int) c;
        }
        int index = value % 64;
        return index;
    }

    public Tuple find_val(String key) {
        int index = hashing(key);
        ArrayList<Tuple> position = hash.get(index);
        if (!position.isEmpty()) {
            for (Tuple item : position) {
                if (item.key.equals(key)) {
                    return item;
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    public void update_or_add(String key, String val) {
        int index = hashing(key);
        ArrayList<Tuple> position = hash.get(index);
        if (!position.isEmpty()) {
            for (Tuple item : position) {
                if (item.key.equals(key)) {
                    item.val = val;
                    break;
                }
            }
            position.add(new Tuple(key, val));
        } else {
            position.add(new Tuple(key, val));
        }
    }

    public void delete(String key) {
        int index = hashing(key);
        ArrayList<Tuple> position = hash.get(index);
        if (!position.isEmpty()) {
            for (int i = 0; i < position.size(); i++) {
                if (position.get(i).key.equals(key)) {
                    position.remove(i);
                    break;
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    private static class Tuple {
        String key;
        String val;

        public Tuple(String key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    private static class KeyError extends RuntimeException {
        public KeyError(String message) {
            super(message);
        }
    }