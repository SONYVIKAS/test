import java.util.*;
import java.util.regex.*;
import com.opencsv.*;

// Class to find matches in a CSV file based on email, phone or both
public class FindMatches {

    private String input_file;
    private String matching_type;
    private int rownum = 0;
    private String[] header;
    private Integer email_col_2 = null;
    private Integer email_col = null;
    private Integer phone_col_2 = null;
    private Integer phone_col = null;
    private HashMap<String, Integer> ids = new HashMap<>();
    private int id = 1;

    // Constructor
    public FindMatches(String input_file, String matching_type) {
        this.input_file = input_file;
        this.matching_type = matching_type.toLowerCase();

        if (!Arrays.asList("email", "phone", "email_phone").contains(this.matching_type)) {
            System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
            return;
        }

        try {
            CSVReader reader = new CSVReader(new FileReader(this.input_file));

            // Get the header
            this.header = reader.readNext();
            this.rownum++;

            // Get the column numbers for email
            for (int col = 0; col < this.header.length; col++) {
                if (this.header[col].toLowerCase().contains("email2")) {
                    this.email_col_2 = col;
                }
                if (this.header[col].toLowerCase().contains("email1") || this.header[col].toLowerCase().equals("email")) {
                    this.email_col = col;
                }
            }

            // Get the column numbers for phone
            for (int col = 0; col < this.header.length; col++) {
                if (this.header[col].toLowerCase().contains("phone2")) {
                    this.phone_col_2 = col;
                }
                if (this.header[col].toLowerCase().contains("phone1") || this.header[col].toLowerCase().equals("phone")) {
                    this.phone_col = col;
                }
            }

            // Write to CSV after all ids are assigned
            this.write_csv(reader, this.header);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to create email tuples and insert unique tuples into the ids dictionary
    private int email_match(String[] row, Integer min_id) {
        Integer row_id = null;
        Integer iden = (min_id != null) ? min_id : this.id;

        // Check if a second email column exists
        if (this.email_col_2 != null) {
            // Check if value exists in second email column/row, and assigns it as the key
            if (row[this.email_col_2] != null) {
                String email2 = row[this.email_col_2];
                this.add_key_to_dict(email2, iden);

                // If key exists in dictionary, assign it the row_id
                if (this.ids.get(email2) != null) {
                    row_id = this.ids.get(email2);
                }
            }
        }

        // Check if value exists in first email column/row, and assigns it as the key
        if (row[this.email_col] != null) {
            String email1 = row[this.email_col];
            // Sets the row_id to the minimum common value if it exists, otherwise sets it to the next available identifier value
            this.add_key_to_dict(email1, iden);
            row_id = this.ids.getOrDefault(email1, this.id);
        }

        // If no value in the field, sets the row_id to the next available identifier value
        if (row_id == null) {
            row_id = this.id;
        }

        return row_id;
    }

    // Method to remove formatting of phone numbers for direct comparison
    private String format_phone(String[] row, int column) {
        String format_phone_col = row[column].replaceAll("\\D+","");

        if (format_phone_col.length() > 10) {
            format_phone_col = format_phone_col.substring(1);
        }

        return format_phone_col;
    }

    // Method to create phone tuples and insert unique tuples into the ids dictionary
    private int phone_match(String[] row, Integer min_id) {
        Integer row_id = null;
        Integer iden = (min_id != null) ? min_id : this.id;

        // Check if a second phone column exists
        if (this.phone_col_2 != null) {
            // Check if value exists in second phone column/row, and assigns it as the key
            if (row[this.phone_col_2] != null) {
                String ids_key = this.format_phone(row, this.phone_col_2);
                this.add_key_to_dict(ids_key, iden);

                // If key exists in dictionary, assign it the row_id
                if (this.ids.get(ids_key) != null) {
                    row_id = this.ids.get(ids_key);
                }
            }
        }

        if (row[this.phone_col] != null) {
            String ids_key = this.format_phone(row, this.phone_col);
            // Sets the row_id to the minimum common value if it exists, otherwise sets it to the next available identifier value
            this.add_key_to_dict(ids_key, iden);
            row_id = this.ids.getOrDefault(ids_key, this.id);
        }

        // If no value in the field, sets the row_id to the next available identifier value
        if (row_id == null) {
            row_id = this.id;
        }

        return row_id;
    }

    // Method to place keys into a dictionary. If the key exists the key-value pair does not change. Otherwise, place it in the dictionary and assign it a new id
    private void add_key_to_dict(String ids_key, int iden) {
        this.ids.putIfAbsent(ids_key, iden);
    }

    // Method to create a copy of the file with unique ids prepended to each row
    private void write_csv(CSVReader reader, String[] header) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("output_file.csv"));

            // Add 'id' column to header
            String[] new_header = new String[header.length + 1];
            new_header[0] = "id";
            System.arraycopy(header, 0, new_header, 1, header.length);

            // Write header row to new file
            writer.writeNext(new_header);

            // Runs matching type tests based on match type given
            String[] row;
            while ((row = reader.readNext()) != null) {
                Integer row_id = null;
                Integer email_row_id = null;
                Integer phone_row_id = null;

                // If the matching type is 'email'
                if (this.matching_type.equals("email")) {
                    String email2 = (this.email_col_2 != null) ? row[this.email_col_2] : null;
                    String email1 = (this.email_col != null) ? row[this.email_col] : null;
                    Integer min_id = null;

                    // Check if either email is in ids dictionary, get the value
                    if (this.ids.containsKey(email2) || this.ids.containsKey(email1)) {
                        Integer email2_exists = this.ids.get(email2);
                        Integer email_exists = this.ids.get(email1);

                        // If multiple values exist, find the lowest and set that to min_id (row_id)
                        List<Integer> id_values = Arrays.asList(email2_exists, email_exists);
                        min_id = id_values.stream().filter(Objects::nonNull).min(Integer::compare).orElse(null);
                    }

                    // See if a second email column exists
                    row_id = this.email_match(row, min_id);
                }

                // If the matching type is 'phone'
                else if (this.matching_type.equals("phone")) {
                    String phone2 = (this.phone_col_2 != null) ? this.format_phone(row, this.phone_col_2) : null;
                    String phone1 = (this.phone_col != null) ? this.format_phone(row, this.phone_col) : null;
                    Integer min_id = null;

                    // Check if either phone is in ids dictionary, get the value
                    if (this.ids.containsKey(phone2) || this.ids.containsKey(phone1)) {
                        Integer phone2_exists = this.ids.get(phone2);
                        Integer phone_exists = this.ids.get(phone1);

                        // If multiple values exist, find the lowest and set that to min_id (row_id)
                        List<Integer> id_values = Arrays.asList(phone2_exists, phone_exists);
                        min_id = id_values.stream().filter(Objects::nonNull).min(Integer::compare).orElse(null);
                    }

                    // See if a second phone column exists
                    row_id = this.phone_match(row, min_id);
                }

                // If the matching type is email OR phone
                else if (this.matching_type.equals("email_phone")) {
                    String email2 = (this.email_col_2 != null) ? row[this.email_col_2] : null;
                    String email1 = (this.email_col != null) ? row[this.email_col] : null;
                    String phone2 = (this.phone_col_2 != null) ? this.format_phone(row, this.phone_col_2) : null;
                    String phone1 = (this.phone_col != null) ? this.format_phone(row, this.phone_col) : null;
                    Integer min_id = null;

                    // Check if either email or phone is in ids dictionary, get the value
                    if (this.ids.containsKey(email2) || this.ids.containsKey(email1) || this.ids.containsKey(phone2) || this.ids.containsKey(phone1)) {
                        Integer email2_exists = this.ids.get(email2);
                        Integer email_exists = this.ids.get(email1);
                        Integer phone2_exists = this.ids.get(phone2);
                        Integer phone_exists = this.ids.get(phone1);

                        // If multiple values exist, find the lowest and set that to min_id (row_id)
                        List<Integer> id_values = Arrays.asList(email2_exists, email_exists, phone2_exists, phone_exists);
                        min_id = id_values.stream().filter(Objects::nonNull).min(Integer::compare).orElse(null);
                        row_id = min_id;
                    }

                    email_row_id = this.email_match(row, min_id);
                    phone_row_id = this.phone_match(row, min_id);

                    if (email_row_id < phone_row_id) {
                        row_id = email_row_id;
                    } else {
                        row_id = phone_row_id;
                    }
                }

                // Writes a new row with the id appended
                String[] new_row = new String[row.length + 1];
                new_row[0] = row_id.toString();
                System.arraycopy(row, 0, new_row, 1, row.length);
                writer.writeNext(new_row);

                // Increments id in ids dictionary for unique row ids
                this.id++;
            }

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FindMatches(args[0], args[1]);
    }