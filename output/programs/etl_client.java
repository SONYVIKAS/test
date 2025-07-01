import java.util.HashMap;
import java.util.Map;

public class ETLClient {
    public String run(DocumentService service, int max_requests) {
        Map<String, Object> documents = new HashMap<String, Object>();
        documents.put("doc-count", 0);
        documents.put("error-count", 0);
        documents.put("docs", new HashMap<String, Object>());

        for (int i = 0; i < max_requests; i++) {
            Map<String, Object> event = null;
            try {
                event = service.handle_request();
            } catch (RetryImmediatelyError e) {
                documents.put("error-count", (int) documents.get("error-count") + 1);
                continue;
            }

            if (event.get("operation").equals("add")) {
                String doc_id = (String) ((Map<String, Object>) event.get("document")).get("id");
                String doc_data = (String) ((Map<String, Object>) event.get("document")).get("data");
                doc_data = remove_words(doc_data);
                ((Map<String, Object>) documents.get("docs")).put(doc_id, doc_data);
                documents.put("doc-count", (int) documents.get("doc-count") + 1);
            } else if (event.get("operation").equals("update")) {
                String doc_id = (String) ((Map<String, Object>) event.get("document")).get("id");
                String doc_data = (String) ((Map<String, Object>) event.get("document")).get("data");
                doc_data = remove_words(doc_data);
                ((Map<String, Object>) documents.get("docs")).put(doc_id, doc_data);
            } else if (event.get("operation").equals("delete")) {
                String doc_id = (String) event.get("document-id");
                ((Map<String, Object>) documents.get("docs")).remove(doc_id);
                documents.put("doc-count", (int) documents.get("doc-count") - 1);
            }
        }

        return new Gson().toJson(documents);
    }

    public String remove_words(String string) {
        String[] remove_words = {"and", "or", "not", "but", "to", "in"};
        String[] words = string.split(" ");

        for (String word : words) {
            for (String remove_word : remove_words) {
                if (word.equals(remove_word)) {
                    word = "";
                    break;
                }
            }
        }

        return String.join(" ", words);
    }