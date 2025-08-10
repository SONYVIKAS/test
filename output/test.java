import java.util.*;
import java.util.regex.*;
import java.nio.file.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.IOUtils;

public class PDFDataExtractionTool {

    public static void main(String[] args) {
        // Initialize the application
        System.out.println("PDF Data Extraction Tool");

        // Choose the genre
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option: 1 for Visawaale, 2 for Jetsave");
        int choice = scanner.nextInt();

        if (choice == 1) {
            // Visawaale
            System.out.println("Visawaale");

            // Prompt user to upload PDF files
            System.out.println("Enter the path of the PDF files (comma separated):");
            scanner.nextLine(); // consume newline
            String[] filePaths = scanner.nextLine().split(",");

            if (filePaths.length > 0) {
                List<Map<String, String>> dataList = new ArrayList<>();

                // Patterns to extract specific fields
                Map<String, String> patterns = new HashMap<>();
                patterns.put("Reference No.", "Reference No\\. & Date\\.\\s*([\\w-]+)");
                patterns.put("Other References", "Other References\\s*([\\w\\d]+)");
                patterns.put("Service Charge", "Service Charges?.*\\s([\\d,]+\\.\\d{2})");
                patterns.put("IGST Amount", "IGST @\\d+%.*?([\\d,]+\\.\\d{2})");
                patterns.put("IGST Rate", "IGST @(\\d+%)");
                patterns.put("Country Before Visa", "([A-Za-z]+)\\s*Visa\\s*Fee");

                // Process each PDF file
                for (String filePath : filePaths) {
                    try {
                        PdfReader reader = new PdfReader(filePath.trim());
                        StringBuilder pdfText = new StringBuilder();
                        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                            pdfText.append(PdfTextExtractor.getTextFromPage(reader, i));
                        }
                        reader.close();

                        // Extract data for the current PDF
                        Map<String, String> extractedData = new HashMap<>();
                        for (Map.Entry<String, String> entry : patterns.entrySet()) {
                            Matcher matcher = Pattern.compile(entry.getValue()).matcher(pdfText.toString());
                            extractedData.put(entry.getKey(), matcher.find() ? matcher.group(1).trim() : null);
                        }

                        // Extract "Total (in words)" and convert to numeric value
                        Matcher totalInWordsMatcher = Pattern.compile("INR\\s*(.*?)\\s*Only", Pattern.CASE_INSENSITIVE).matcher(pdfText.toString());
                        if (totalInWordsMatcher.find()) {
                            String totalInWords = totalInWordsMatcher.group(1).trim();
                            try {
                                extractedData.put("Total", String.valueOf(wordToNumber(totalInWords.toLowerCase())));
                            } catch (Exception e) {
                                extractedData.put("Total", "Conversion Error");
                            }
                        }

                        // Append the extracted data to the list
                        dataList.add(extractedData);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Create a CSV from the extracted data
                try {
                    FileWriter out = new FileWriter("extracted_data.csv");
                    CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(dataList.get(0).keySet().toArray(new String[0])));
                    for (Map<String, String> data : dataList) {
                        printer.printRecord(data.values());
                    }
                    printer.flush();
                    printer.close();
                    System.out.println("Data extracted and saved to extracted_data.csv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Please upload PDF files to proceed.");
            }

        } else if (choice == 2) {
            // Jetsave
            System.out.println("Jetsave");

            // Prompt user to upload PDF files
            System.out.println("Enter the path of the PDF files (comma separated):");
            scanner.nextLine(); // consume newline
            String[] filePaths = scanner.nextLine().split(",");

            if (filePaths.length > 0) {
                List<Map<String, String>> allInvoiceDetails = new ArrayList<>();

                // Process each uploaded file
                for (String filePath : filePaths) {
                    try {
                        String pdfText = extractTextFromPdf(filePath.trim());
                        Map<String, String> invoiceDetails = parseTextToDataframe(pdfText);

                        // Store data from each file
                        allInvoiceDetails.add(invoiceDetails);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Create a CSV from the combined invoice details
                try {
                    FileWriter out = new FileWriter("invoice_details.csv");
                    CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(allInvoiceDetails.get(0).keySet().toArray(new String[0])));
                    for (Map<String, String> invoice : allInvoiceDetails) {
                        printer.printRecord(invoice.values());
                    }
                    printer.flush();
                    printer.close();
                    System.out.println("Invoice details extracted and saved to invoice_details.csv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        scanner.close();
    }

    // Function to extract text from a PDF
    private static String extractTextFromPdf(String filePath) throws IOException {
        PdfReader reader = new PdfReader(filePath);
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            text.append(PdfTextExtractor.getTextFromPage(reader, i)).append("\n");
        }
        reader.close();
        return text.toString();
    }

    // Function to parse the extracted text into structured data
    private static Map<String, String> parseTextToDataframe(String text) {
        String[] lines = text.split("\\r?\\n");
        Map<String, String> invoiceDetails = new HashMap<>();
        invoiceDetails.put("invoice_number", null);
        invoiceDetails.put("corporate", null);
        invoiceDetails.put("date", null);
        invoiceDetails.put("pax_name", null);
        invoiceDetails.put("country", null);
        invoiceDetails.put("total_amount", null);
        invoiceDetails.put("igst", null);
        invoiceDetails.put("cgst", null);
        invoiceDetails.put("sgst", null);
        invoiceDetails.put("net_amount", null);

        for (String line : lines) {
            if (line.contains("Invoice No")) {
                invoiceDetails.put("invoice_number", line.split(":")[1].trim().replace("DEL", ""));
            } else if (line.contains("Date :")) {
                invoiceDetails.put("date", line.split(":")[1].trim());
            } else if (line.contains("Pax Name :")) {
                invoiceDetails.put("pax_name", line.split(":")[1].trim());
            } else if (line.contains("VISA Fee")) {
                String[] descriptionParts = line.split("\\s+");
                invoiceDetails.put("country", descriptionParts.length > 0 ? descriptionParts[0] : null);
            } else if (line.contains("Corporate") || line.startsWith("IN")) {
                invoiceDetails.put("corporate", line.split(":")[1].trim().split("/")[0].trim());
            } else if (line.contains("Total") && line.trim().startsWith("Total")) {
                invoiceDetails.put("total_amount", line.split("\\s+")[line.split("\\s+").length - 1].trim());
            } else if (line.contains("IGST")) {
                invoiceDetails.put("igst", line.split("\\s+")[line.split("\\s+").length - 1].trim());
            } else if (line.contains("CGST")) {
                invoiceDetails.put("cgst", line.split("\\s+")[line.split("\\s+").length - 1].trim());
            } else if (line.contains("SGST")) {
                invoiceDetails.put("sgst", line.split("\\s+")[line.split("\\s+").length - 1].trim());
            } else if (line.contains("Net Amount")) {
                invoiceDetails.put("net_amount", line.split("\\s+")[line.split("\\s+").length - 1].trim());
            }
        }

        return invoiceDetails;
    }

    // Function to convert words to numbers
    private static int wordToNumber(String words) {
        // Implement a simple word to number conversion logic or use a library
        // For simplicity, this function is a placeholder
        return 0;
    }