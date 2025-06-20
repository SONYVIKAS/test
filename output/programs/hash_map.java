import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class HashMap {
    private List<List<Map.Entry<String, Integer>>> hash;

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

    public Integer findVal(String key) {
        int index = hashing(key);
        List<Map.Entry<String, Integer>> position = hash.get(index);
        if (!position.isEmpty()) {
            for (Map.Entry<String, Integer> entry : position) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            throw new KeyError("Key does not exist.");
        } else {
            throw new KeyError("Key does not exist.");
        }
    }

    public void updateOrAdd(String key, Integer value) {
        int index = hashing(key);
        List<Map.Entry<String, Integer>> position = hash.get(index);
        if (!position.isEmpty()) {
            for (Map.Entry<String, Integer> entry : position) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    break;
                }
            }
            position.add(new HashMap.Entry(key, value));
        } else {
            position.add(new HashMap.Entry(key, value));
        }
    }

    public void delete(String key) {
        int index = hashing(key);
        List<Map.Entry<String, Integer>> position = hash.get(index);
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