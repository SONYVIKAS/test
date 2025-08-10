import java.util.*;
import java.util.regex.*;
import java.nio.file.*;

public class FindMatches {
    private String matchingType;
    private String inputFile;
    private int rownum = 0;
    private List<String> header = null;
    private Integer emailCol2 = null;
    private Integer emailCol = null;
    private Integer phoneCol2 = null;
    private Integer phoneCol = null;
    private Map<String, Integer> ids = new HashMap<>();
    private int id = 1;

    public FindMatches(String inputFile, String matchingType) throws IOException {
        this.matchingType = matchingType.toLowerCase();
        if (!Arrays.asList("email", "phone", "email_phone").contains(this.matchingType)) {
            System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
            return;
        }

        this.inputFile = inputFile;
        List<String> lines = Files.readAllLines(Paths.get(this.inputFile));
        List<List<String>> rows = new ArrayList<>();
        for (String line : lines) {
            rows.add(Arrays.asList(line.split(",")));
        }

        // Declare the header for the file
        this.header = rows.get(0);
        this.rownum++;

        // Get the column number(s) for email
        for (int col = 0; col < this.header.size(); col++) {
            if (this.header.get(col).toLowerCase().contains("email2")) {
                this.emailCol2 = col;
            }
            if (this.header.get(col).toLowerCase().equals("email1") || this.header.get(col).toLowerCase().equals("email")) {
                this.emailCol = col;
            }
        }

        // Get the column number(s) for phone
        for (int col = 0; col < this.header.size(); col++) {
            if (this.header.get(col).toLowerCase().contains("phone2")) {
                this.phoneCol2 = col;
            }
            if (this.header.get(col).toLowerCase().equals("phone1") || this.header.get(col).toLowerCase().equals("phone")) {
                this.phoneCol = col;
            }
        }

        // Write to csv after all ids are assigned
        writeCsv(rows);
    }

    private int emailMatch(List<String> row, Integer minId) {
        Integer rowId = null;
        int iden = (minId != null) ? minId : this.id;

        if (this.emailCol2 != null && !row.get(this.emailCol2).isEmpty()) {
            String email2 = row.get(this.emailCol2);
            addKeyToDict(email2, iden);
            if (this.ids.containsKey(email2)) {
                rowId = this.ids.get(email2);
            }
        }

        if (this.emailCol != null && !row.get(this.emailCol).isEmpty()) {
            String email1 = row.get(this.emailCol);
            addKeyToDict(email1, iden);
            rowId = this.ids.getOrDefault(email1, this.id);
        }

        if (rowId == null) {
            rowId = this.id;
        }

        return rowId;
    }

    private String formatPhone(List<String> row, int column) {
        String formatPhoneCol = row.get(column).replaceAll("\\D+", "");
        if (formatPhoneCol.length() > 10) {
            formatPhoneCol = formatPhoneCol.substring(1);
        }
        return formatPhoneCol;
    }

    private int phoneMatch(List<String> row, Integer minId) {
        Integer rowId = null;
        int iden = (minId != null) ? minId : this.id;

        if (this.phoneCol2 != null && !row.get(this.phoneCol2).isEmpty()) {
            String idsKey = formatPhone(row, this.phoneCol2);
            addKeyToDict(idsKey, iden);
            if (this.ids.containsKey(idsKey)) {
                rowId = this.ids.get(idsKey);
            }
        }

        if (this.phoneCol != null && !row.get(this.phoneCol).isEmpty()) {
            String idsKey = formatPhone(row, this.phoneCol);
            addKeyToDict(idsKey, iden);
            rowId = this.ids.getOrDefault(idsKey, this.id);
        }

        if (rowId == null) {
            rowId = this.id;
        }

        return rowId;
    }

    private void addKeyToDict(String idsKey, int iden) {
        this.ids.putIfAbsent(idsKey, iden);
    }

    private void writeCsv(List<List<String>> rows) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("output_file.csv"))) {
            List<String> headerWithId = new ArrayList<>(this.header);
            headerWithId.add(0, "id");
            writer.write(String.join(",", headerWithId));
            writer.newLine();

            for (List<String> row : rows.subList(1, rows.size())) {
                Integer rowId = null;
                Integer emailRowId = null;
                Integer phoneRowId = null;

                if (this.matchingType.equals("email")) {
                    String email2 = (this.emailCol2 != null) ? row.get(this.emailCol2) : null;
                    String email1 = (this.emailCol != null) ? row.get(this.emailCol) : null;
                    Integer minId = null;

                    if (this.ids.containsKey(email2) || this.ids.containsKey(email1)) {
                        Integer email2Exists = this.ids.get(email2);
                        Integer emailExists = this.ids.get(email1);
                        minId = Collections.min(Arrays.asList(email2Exists, emailExists), Comparator.nullsLast(Comparator.naturalOrder()));
                    }

                    rowId = emailMatch(row, minId);

                } else if (this.matchingType.equals("phone")) {
                    String phone2 = (this.phoneCol2 != null) ? formatPhone(row, this.phoneCol2) : null;
                    String phone1 = (this.phoneCol != null) ? formatPhone(row, this.phoneCol) : null;
                    Integer minId = null;

                    if (this.ids.containsKey(phone2) || this.ids.containsKey(phone1)) {
                        Integer phone2Exists = this.ids.get(phone2);
                        Integer phoneExists = this.ids.get(phone1);
                        minId = Collections.min(Arrays.asList(phone2Exists, phoneExists), Comparator.nullsLast(Comparator.naturalOrder()));
                    }

                    rowId = phoneMatch(row, minId);

                } else if (this.matchingType.equals("email_phone")) {
                    String email2 = (this.emailCol2 != null) ? row.get(this.emailCol2) : null;
                    String email1 = (this.emailCol != null) ? row.get(this.emailCol) : null;
                    String phone2 = (this.phoneCol2 != null) ? formatPhone(row, this.phoneCol2) : null;
                    String phone1 = (this.phoneCol != null) ? formatPhone(row, this.phoneCol) : null;
                    Integer minId = null;

                    if (this.ids.containsKey(email2) || this.ids.containsKey(email1) || this.ids.containsKey(phone2) || this.ids.containsKey(phone1)) {
                        Integer email2Exists = this.ids.get(email2);
                        Integer emailExists = this.ids.get(email1);
                        Integer phone2Exists = this.ids.get(phone2);
                        Integer phoneExists = this.ids.get(phone1);
                        minId = Collections.min(Arrays.asList(email2Exists, emailExists, phone2Exists, phoneExists), Comparator.nullsLast(Comparator.naturalOrder()));
                        rowId = minId;
                    }

                    emailRowId = emailMatch(row, minId);
                    phoneRowId = phoneMatch(row, minId);

                    rowId = (emailRowId < phoneRowId) ? emailRowId : phoneRowId;
                }

                List<String> newRow = new ArrayList<>(row);
                newRow.add(0, rowId.toString());
                writer.write(String.join(",", newRow));
                writer.newLine();

                this.id++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new FindMatches(args[0], args[1]);
    }