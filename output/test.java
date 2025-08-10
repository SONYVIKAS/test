import java.util.*;
import java.util.regex.*;
import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;

// Main class
public class PDFDataExtractionTool {

    // Enum for genre selection
    enum Genre {
        VISAWAALE, JETSAVE
    }

    public static void main(String[] args) {
        // Initialize Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Display title
        System.out.println("PDF Data Extraction Tool");

        // Prompt user for genre selection
        System.out.println("Select Genre: 1. Visawaale 2. Jetsave");
        int genreChoice = scanner.nextInt();
        Genre genre = genreChoice == 1 ? Genre.VISAWAALE : Genre.JETSAVE;

        // Process based on genre
        if (genre == Genre.VISAWAALE) {
            processVisawaale();
        } else if (genre == Genre.JETSAVE) {
            processJetsave();
        }

        // Close scanner
        scanner.close();
    }

    // Method to process Visawaale genre
    private static void processVisawaale() {
        // Initialize patterns for data extraction
        Map<String, String> patterns = new HashMap<>();
        patterns.put("Reference No.", "Reference No\\. & Date\\.\\s*([\\w-]+)");
        patterns.put("Other References", "Other References\\s*([\\w\\d]+)");
        patterns.put("Service Charge", "Service Charges?.*\\s([\\d,]+\\.\\d{2})");
        patterns.put("IGST Amount", "IGST @\\d+%.*?([\\d,]+\\.\\d{2})");
        patterns.put("IGST Rate", "IGST @(\\d+%)");
        patterns.put("Country Before Visa", "([A-Za-z]+)\\s*Visa\\s*Fee");

        // Initialize list to store extracted data
        List<Map<String, String>> dataList = new ArrayList<>();

        // Prompt user for PDF file paths
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter PDF file paths (comma-separated):");
        String[] filePaths = scanner.nextLine().split(",");

        // Process each file
        for (String filePath : filePaths) {
            try {
                // Read PDF content
                PdfReader reader = new PdfReader(filePath.trim());
                StringBuilder pdfText = new StringBuilder();
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    pdfText.append(PdfTextExtractor.getTextFromPage(reader, i));
                }
                reader.close();

                // Extract data using patterns
                Map<String, String> extractedData = new HashMap<>();
                for (Map.Entry<String, String> entry : patterns.entrySet()) {
                    Matcher matcher = Pattern.compile(entry.getValue()).matcher(pdfText);
                    extractedData.put(entry.getKey(), matcher.find() ? matcher.group(1).trim() : null);
                }

                // Extract "Total (in words)" and convert to numeric value
                Matcher totalInWordsMatcher = Pattern.compile("INR\\s*(.*?)\\s*Only", Pattern.CASE_INSENSITIVE).matcher(pdfText);
                if (totalInWordsMatcher.find()) {
                    String totalInWords = totalInWordsMatcher.group(1).trim();
                    try {
                        extractedData.put("Total", String.valueOf(wordToNumber(totalInWords.toLowerCase())));
                    } catch (Exception e) {
                        extractedData.put("Total", "Conversion Error");
                    }
                }

                // Add extracted data to list
                dataList.add(extractedData);

            } catch (IOException e) {
                System.out.println("Error reading file: " + filePath);
            }
        }

        // Convert data list to CSV and display
        try {
            StringWriter csvOutput = new StringWriter();
            CSVPrinter csvPrinter = new CSVPrinter(csvOutput, CSVFormat.DEFAULT.withHeader(dataList.get(0).keySet().toArray(new String[0])));
            for (Map<String, String> data : dataList) {
                csvPrinter.printRecord(data.values());
            }
            csvPrinter.flush();
            System.out.println("Extracted Data:\n" + csvOutput.toString());
        } catch (IOException e) {
            System.out.println("Error generating CSV");
        }
    }

    // Method to process Jetsave genre
    private static void processJetsave() {
        // Prompt user for PDF file paths
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter PDF file paths (comma-separated):");
        String[] filePaths = scanner.nextLine().split(",");

        // Initialize list to store invoice details
        List<Map<String, String>> allInvoiceDetails = new ArrayList<>();

        // Process each file
        for (String filePath : filePaths) {
            try {
                // Extract text from PDF
                String pdfText = extractTextFromPdf(filePath.trim());

                // Parse text to structured data
                Map<String, String> invoiceDetails = parseTextToDataframe(pdfText);

                // Add invoice details to list
                allInvoiceDetails.add(invoiceDetails);

            } catch (IOException e) {
                System.out.println("Error reading file: " + filePath);
            }
        }

        // Convert invoice details to CSV and display
        try {
            StringWriter csvOutput = new StringWriter();
            CSVPrinter csvPrinter = new CSVPrinter(csvOutput, CSVFormat.DEFAULT.withHeader(allInvoiceDetails.get(0).keySet().toArray(new String[0])));
            for (Map<String, String> details : allInvoiceDetails) {
                csvPrinter.printRecord(details.values());
            }
            csvPrinter.flush();
            System.out.println("All Invoice Details:\n" + csvOutput.toString());
        } catch (IOException e) {
            System.out.println("Error generating CSV");
        }
    }

    // Method to extract text from PDF
    private static String extractTextFromPdf(String filePath) throws IOException {
        PdfReader reader = new PdfReader(filePath);
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            text.append(PdfTextExtractor.getTextFromPage(reader, i)).append("\n");
        }
        reader.close();
        return text.toString();
    }

    // Method to parse text to structured data
    private static Map<String, String> parseTextToDataframe(String text) {
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

        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            if (line.contains("Invoice No")) {
                invoiceDetails.put("invoice_number", line.split(":")[1].trim().replace("DEL", ""));
            } else if (line.contains("Date :")) {
                invoiceDetails.put("date", line.split(":")[1].trim());
            } else if (line.contains("Pax Name :")) {
                invoiceDetails.put("pax_name", line.split(":")[1].trim());
            } else if (line.contains("VISA Fee")) {
                String[] descriptionParts = line.split(" ");
                invoiceDetails.put("country", descriptionParts.length > 0 ? descriptionParts[0] : null);
            } else if (line.contains("Corporate") || line.startsWith("IN")) {
                invoiceDetails.put("corporate", line.split(":")[1].trim().split("/")[0].trim());
            } else if (line.contains("Total") && line.trim().startsWith("Total")) {
                invoiceDetails.put("total_amount", line.split(" ")[line.split(" ").length - 1].trim());
            } else if (line.contains("IGST")) {
                invoiceDetails.put("igst", line.split(" ")[line.split(" ").length - 1].trim());
            } else if (line.contains("CGST")) {
                invoiceDetails.put("cgst", line.split(" ")[line.split(" ").length - 1].trim());
            } else if (line.contains("SGST")) {
                invoiceDetails.put("sgst", line.split(" ")[line.split(" ").length - 1].trim());
            } else if (line.contains("Net Amount")) {
                invoiceDetails.put("net_amount", line.split(" ")[line.split(" ").length - 1].trim());
            }
        }
        return invoiceDetails;
    }

    // Helper method to convert words to numbers (simplified version)
    private static int wordToNumber(String words) {
        // This is a simplified version and may not cover all cases
        Map<String, Integer> wordToNumMap = new HashMap<>();
        wordToNumMap.put("zero", 0);
        wordToNumMap.put("one", 1);
        wordToNumMap.put("two", 2);
        wordToNumMap.put("three", 3);
        wordToNumMap.put("four", 4);
        wordToNumMap.put("five", 5);
        wordToNumMap.put("six", 6);
        wordToNumMap.put("seven", 7);
        wordToNumMap.put("eight", 8);
        wordToNumMap.put("nine", 9);
        wordToNumMap.put("ten", 10);
        // Add more mappings as needed

        int number = 0;
        String[] wordArray = words.split(" ");
        for (String word : wordArray) {
            if (wordToNumMap.containsKey(word)) {
                number += wordToNumMap.get(word);
            }
        }
        return number;
    }