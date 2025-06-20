
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
    private int rownum = 0;
    private String[] header;
    private int emailCol2;
    private int emailCol;
    private int phoneCol2;
    private int phoneCol;
    private Map<String, Integer> ids = new HashMap<String, Integer>();
    private int id = 1;

    public FindMatches(String inputFile, String matchingType) throws IOException {
        this.inputFile = inputFile;
        this.matchingType = matchingType.toLowerCase();
        if (!matchingType.equals("email") &&!matchingType.equals("phone") &&!matchingType.equals("email_phone")) {
            System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
        }

        BufferedReader csvFileIn = new BufferedReader(new FileReader(this.inputFile));
        String line = csvFileIn.readLine();
        while (line!= null) {
            if (rownum == 0) {
                header = line.split(",");
                rownum++;
                line = csvFileIn.readLine();
                continue;
            }

            for (int col = 0; col < header.length; col++) {
                if (header[col].toLowerCase().contains("email2")) {
                    emailCol2 = col;
                } else if (header[col].toLowerCase().contains("email1") || header[col].toLowerCase().equals("email")) {
                    emailCol = col;
                } else if (header[col].toLowerCase().contains("phone2")) {
                    phoneCol2 = col;
                } else if (header[col].toLowerCase().contains("phone1") || header[col].toLowerCase().equals("phone")) {
                    phoneCol = col;
                }
            }

            writeCsv(csvFileIn, header);

            line = csvFileIn.readLine();
        }

        csvFileIn.close();
    }

    private int emailMatch(String[] row, Integer minId) {
        Integer rowId = null;

        if (minId!= null) {
            rowId = minId;
        } else {
            rowId = id;
        }

        if (emailCol2!= -1) {
            if (row[emailCol2]!= null &&!row[emailCol2].isEmpty()) {
                String email2 = row[emailCol2];
                addKeyToDict(email2, rowId);

                if (ids.get(email2)!= null) {
                    rowId = ids.get(email2);
                }
            }
        }

        if (row[emailCol]!= null &&!row[emailCol].isEmpty()) {
            String email1 = row[emailCol];
            addKeyToDict(email1, rowId);
            rowId = ids.get(email1, id);
        }

        if (rowId == null) {
            rowId = id;
        }

        return rowId;
    }

    private String formatPhone(String[] row, int column) {
        String formatPhoneCol = row[column].replaceAll("\\D+", "");

        if (formatPhoneCol.length() > 10) {
            formatPhoneCol = formatPhoneCol.substring(1);
        }

        return formatPhoneCol;
    }

    private int phoneMatch(String[] row, Integer minId) {
        Integer rowId = null;

        if (minId!= null) {
            rowId = minId;
        } else {
            rowId = id;
        }

        if (phoneCol2!= -1) {
            if (row[phoneCol2]!= null &&!row[phoneCol2].isEmpty()) {
                String idsKey = formatPhone(row, phoneCol2);
                addKeyToDict(idsKey, rowId);

                if (ids.get(idsKey)!= null) {
                    rowId = ids.get(idsKey);
                }
            }
        }

        if (row[phoneCol]!= null &&!row[phoneCol].isEmpty()) {
            String idsKey = formatPhone(row, phoneCol);
            addKeyToDict(idsKey, rowId);
            rowId = ids.get(idsKey, id);
        }

        if (rowId == null) {
            rowId = id;
        }

        return rowId;
    }

    private void addKeyToDict(String idsKey, Integer id) {