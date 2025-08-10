import java.util.*;

// Exception class for RetryImmediatelyError
class RetryImmediatelyError extends Exception {
    public RetryImmediatelyError(String errorMessage) {
        super(errorMessage);
    }
}

// DocumentService interface
interface DocumentService {
    Map<String, Object> handle_request() throws RetryImmediatelyError;
}

// ETLClient class
public class ETLClient {
    public String run(DocumentService service, int max_requests) {
        List<Map<String, Object>> documents = new ArrayList<>();

        Map<String, Object> docMap = new HashMap<>();
        docMap.put("doc-count", 0);
        docMap.put("error-count", 0);
        docMap.put("docs", new HashMap<String, List<String>>());

        documents.add(docMap);

        for (int i = 0; i < max_requests; i++) {
            while (true) {
                try {
                    Map<String, Object> event = service.handle_request();

                    if (event.get("operation").equals("add")) {
                        String doc_id = (String) ((Map<String, Object>) event.get("document")).get("id");
                        List<String> doc_data = this.remove_words((String) ((Map<String, Object>) event.get("document")).get("data"));

                        ((Map<String, List<String>>) documents.get(0).get("docs")).put(doc_id, doc_data);
                        documents.get(0).put("doc-count", (Integer) documents.get(0).get("doc-count") + 1);
                    }

                    if (event.get("operation").equals("update")) {
                        String doc_id = (String) ((Map<String, Object>) event.get("document")).get("id");
                        List<String> doc_data = this.remove_words((String) ((Map<String, Object>) event.get("document")).get("data"));

                        ((Map<String, List<String>>) documents.get(0).get("docs")).put(doc_id, doc_data);
                    }

                    if (event.get("operation").equals("delete")) {
                        String doc_id = (String) event.get("document-id");
                        ((Map<String, List<String>>) documents.get(0).get("docs")).remove(doc_id);
                        documents.get(0).put("doc-count", (Integer) documents.get(0).get("doc-count") - 1);
                    }
                } catch (RetryImmediatelyError e) {
                    documents.get(0).put("error-count", (Integer) documents.get(0).get("error-count") + 1);
                    continue;
                }
                break;
            }
        }

        return new Gson().toJson(documents.get(0));
    }

    private List<String> remove_words(String string) {
        Set<String> remove_words = new HashSet<>(Arrays.asList("and", "or", "not", "but", "to", "in"));
        List<String> words = new ArrayList<>(Arrays.asList(string.toLowerCase().split(" ")));

        words.removeIf(remove_words::contains);

        return words;
    }