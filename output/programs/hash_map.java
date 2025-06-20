import java.util.ArrayList;
import java.util.List;

public class HashMap {
    private List<List<Pair>> hash;

    public HashMap() {
        this.hash = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            this.hash.add(new ArrayList<>());
        }
    }

    private int hashing(String key) {
        int value = 0;
        for (char c : key.toCharArray()) {
            value += (int) c;
        }
        return value % 64;
    }

    public Object findValue(String key) {
        int index = hashing(key);
        List<Pair> position = this.hash.get(index);
        if (!position.isEmpty()) {
            for (Pair pair : position) {
                if (pair.key.equals(key)) {
                    return pair.value;
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    public void updateOrAdd(String key, Object value) {
        int index = hashing(key);
        List<Pair> position = this.hash.get(index);
        if (!position.isEmpty()) {
            for (Pair pair : position) {
                if (pair.key.equals(key)) {
                    pair.value = value;
                    break;
                }
            }
            position.add(new Pair(key, value));
        } else {
            position.add(new Pair(key, value));
        }
    }

    public void delete(String key) {
        int index = hashing(key);
        List<Pair> position = this.hash.get(index);
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

    private static class Pair {
        private String key;
        private Object value;

        public Pair(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class KeyError extends RuntimeException {
        public KeyError(String message) {
            super(message);
        }
    }