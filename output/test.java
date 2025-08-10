import java.util.*;
import java.util.regex.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class PDFDataExtractionTool {

    public static void main(String[] args) {
        // Initialize the application
        System.out.println("PDF Data Extraction Tool");

        // Simulate genre selection
        String genre = "visawaale"; // or "Jetsave"

        if (genre.equals("visawaale")) {
            // Title of the application
            System.out.println("Visawaale");

            // Simulate file upload
            List<File> uploadedFiles = Arrays.asList(new File("example1.pdf"), new File("example2.pdf"));

            if (!uploadedFiles.isEmpty()) {
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
                for (File uploadedFile : uploadedFiles) {
                    try {
                        // Read the uploaded file
                        PdfReader reader = new PdfReader(new FileInputStream(uploadedFile));
                        StringBuilder pdfText = new StringBuilder();
                        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                            pdfText.append(PdfTextExtractor.getTextFromPage(reader, i));
                        }
                        reader.close();

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
                                extractedData.put("Total", String.valueOf(WordUtils.toNumber(totalInWords.toLowerCase())));
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Data extracted and saved to extracted_data.csv");

            } else {
                System.out.println("Please upload PDF files to proceed.");
            }

        } else if (genre.equals("Jetsave")) {
            // Title of the application
            System.out.println("Jetsave");

            // Simulate file upload
            List<File> uploadedFiles = Arrays.asList(new File("example1.pdf"), new File("example2.pdf"));

            if (!uploadedFiles.isEmpty()) {
                List<Map<String, String>> allInvoiceDetails = new ArrayList<>();

                // Process each uploaded file
                for (File uploadedFile : uploadedFiles) {
                    try {
                        String pdfText = extractTextFromPdf(uploadedFile);
                        Map<String, String> invoiceDetails = parseTextToDataframe(pdfText);

                        // Store data from each file
                        allInvoiceDetails.add(invoiceDetails);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // Convert all results into a single CSV
                try (Writer writer = new FileWriter("invoice_details.csv");
                     CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(allInvoiceDetails.get(0).keySet().toArray(new String[0])))) {
                    for (Map<String, String> invoice : allInvoiceDetails) {
                        csvPrinter.printRecord(invoice.values());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("All Invoice Details saved to invoice_details.csv");
            }
        }
    }

    // Function to extract text from a PDF
    private static String extractTextFromPdf(File file) throws IOException {
        PdfReader reader = new PdfReader(new FileInputStream(file));
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
                invoiceDetails.put("invoice_number", line.split(":")[-1].trim().replace("DEL", ""));
            } else if (line.contains("Date :")) {
                invoiceDetails.put("date", line.split(":")[-1].trim());
            } else if (line.contains("Pax Name :")) {
                invoiceDetails.put("pax_name", line.split(":")[-1].trim());
            } else if (line.contains("VISA Fee")) {
                String[] descriptionParts = line.split(" ");
                invoiceDetails.put("country", descriptionParts.length > 0 ? descriptionParts[0] : null);
            } else if (line.contains("Corporate") || line.startsWith("IN")) {
                invoiceDetails.put("corporate", line.split(":")[-1].trim().split("/")[0].trim());
            } else if (line.contains("Total") && line.trim().startsWith("Total")) {
                invoiceDetails.put("total_amount", line.split(" ")[-1].trim());
            } else if (line.contains("IGST")) {
                invoiceDetails.put("igst", line.split(" ")[-1].trim());
            } else if (line.contains("CGST")) {
                invoiceDetails.put("cgst", line.split(" ")[-1].trim());
            } else if (line.contains("SGST")) {
                invoiceDetails.put("sgst", line.split(" ")[-1].trim());
            } else if (line.contains("Net Amount")) {
                invoiceDetails.put("net_amount", line.split(" ")[-1].trim());
            }
        }

        return invoiceDetails;
    }