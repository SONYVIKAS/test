import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FindMatches {
    // Declare necessary variables
    private String matchingType;
    private String inputFile;
    private int rownum = 0;
    private String[] header = null;
    private Integer emailCol2 = null;
    private Integer emailCol = null;
    private Integer phoneCol2 = null;
    private Integer phoneCol = null;
    private HashMap<String, Integer> ids = new HashMap<>();
    private int id = 1;

    public FindMatches(String inputFile, String matchingType) {
        // Check if matchingType input is valid
        this.matchingType = matchingType.toLowerCase();
        if (!Arrays.asList("email", "phone", "email_phone").contains(this.matchingType)) {
            System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
        }

        // Initialize variables for later assignment
        this.inputFile = inputFile;

        // Open the file with a csv reader
        try (BufferedReader reader = new BufferedReader(new FileReader(this.inputFile))) {
            // Declare the header for the file
            String line;
            while ((line = reader.readLine()) != null) {
                if (this.rownum == 0) {
                    this.header = line.split(",");
                    this.rownum += 1;
                    break;
                }
            }

            // Get the column number(s) for email
            for (int col = 0; col < this.header.length; col++) {
                if (this.header[col].toLowerCase().contains("email2")) {
                    this.emailCol2 = col;
                }
                if (this.header[col].toLowerCase().contains("email1") || "email".equals(this.header[col].toLowerCase())) {
                    this.emailCol = col;
                }
            }

            // Get the column number(s) for phone
            for (int col = 0; col < this.header.length; col++) {
                if (this.header[col].toLowerCase().contains("phone2")) {
                    this.phoneCol2 = col;
                }
                if (this.header[col].toLowerCase().contains("phone1") || "phone".equals(this.header[col].toLowerCase())) {
                    this.phoneCol = col;
                }
            }

            // Write to csv after all ids are assigned
            this.writeCsv(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int emailMatch(String[] row, Integer minId) {
        // Creates email tuples and insert unique tuples into the ids dictionary.
        Integer rowId = null;

        // Assigns the id value to minId if it exists, otherwise assigns it the next id value available
        int iden = minId != null ? minId : this.id;

        // Checks if a second email column exists
        if (this.emailCol2 != null) {
            // Checks if value exists in second email column/row, and assigns it as the key
            if (!row[this.emailCol2].isEmpty()) {
                String email2 = row[this.emailCol2];
                this.addKeyToDict(email2, iden);

                // If key exists in dictionary, assign it the rowId
                if (this.ids.get(email2) != null) {
                    rowId = this.ids.get(email2);
                }
            }
        }

        // Checks if value exists in first email column/row, and assigns it as the key
        if (!row[this.emailCol].isEmpty()) {
            String email1 = row[this.emailCol];
            // Sets the rowId to the minimum common value if it exists, otherwise sets it to the next available identifier value
            this.addKeyToDict(email1, iden);
            rowId = this.ids.getOrDefault(email1, this.id);
        }

        // If no value in the field, sets the rowId to the next available identifier value
        if (rowId == null) {
            rowId = this.id;
        }

        return rowId;
    }

    private String formatPhone(String[] row, int column) {
        // Removes formatting of phone numbers for direct comparison.
        String formatPhoneCol = row[column].replaceAll("\\D+","");

        if (formatPhoneCol.length() > 10) {
            formatPhoneCol = formatPhoneCol.substring(1);
        }

        return formatPhoneCol;
    }

    private int phoneMatch(String[] row, Integer minId) {
        // Creates phone tuples and insert unique tuples into the ids dictionary.
        Integer rowId = null;

        // Assigns the id value to minId if it exists, otherwise assigns it the next id value available
        int iden = minId != null ? minId : this.id;

        // Checks if a second phone column exists
        if (this.phoneCol2 != null) {
            // Checks if value exists in second phone column/row, and assigns it as the key
            if (!row[this.phoneCol2].isEmpty()) {
                String idsKey = this.formatPhone(row, this.phoneCol2);
                this.addKeyToDict(idsKey, iden);

                // If key exists in dictionary, assign it the rowId
                if (this.ids.get(idsKey) != null) {
                    rowId = this.ids.get(idsKey);
                }
            }
        }

        if (!row[this.phoneCol].isEmpty()) {
            String idsKey = this.formatPhone(row, this.phoneCol);
            // Sets the rowId to the minimum common value if it exists, otherwise sets it to the next available identifier value
            this.addKeyToDict(idsKey, iden);
            rowId = this.ids.getOrDefault(idsKey, this.id);
        }

        // If no value in the field, sets the rowId to the next available identifier value
        if (rowId == null) {
            rowId = this.id;
        }

        return rowId;
    }

    private void addKeyToDict(String idsKey, int iden) {
        // Places keys into a dictionary. If the key exists the key-value pair does not change. Otherwise, place it in the dictionary and assign it a new id.
        this.ids.put(idsKey, this.ids.getOrDefault(idsKey, iden));
    }

    private void writeCsv(BufferedReader reader) {
        // Using a csv writer, creates a copy of the file with unique ids prepended to each row.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output_file.csv"))) {
            // Add 'id' column to header
            String[] newHeader = new String[this.header.length + 1];
            newHeader[0] = "id";
            System.arraycopy(this.header, 0, newHeader, 1, this.header.length);

            // Write header row to new file
            writer.write(String.join(",", newHeader));
            writer.newLine();

            // Runs matching type tests based on match type given
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Integer rowId = null;
                Integer emailRowId = null;
                Integer phoneRowId = null;

                // If the matching type is 'email'
                if (this.matchingType.equals("email")) {
                    String email2 = null;
                    String email1 = null;
                    Integer minId = null;

                    // Check if email columns exist
                    if (this.emailCol2 != null) {
                        email2 = row[this.emailCol2];
                    }
                    if (this.emailCol != null) {
                        email1 = row[this.emailCol];
                    }

                    // Check if either email is in ids dictionary, get the value
                    if (this.ids.containsKey(email2) || this.ids.containsKey(email1)) {
                        Integer email2Exists = this.ids.get(email2);
                        Integer emailExists = this.ids.get(email1);

                        // If multiple values exist, find the lowest and set that to minId (rowId)
                        List<Integer> idValues = Arrays.asList(email2Exists, emailExists);
                        minId = idValues.stream().filter(Objects::nonNull).min(Integer::compare).orElse(null);
                    }

                    // See if a second email column exists
                    rowId = this.emailMatch(row, minId);
                }

                // If the matching type is 'phone'
                else if (this.matchingType.equals("phone")) {
                    String phone2 = null;
                    String phone1 = null;
                    Integer minId = null;

                    // Check if phone columns exist
                    if (this.phoneCol2 != null) {
                        phone2 = this.formatPhone(row, this.phoneCol2);
                    }
                    if (this.phoneCol != null) {
                        phone1 = this.formatPhone(row, this.phoneCol);
                    }

                    // Check if either email is in ids dictionary, get the value
                    if (this.ids.containsKey(phone2) || this.ids.containsKey(phone1)) {
                        Integer phone2Exists = this.ids.get(phone2);
                        Integer phoneExists = this.ids.get(phone1);

                        // If multiple values exist, find the lowest and set that to minId (rowId)
                        List<Integer> idValues = Arrays.asList(phone2Exists, phoneExists);
                        minId = idValues.stream().filter(Objects::nonNull).min(Integer::compare).orElse(null);
                    }

                    // See if a second phone column exists
                    rowId = this.phoneMatch(row, minId);
                }

                // If the matching type is email OR phone
                else if (this.matchingType.equals("email_phone")) {
                    String email2 = null;
                    String email1 = null;
                    String phone2 = null;
                    String phone1 = null;
                    Integer minId = null;

                    // Check if email and phone columns exist
                    if (this.emailCol2 != null) {
                        email2 = row[this.emailCol2];
                    }
                    if (this.emailCol != null) {
                        email1 = row[this.emailCol];
                    }
                    if (this.phoneCol2 != null) {
                        phone2 = this.formatPhone(row, this.phoneCol2);
                    }
                    if (this.phoneCol != null) {
                        phone1 = this.formatPhone(row, this.phoneCol);
                    }

                    // Check if either email or phone is in ids dictionary, get the value
                    if (this.ids.containsKey(email2) || this.ids.containsKey(email1) || this.ids.containsKey(phone2) || this.ids.containsKey(phone1)) {
                        Integer email2Exists = this.ids.get(email2);
                        Integer emailExists = this.ids.get(email1);
                        Integer phone2Exists = this.ids.get(phone2);
                        Integer phoneExists = this.ids.get(phone1);

                        // If multiple values exist, find the lowest and set that to minId (rowId)
                        List<Integer> idValues = Arrays.asList(email2Exists, emailExists, phone2Exists, phoneExists);
                        minId = idValues.stream().filter(Objects::nonNull).min(Integer::compare).orElse(null);
                        rowId = minId;
                    }

                    emailRowId = this.emailMatch(row, minId);
                    phoneRowId = this.phoneMatch(row, minId);

                    if (emailRowId < phoneRowId) {
                        rowId = emailRowId;
                    } else {
                        rowId = phoneRowId;
                    }
                }

                // Writes a new row with the id appended
                String[] newRow = new String[row.length + 1];
                newRow[0] = rowId.toString();
                System.arraycopy(row, 0, newRow, 1, row.length);
                writer.write(String.join(",", newRow));
                writer.newLine();

                // Increments id in ids dictionary for unique row ids
                this.id += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FindMatches(args[0], args[1]);
    }