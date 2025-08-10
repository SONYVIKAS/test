import java.util.*;

// Define custom exception for RetryImmediatelyError
class RetryImmediatelyError extends Exception {
    public RetryImmediatelyError(String errorMessage) {
        super(errorMessage);
    }
}

// Define the ETLClient class
public class ETLClient {

    public String run(DocumentService service, int maxRequests) throws RetryImmediatelyError {
        List<Map<String, Object>> documents = new ArrayList<>();
        Map<String, Object> docMap = new HashMap<>();
        docMap.put("doc-count", 0);
        docMap.put("error-count", 0);
        docMap.put("docs", new HashMap<>());
        documents.add(docMap);

        for (int i = 0; i < maxRequests; i++) {
            while (true) {
                try {
                    Map<String, Object> event = service.handleRequest();

                    if (event.get("operation").equals("add")) {
                        String docId = (String) ((Map<String, Object>) event.get("document")).get("id");
                        List<String> docData = removeWords((String) ((Map<String, Object>) event.get("document")).get("data"));

                        ((Map<String, List<String>>) documents.get(0).get("docs")).put(docId, docData);
                        documents.get(0).put("doc-count", (Integer) documents.get(0).get("doc-count") + 1);
                    }

                    if (event.get("operation").equals("update")) {
                        String docId = (String) ((Map<String, Object>) event.get("document")).get("id");
                        List<String> docData = removeWords((String) ((Map<String, Object>) event.get("document")).get("data"));

                        ((Map<String, List<String>>) documents.get(0).get("docs")).put(docId, docData);
                    }

                    if (event.get("operation").equals("delete")) {
                        String docId = (String) event.get("document-id");
                        Map<String, List<String>> docs = (Map<String, List<String>>) documents.get(0).get("docs");
                        docs.remove(docId);

                        documents.get(0).put("doc-count", (Integer) documents.get(0).get("doc-count") - 1);
                    }
                } catch (RetryImmediatelyError e) {
                    documents.get(0).put("error-count", (Integer) documents.get(0).get("error-count") + 1);
                    continue;
                }
                break;
            }
        }

        return new JSONObject(documents.get(0)).toString();
    }

    public List<String> removeWords(String string) {
        Set<String> removeWords = new HashSet<>(Arrays.asList("and", "or", "not", "but", "to", "in"));
        List<String> words = new ArrayList<>(Arrays.asList(string.toLowerCase().split(" ")));

        words.removeIf(removeWords::contains);

        return words;
    }