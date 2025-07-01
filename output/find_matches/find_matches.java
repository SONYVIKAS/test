
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FindMatches {
    private final String inputFile;
    private final String matchingType;
    private int rowNum;
    private String[] header;
    private Integer emailCol2;
    private Integer emailCol;
    private Integer phoneCol2;
    private Integer phoneCol;
    private Map<String, Integer> ids;
    private Integer id;

    public FindMatches(String inputFile, String matchingType) throws IOException {
        this.inputFile = inputFile;
        this.matchingType = matchingType;
        this.rowNum = 0;
        this.header = null;
        this.emailCol2 = null;
        this.emailCol = null;
        this.phoneCol2 = null;
        this.phoneCol = null;
        this.ids = new HashMap<>();
        this.id = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.inputFile))) {
            String line;
            while ((line = reader.readLine())!= null) {
                if (this.rowNum == 0) {
                    this.header = line.split(",");
                    this.rowNum++;
                    continue;
                }

                String[] row = line.split(",");
                if (this.matchingType.equals("email")) {
                    this.emailMatch(row, null);
                } else if (this.matchingType.equals("phone")) {
                    this.phoneMatch(row, null);
                } else if (this.matchingType.equals("email_phone")) {
                    this.emailMatch(row, null);
                    this.phoneMatch(row, null);
                }
            }
        }

        this.writeCsv();
    }

    private void emailMatch(String[] row, Integer minId) {
        Integer rowId = null;

        if (minId!= null) {
            rowId = minId;
        } else {
            rowId = this.id;
        }

        if (this.emailCol2!= null) {
            if (row[this.emailCol2]!= null) {
                String email2 = row[this.emailCol2];
                this.addKeyToDict(email2, rowId);

                if (this.ids.get(email2)!= null) {
                    rowId = this.ids.get(email2);
                }
            }
        }

        if (row[this.emailCol]!= null) {
            String email1 = row[this.emailCol];
            this.addKeyToDict(email1, rowId);
            rowId = this.ids.get(email1, this.id);
        }

        if (rowId == null) {
            rowId = this.id;
        }
    }

    private String formatPhone(String phone) {
        return Pattern.compile("\\D+").matcher(phone).replaceAll("");
    }

    private void phoneMatch(String[] row, Integer minId) {
        Integer rowId = null;

        if (minId!= null) {
            rowId = minId;
        } else {
            rowId = this.id;
        }

        if (this.phoneCol2!= null) {
            if (row[this.phoneCol2]!= null) {
                String phone2 = this.formatPhone(row[this.phoneCol2]);
                this.addKeyToDict(phone2, rowId);

                if (this.ids.get(phone2)!= null) {
                    rowId = this.ids.get(phone2);
                }
            }
        }

        if (row[this.phoneCol]!= null) {
            String phone1 = this.formatPhone(row[this.phoneCol]);
            this.addKeyToDict(phone1, rowId);
            rowId = this.ids.get(phone1, this.id);
        }

        if (rowId == null) {
            rowId = this.id;
        }
    }

    private void addKeyToDict(String key, Integer value) {
        if (this.ids.get(key) == null) {
            this.ids.put(key, value);
        }
    }

    private void writeCsv() throws IOException {
        try (FileWriter writer = new FileWriter("output_file.csv")) {
            for (String headerColumn : this.header) {
                writer.write("id," + headerColumn + "\n");
            }

            for (String key : this.ids.keySet()) {