import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ETLClient {

    public String run(DocumentService service, int maxRequests) {
        // Initialize the result structure
        Map<String, Object> documents = new HashMap<>();
        documents.put("doc-count", 0);
        documents.put("error-count", 0);
        documents.put("docs", new HashMap<String, String[]>());

        for (int i = 0; i < maxRequests; i++) {
            while (true) {
                try {
                    Map<String, Object> event = service.handleRequest();

                    String operation = (String) event.get("operation");
                    Map<String, String> document = (Map<String, String>) event.get("document");
                    String docId = document.get("id");
                    String[] docData = removeWords(document.get("data"));

                    switch (operation) {
                        case "add":
                            // Add new document
                            ((Map<String, String[]>) documents.get("docs")).put(docId, docData);
                            documents.put("doc-count", (int) documents.get("doc-count") + 1);
                            break;
                        case "update":
                            // Update existing document
                            ((Map<String, String[]>) documents.get("docs")).put(docId, docData);
                            break;
                        case "delete":
                            // Delete document
                            ((Map<String, String[]>) documents.get("docs")).remove(docId);
                            documents.put("doc-count", (int) documents.get("doc-count") - 1);
                            break;
                    }
                } catch (RetryImmediatelyError e) {
                    // Increment error count and retry
                    documents.put("error-count", (int) documents.get("error-count") + 1);
                    continue;
                }
                break;
            }
        }

        // Convert the result to JSON string
        return new JSONObject(documents).toString();
    }

    private String[] removeWords(String data) {
        // Remove unwanted words and convert to lowercase
        Set<String> removeWords = new HashSet<>();
        removeWords.add("and");
        removeWords.add("or");
        removeWords.add("not");
        removeWords.add("but");
        removeWords.add("to");
        removeWords.add("in");

        String[] words = data.toLowerCase().split(" ");
        return Arrays.stream(words)
                .filter(word -> !removeWords.contains(word))
                .toArray(String[]::new);
    }
}

// Mock classes for testing purposes
class DocumentService {
    public Map<String, Object> handleRequest() throws RetryImmediatelyError {
        // Mock implementation
        return new HashMap<>();
    }
}

class RetryImmediatelyError extends Exception {
    // Mock exception class