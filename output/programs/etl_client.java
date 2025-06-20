import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.json.JSONObject;
import org.json.JSONException;

public class ETLClient {
    public String run(DocumentService service, int maxRequests) throws JSONException {
        Map<String, List<String>> docs = new HashMap<String, List<String>>();
        int docCount = 0;
        int errorCount = 0;

        for (int i = 0; i < maxRequests; i++) {
            try {
                Map<String, Object> event = service.handleRequest();
                String operation = (String) event.get("operation");

                if (operation.equals("add")) {
                    Map<String, Object> document = (Map<String, Object>) event.get("document");
                    String docId = (String) document.get("id");
                    String docData = (String) document.get("data");
                    List<String> sanitizedData = removeWords(docData);
                    docs.put(docId, sanitizedData);
                    docCount++;
                } else if (operation.equals("update")) {
                    Map<String, Object> document = (Map<String, Object>) event.get("document");
                    String docId = (String) document.get("id");
                    String docData = (String) document.get("data");
                    List<String> sanitizedData = removeWords(docData);
                    docs.put(docId, sanitizedData);
                } else if (operation.equals("delete")) {
                    String docId = (String) event.get("document-id");
                    docs.remove(docId);
                    docCount--;
                }
            } catch (RetryImmediatelyError e) {
                errorCount++;
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("doc-count", docCount);
        result.put("error-count", errorCount);
        result.put("docs", docs);

        return new JSONObject(result).toString();
    }

    private List<String> removeWords(String data) {
        Set<String> removeWords = new HashSet<String>();
        removeWords.add("and");
        removeWords.add("or");
        removeWords.add("not");
        removeWords.add("but");
        removeWords.add("to");
        removeWords.add("in");

        List<String> words = new ArrayList<String>();
        for (String word : data.split(" ")) {
            if (!removeWords.contains(word)) {
                words.add(word.toLowerCase());
            }
        }

        return words;
    }