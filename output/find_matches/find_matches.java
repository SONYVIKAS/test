
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FindMatches {
    private String inputFile;
    private String matchingType;
    private int rowNum = 0;
    private String[] header;
    private Integer emailCol2;
    private Integer emailCol;
    private Integer phoneCol2;
    private Integer phoneCol;
    private Map<String, Integer> ids = new HashMap<>();
    private int id = 1;

    public FindMatches(String inputFile, String matchingType) throws IOException {
        this.inputFile = inputFile;
        this.matchingType = matchingType.toLowerCase();
        if (!matchingType.equals("email") &&!matchingType.equals("phone") &&!matchingType.equals("email_phone")) {
            System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(this.inputFile))) {
            String line;
            while ((line = reader.readLine())!= null) {
                if (rowNum == 0) {
                    header = line.split(",");
                    rowNum++;
                    continue;
                }

                for (int col = 0; col < header.length; col++) {
                    if (header[col].toLowerCase().contains("email2")) {
                        emailCol2 = col;
                    }
                    if (header[col].toLowerCase().contains("email1") || header[col].toLowerCase().equals("email")) {
                        emailCol = col;
                    }
                    if (header[col].toLowerCase().contains("phone2")) {
                        phoneCol2 = col;
                    }
                    if (header[col].toLowerCase().contains("phone1") || header[col].toLowerCase().equals("phone")) {
                        phoneCol = col;
                    }
                }
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(this.inputFile));
             FileWriter writer = new FileWriter("output_file.csv")) {
            String line;
            while ((line = reader.readLine())!= null) {
                String[] row = line.split(",");
                Integer rowId = null;
                if (rowNum == 0) {
                    writer.write("id," + line + "\n");
                    rowNum++;
                    continue;
                }

                if (matchingType.equals("email")) {
                    rowId = emailMatch(row);
                } else if (matchingType.equals("phone")) {
                    rowId = phoneMatch(row);
                } else if (matchingType.equals("email_phone")) {
                    rowId = emailPhoneMatch(row);
                }

                writer.write(rowId + "," + line + "\n");
                id++;
            }
        }
    }

    private Integer emailMatch(String[] row) {
        Integer rowId = null;
        Integer minId = null;

        if (emailCol2!= null && row[emailCol2]!= null &&!row[emailCol2].isEmpty()) {
            String email2 = row[emailCol2];
            addKeyToDict(email2, minId);

            if (ids.containsKey(email2)) {
                rowId = ids.get(email2);
            }
        }

        if (emailCol!= null && row[emailCol]!= null &&!row[emailCol].isEmpty()) {
            String email1 = row[emailCol];
            addKeyToDict(email1, minId);
            rowId = ids.getOrDefault(email1, id);
        }

        if (rowId == null) {
            rowId = id;
        }

        return rowId;
    }

    private Integer phoneMatch(String[] row) {
        Integer rowId = null;
        Integer minId = null;

        if (phoneCol2!= null && row[phoneCol2]!= null &&!row[phoneCol2].isEmpty()) {
            String phone2 = formatPhone(row[phoneCol2]);
            addKeyToDict(phone2, minId);

            if (ids.containsKey(phone2)) {
                rowId = ids.get(phone2);
            }
        }

        if (phoneCol!= null && row[phoneCol]!= null &&!row[phoneCol].isEmpty()) {
            String phone1 = formatPhone(row[phoneCol]);
            addKeyToDict(phone1, minId);
            rowId = ids.getOrDefault(phone1, id);
        }

        if (rowId == null) {
            rowId = id;
        }

        return rowId;