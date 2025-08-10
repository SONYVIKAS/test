import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.apache.commons.text.WordToNumberConverter;

public class PDFDataExtractionTool {

    public static void main(String[] args) {
        // Initialize the application
        System.out.println("PDF Data Extraction Tool");

        // Choose the genre
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the genre: 1. Visawaale 2. Jetsave");
        int genreChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (genreChoice == 1) {
            // Visawaale processing
            System.out.println("Visawaale");

            // Prompt user to enter PDF file paths
            System.out.println("Enter PDF file paths (comma-separated):");
            String[] filePaths = scanner.nextLine().split(",");

            // Initialize a list to store the extracted data
            List<Map<String, String>> dataList = new ArrayList<>();

            // Patterns to extract specific fields
            Map<String, String> patterns = new HashMap<>();
            patterns.put("Reference No.", "Reference No\\. & Date\\.\\s*([\\w-]+)");
            patterns.put("Other References", "Other References\\s*([\\w\\d]+)");
            patterns.put("Service Charge", "Service Charges?.*\\s([\\d,]+\\.\\d{2})");
            patterns.put("IGST Amount", "IGST @\\d+%.*?([\\d,]+\\.\\d{2})");
            patterns.put("IGST Rate", "IGST @(\\d+%)");
            patterns.put("Country Before Visa", "([A-Za-z]+)\\s*Visa\\s*Fee");

            // Loop through each uploaded PDF file
            for (String filePath : filePaths) {
                try {
                    // Read the uploaded file
                    PdfReader reader = new PdfReader(filePath.trim());
                    StringBuilder pdfText = new StringBuilder();
                    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                        pdfText.append(PdfTextExtractor.getTextFromPage(reader, i));
                    }

                    // Extract data for the current PDF
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
                            extractedData.put("Total", String.valueOf(WordToNumberConverter.convert(totalInWords.toLowerCase())));
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
            try (Writer writer = new FileWriter("extracted_data.csv");
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(dataList.get(0).keySet().toArray(new String[0])))) {
                for (Map<String, String> data : dataList) {
                    csvPrinter.printRecord(data.values());
                }
                System.out.println("Data extracted and saved to extracted_data.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (genreChoice == 2) {
            // Jetsave processing
            System.out.println("Jetsave");

            // Prompt user to enter PDF file paths
            System.out.println("Enter PDF file paths (comma-separated):");
            String[] filePaths = scanner.nextLine().split(",");

            List<Map<String, String>> allInvoiceDetails = new ArrayList<>();

            // Process each uploaded file
            for (String filePath : filePaths) {
                try {
                    String pdfText = extractTextFromPdf(filePath.trim());
                    Map<String, String> invoiceDetails = parseTextToDataframe(pdfText);
                    allInvoiceDetails.add(invoiceDetails);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Create a CSV from the combined invoice details
            try (Writer writer = new FileWriter("invoice_details.csv");
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(allInvoiceDetails.get(0).keySet().toArray(new String[0])))) {
                for (Map<String, String> invoice : allInvoiceDetails) {
                    csvPrinter.printRecord(invoice.values());
                }
                System.out.println("Invoice details extracted and saved to invoice_details.csv");
            } catch (IOException e) {
                e.printStackTrace();
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
        return text.toString();
    }

    // Function to parse the extracted text into structured data
    private static Map<String, String> parseTextToDataframe(String text) {
        String[] lines = text.split("\n");
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