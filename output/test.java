import java.util.*;
import java.util.regex.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

public class PDFDataExtractionTool {

    public static void main(String[] args) {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Display title
        System.out.println("PDF Data Extraction Tool");

        // Ask user to select genre
        System.out.println("Select genre: 1. visawaale 2. Jetsave");
        int genreChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (genreChoice == 1) {
            // Visawaale
            System.out.println("Visawaale");

            // Ask user to input PDF file paths
            System.out.println("Enter paths of PDF files separated by commas:");
            String[] filePaths = scanner.nextLine().split(",");

            // Initialize list to store extracted data
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

                    // Extract text from each page
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
                            extractedData.put("Total", String.valueOf(NumberUtils.toInt(totalInWords.toLowerCase())));
                        } catch (NumberFormatException e) {
                            extractedData.put("Total", "Conversion Error");
                        }
                    }

                    // Append the extracted data to the list
                    dataList.add(extractedData);

                } catch (IOException e) {
                    System.out.println("Error reading PDF file: " + filePath);
                }
            }

            // Create CSV from extracted data
            try (Writer writer = new FileWriter("extracted_data.csv");
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(dataList.get(0).keySet().toArray(new String[0])))) {
                for (Map<String, String> data : dataList) {
                    csvPrinter.printRecord(data.values());
                }
                System.out.println("Extracted data saved to extracted_data.csv");
            } catch (IOException e) {
                System.out.println("Error writing CSV file.");
            }

        } else if (genreChoice == 2) {
            // Jetsave
            System.out.println("Jetsave");

            // Ask user to input PDF file paths
            System.out.println("Enter paths of PDF files separated by commas:");
            String[] filePaths = scanner.nextLine().split(",");

            // Initialize list to store invoice details
            List<Map<String, String>> allInvoiceDetails = new ArrayList<>();

            // Process each PDF file
            for (String filePath : filePaths) {
                try {
                    String pdfText = extractTextFromPdf(filePath.trim());
                    Map<String, String> invoiceDetails = parseTextToDataframe(pdfText);
                    allInvoiceDetails.add(invoiceDetails);
                } catch (IOException e) {
                    System.out.println("Error reading PDF file: " + filePath);
                }
            }

            // Create CSV from invoice details
            try (Writer writer = new FileWriter("invoice_details.csv");
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(allInvoiceDetails.get(0).keySet().toArray(new String[0])))) {
                for (Map<String, String> invoice : allInvoiceDetails) {
                    csvPrinter.printRecord(invoice.values());
                }
                System.out.println("Invoice details saved to invoice_details.csv");
            } catch (IOException e) {
                System.out.println("Error writing CSV file.");
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

        String[] lines = text.split("\n");
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