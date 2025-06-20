
  ```java
  import java.io.File;
  import java.io.FileReader;
  import java.io.BufferedReader;
  import java.io.FileWriter;
  import java.io.PrintWriter;
  import java.util.regex.Pattern;
  import java.util.regex.Matcher;
  import java.util.HashMap;

  public class FindMatches {
      private String inputFile;
      private String matchingType;
      private int rowNum = 0;
      private String[] header;
      private Integer emailCol2;
      private Integer emailCol;
      private Integer phoneCol2;
      private Integer phoneCol;
      private HashMap<String, Integer> ids = new HashMap<String, Integer>();
      private int id = 1;

      public FindMatches(String inputFile, String matchingType) {
          this.inputFile = inputFile;
          this.matchingType = matchingType.toLowerCase();
          if (!this.matchingType.equals("email") &&!this.matchingType.equals("phone") &&!this.matchingType.equals("email_phone")) {
              System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
              return;
          }
          try {
              File file = new File(this.inputFile);
              FileReader fileReader = new FileReader(file);
              BufferedReader reader = new BufferedReader(fileReader);
              String line = reader.readLine();
              while (line!= null) {
                  if (this.rowNum == 0) {
                      this.header = line.split(",");
                      this.rowNum++;
                      line = reader.readLine();
                      continue;
                  }
                  String[] row = line.split(",");
                  Integer rowId = null;
                  if (this.matchingType.equals("email")) {
                      rowId = this.emailMatch(row);
                  } else if (this.matchingType.equals("phone")) {
                      rowId = this.phoneMatch(row);
                  } else if (this.matchingType.equals("email_phone")) {
                      rowId = this.emailPhoneMatch(row);
                  }
                  if (rowId!= null) {
                      String[] newRow = new String[row.length + 1];
                      newRow[0] = rowId.toString();
                      System.arraycopy(row, 0, newRow, 1, row.length);
                      line = String.join(",", newRow);
                  }
                  line = reader.readLine();
              }
              reader.close();
              fileReader.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      private Integer emailMatch(String[] row) {
          Integer rowId = null;
          Integer minId = null;
          if (this.emailCol2!= null) {
              if (row[this.emailCol2]!= null &&!row[this.emailCol2].isEmpty()) {
                  String email2 = row[this.emailCol2];
                  this.addKeyToDict(email2, minId);
                  if (this.ids.containsKey(email2)) {
                      rowId = this.ids.get(email2);
                  }
              }
          }
          if (row[this.emailCol]!= null &&!row[this.emailCol].isEmpty()) {
              String email1 = row[this.emailCol];
              this.addKeyToDict(email1, minId);
              rowId = this.ids.getOrDefault(email1, this.id);
          }
          if (rowId == null) {
              rowId = this.id;
          }
          return rowId;
      }

      private Integer phoneMatch(String[] row) {
          Integer rowId = null;
          Integer minId = null;
          if (this.phoneCol2!= null) {
              if (row[this.phoneCol2]!= null &&!row[this.phoneCol2].isEmpty()) {
                  String phone2 = this.formatPhone(row[this.phoneCol2]);
                  this.addKeyToDict(phone2, minId);
                  if (this.ids.containsKey(phone2)) {
                      rowId = this.ids.get(phone2);
                  }
              }
          }
          if (row[this.phoneCol]!= null &&!row[this.phoneCol].isEmpty()) {
              String phone1 = this.formatPhone(row[this.phoneCol]);
              this.addKeyToDict(phone1, minId);
              rowId = this.ids.getOrDefault(phone1, this.id);
          }
          if (rowId == null) {
              rowId = this.id;
          }
          return rowId;
      }

      private Integer emailPhoneMatch(String[] row) {