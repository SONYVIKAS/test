import java.util.*;
import java.util.regex.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.WordUtils;

public class PDFDataExtractionTool {

    public static void main(String[] args) {
        // Initialize the application
        System.out.println("PDF Data Extraction Tool");

        // Choose the genre
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose genre: 1. visawaale 2. Jetsave");
        int genreChoice = scanner.nextInt();

        if (genreChoice == 1) {
            // Visawaale processing
            System.out.println("Visawaale");

            // Simulate file upload
            List<File> uploadedFiles = simulateFileUpload();

            if (!uploadedFiles.isEmpty()) {
                List<Map<String, String>> dataList = new ArrayList<>();

                // Patterns to extract specific fields
                Map<String, String> patterns = new HashMap<>();
                patterns.put("Reference No.", "Reference No\\. & Date\\.\\s*([\\w-]+)");
                patterns.put("Other References", "Other References\\s*([\\w\\d]+)");
                patterns.put("Service Charge", "Service Charges?.*\\s([\\d,]+\\.\\d{2})");
                patterns.put("IGST Amount", "IGST @\\d+%.*?([\\d,]+\\.\\d{2})");
                patterns.put("IGST Rate", "IGST @(\\d+%)");
                patterns.put("Country Before Visa", "([A-Za-z]+)\\s*Visa\\s*Fee");

                for (File uploadedFile : uploadedFiles) {
                    String pdfText = extractTextFromPDF(uploadedFile);

                    Map<String, String> extractedData = new HashMap<>();
                    for (Map.Entry<String, String> entry : patterns.entrySet()) {
                        Matcher matcher = Pattern.compile(entry.getValue()).matcher(pdfText);
                        extractedData.put(entry.getKey(), matcher.find() ? matcher.group(1).trim() : null);
                    }

                    Matcher totalInWordsMatcher = Pattern.compile("INR\\s*(.*?)\\s*Only", Pattern.CASE_INSENSITIVE).matcher(pdfText);
                    if (totalInWordsMatcher.find()) {
                        String totalInWords = totalInWordsMatcher.group(1).trim();
                        try {
                            extractedData.put("Total", String.valueOf(wordToNumber(totalInWords.toLowerCase())));
                        } catch (Exception e) {
                            extractedData.put("Total", "Conversion Error");
                        }
                    }

                    dataList.add(extractedData);
                }

                // Convert dataList to CSV and simulate download
                String csv = convertToCSV(dataList);
                simulateDownload(csv, "extracted_data.csv");
            } else {
                System.out.println("Please upload PDF files to proceed.");
            }

        } else if (genreChoice == 2) {
            // Jetsave processing
            System.out.println("Jetsave");

            // Simulate file upload
            List<File> uploadedFiles = simulateFileUpload();

            if (!uploadedFiles.isEmpty()) {
                List<Map<String, String>> allInvoiceDetails = new ArrayList<>();

                for (File uploadedFile : uploadedFiles) {
                    String pdfText = extractTextFromPDF(uploadedFile);
                    Map<String, String> invoiceDetails = parseTextToDataFrame(pdfText);
                    allInvoiceDetails.add(invoiceDetails);
                }

                // Convert allInvoiceDetails to CSV and simulate download
                String csv = convertToCSV(allInvoiceDetails);
                simulateDownload(csv, "invoice_details.csv");
            }
        }
    }

    // Simulate file upload
    private static List<File> simulateFileUpload() {
        // This method should return a list of files to simulate file upload
        return new ArrayList<>();
    }

    // Extract text from PDF
    private static String extractTextFromPDF(File file) {
        StringBuilder text = new StringBuilder();
        try (PdfReader reader = new PdfReader(new FileInputStream(file))) {
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                text.append(PdfTextExtractor.getTextFromPage(reader, i)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    // Convert word to number
    private static int wordToNumber(String words) {
        // This method should convert words to numbers
        return NumberUtils.toInt(WordUtils.capitalizeFully(words));
    }

    // Parse text to structured data
    private static Map<String, String> parseTextToDataFrame(String text) {
        Map<String, String> invoiceDetails = new HashMap<>();
        String[] lines = text.split("\n");

        for (String line : lines) {
            if (line.contains("Invoice No")) {
                invoiceDetails.put("invoice_number", line.split(":")[1].trim().replace("DEL", ""));
            } else if (line.contains("Date :")) {
                invoiceDetails.put("date", line.split(":")[1].trim());
            } else if (line.contains("Pax Name :")) {
                invoiceDetails.put("pax_name", line.split(":")[1].trim());
            } else if (line.contains("VISA Fee")) {
                String[] descriptionParts = line.split();
                invoiceDetails.put("country", descriptionParts.length > 0 ? descriptionParts[0] : null);
            } else if (line.contains("Corporate") || line.startsWith("IN")) {
                invoiceDetails.put("corporate", line.split(":")[1].trim().split("/")[0].trim());
            } else if (line.startsWith("Total")) {
                invoiceDetails.put("total_amount", line.split()[line.split().length - 1].trim());
            } else if (line.contains("IGST")) {
                invoiceDetails.put("igst", line.split()[line.split().length - 1].trim());
            } else if (line.contains("CGST")) {
                invoiceDetails.put("cgst", line.split()[line.split().length - 1].trim());
            } else if (line.contains("SGST")) {
                invoiceDetails.put("sgst", line.split()[line.split().length - 1].trim());
            } else if (line.contains("Net Amount")) {
                invoiceDetails.put("net_amount", line.split()[line.split().length - 1].trim());
            }
        }

        return invoiceDetails;
    }

    // Convert data to CSV format
    private static String convertToCSV(List<Map<String, String>> dataList) {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(dataList.get(0).keySet().toArray(new String[0])))) {
            for (Map<String, String> data : dataList) {
                csvPrinter.printRecord(data.values());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    // Simulate download
    private static void simulateDownload(String data, String fileName) {
        // This method should simulate downloading the CSV file
        System.out.println("Downloading: " + fileName);
    }