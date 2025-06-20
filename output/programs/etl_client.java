  import org.json.JSONObject;

  public class ETLClient {
      public String run(DocumentService service, int maxRequests) {
          JSONObject documents = new JSONObject();
          documents.put("doc-count", 0);
          documents.put("error-count", 0);
          documents.put("docs", new JSONObject());

          for (int i = 0; i < maxRequests; i++) {
              while (true) {
                  try {
                      JSONObject event = service.handleRequest();
                      if (event.getString("operation").equals("add")) {
                          String docId = event.getJSONObject("document").getString("id");
                          String docData = event.getJSONObject("document").getString("data");
                          docData = removeWords(docData);
                          documents.getJSONObject("docs").put(docId, docData);
                          documents.put("doc-count", documents.getInt("doc-count") + 1);
                      } else if (event.getString("operation").equals("update")) {
                          String docId = event.getJSONObject("document").getString("id");
                          String docData = event.getJSONObject("document").getString("data");
                          docData = removeWords(docData);
                          documents.getJSONObject("docs").put(docId, docData);
                      } else if (event.getString("operation").equals("delete")) {
                          String docId = event.getString("document-id");
                          documents.getJSONObject("docs").remove(docId);
                          documents.put("doc-count", documents.getInt("doc-count") - 1);
                      }
                  } catch (RetryImmediatelyError e) {
                      documents.put("error-count", documents.getInt("error-count") + 1);
                      continue;
                  }
                  break;
              }
          }
          return documents.toString();
      }

      public String removeWords(String string) {
          String[] removeWords = {"and", "or", "not", "but", "to", "in"};
          String[] words = string.split(" ");
          for (String word : words) {
              for (String removeWord : removeWords) {
                  if (word.equals(removeWord)) {
                      word = "";
                      break;
                  }
              }
          }
          return String.join(" ", words);
      }
  }