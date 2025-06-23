 2. The `import csv` statement imports the `csv` module, which provides functions for reading and writing CSV files.
 3. The `import re` statement imports the `re` module, which provides functions for working with regular expressions.
 4. The `class FindMatches` defines a class with the name `FindMatches`.
 5. The `__init__` method is a special method that is called when an object is created from the class.
 6. The `email_match` method takes a `row` and a `min_id` as arguments and returns a `row_id`.
 7. The `format_phone` method takes a `row` and a `column` as arguments and returns a formatted phone number.
 8. The `phone_match` method takes a `row` and a `min_id` as arguments and returns a `row_id`.
 9. The `add_key_to_dict` method takes an `ids_key` and an `iden` as arguments and adds the key-value pair to the `ids` dictionary.
 10. The `write_csv` method takes a `reader`, a `header`, and a `row_is_header` as arguments and writes a new CSV file with the updated data.

Here's how you can translate the Python code to Java:

```java
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FindMatches {
    private String inputFile;
    private String matchingType;
    private int rowNum;
    private String[] header;
    private int emailCol2;
    private int emailCol;
    private int phoneCol2;
    private int phoneCol;
    private Map<String, Integer> ids;
    private int id;

    public FindMatches(String inputFile, String matchingType) {
        this.inputFile = inputFile;
        this.matchingType = matchingType.toLowerCase();
        if (!this.matchingType.equals("email") &&!this.matchingType.equals("phone") &&!this.matchingType.equals("email_phone")) {
            System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
        }
        this.rowNum = 0;
        this.header = null;
        this.emailCol2 = -1;
        this.emailCol = -1;
        this.phoneCol2 = -1;
        this.phoneCol = -1;
        this.ids = new HashMap<>();
        this.id = 1;
        try {
            FileReader csvFileIn = new FileReader(new File(this.inputFile));
            csvFileIn.read();
            for (String row : csvFileIn) {
                if (this.rowNum == 0) {
                    this.header = row.split(",");
                    this.rowNum++;
                    break;
                }
            }
            for (int col = 0; col < this.header.length; col++) {
                if (this.header[col].toLowerCase().contains("email2")) {
                    this.emailCol2 = col;
                }
                if (this.header[col].toLowerCase().contains("email1") || this.header[col].toLowerCase().equals("email")) {
                    this.emailCol = col;
                }
            }
            for (int col = 0; col < this.header.length; col++) {
                if (this.header[col].toLowerCase().contains("phone2")) {
                    this.phoneCol2 = col;
                }
                if (this.header[col].toLowerCase().contains("phone1") || this.header[col].toLowerCase().equals("phone")) {
                    this.phoneCol = col;
                }
            }
            this.writeCsv(csvFileIn, this.header);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int emailMatch(String[] row, Integer minId) {
        Integer rowId = null;
        if (minId!= null) {
            rowId = minId;
        } else {
            rowId = this.id;
        }
        if (this.emailCol2!= -1) {
            if (row[this.emailCol2]!= null &&!row[this.emailCol2].isEmpty()) {
                String email2 = row[this.emailCol2];