import java.util.List;
import java.util.ArrayList;

public class HashMap {

    private List<List<Tuple>> hash;

    public HashMap() {
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

    public Object findVal(String key) {
        int index = hashing(key);
        List<Tuple> position = hash.get(index);

        if (!position.isEmpty()) {
            for (Tuple item : position) {
                if (item.getKey().equals(key)) {
                    return item.getValue();
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    public void updateOrAdd(String key, Object value) {
        int index = hashing(key);
        List<Tuple> position = hash.get(index);

        if (!position.isEmpty()) {
            for (Tuple item : position) {
                if (item.getKey().equals(key)) {
                    item.setValue(value);
                    break;
                }
            }
            position.add(new Tuple(key, value));
        } else {
            position.add(new Tuple(key, value));
        }
    }

    public void delete(String key) {
        int index = hashing(key);
        List<Tuple> position = hash.get(index);

        if (!position.isEmpty()) {
            for (int i = 0; i < position.size(); i++) {
                if (position.get(i).getKey().equals(key)) {
                    position.remove(i);
                    break;
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    private class Tuple {

        private String key;
        private Object value;

        public Tuple(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    private class KeyError extends Exception {

        public KeyError(String message) {
            super(message);
        }
    }