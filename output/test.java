import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

// Main class
public class PDFDataExtractionTool {

    // Method to extract text from PDF using iText
    private static String extractTextFromPDF(InputStream inputStream) throws IOException {
        PdfReader reader = new PdfReader(inputStream);
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            text.append(PdfTextExtractor.getTextFromPage(reader, i)).append("\n");
        }
        reader.close();
        return text.toString();
    }

    // Method to parse text and extract data based on patterns
    private static Map<String, String> extractDataFromText(String text, Map<String, String> patterns) {
        Map<String, String> extractedData = new HashMap<>();
        for (Map.Entry<String, String> entry : patterns.entrySet()) {
            Pattern pattern = Pattern.compile(entry.getValue());
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                extractedData.put(entry.getKey(), matcher.group(1).trim());
            } else {
                extractedData.put(entry.getKey(), null);
            }
        }
        return extractedData;
    }

    // Method to convert words to numbers (simplified version)
    private static int wordToNum(String words) {
        // Implement a simple conversion or use a library if available
        // For simplicity, returning a dummy value
        return 0;
    }

    // Main method
    public static void main(String[] args) {
        // Define patterns for data extraction
        Map<String, String> patterns = new HashMap<>();
        patterns.put("Reference No.", "Reference No\\. & Date\\.\\s*([\\w-]+)");
        patterns.put("Other References", "Other References\\s*([\\w\\d]+)");
        patterns.put("Service Charge", "Service Charges?.*\\s([\\d,]+\\.\\d{2})");
        patterns.put("IGST Amount", "IGST @\\d+%.*?([\\d,]+\\.\\d{2})");
        patterns.put("IGST Rate", "IGST @(\\d+%)");
        patterns.put("Country Before Visa", "([A-Za-z]+)\\s*Visa\\s*Fee");

        // Simulate file upload (replace with actual file handling)
        List<InputStream> uploadedFiles = new ArrayList<>();
        // Add InputStreams to uploadedFiles

        if (!uploadedFiles.isEmpty()) {
            List<Map<String, String>> dataList = new ArrayList<>();

            for (InputStream uploadedFile : uploadedFiles) {
                try {
                    String pdfText = extractTextFromPDF(uploadedFile);
                    Map<String, String> extractedData = extractDataFromText(pdfText, patterns);

                    // Extract "Total (in words)" and convert to numeric value
                    Pattern totalPattern = Pattern.compile("INR\\s*(.*?)\\s*Only", Pattern.CASE_INSENSITIVE);
                    Matcher totalMatcher = totalPattern.matcher(pdfText);
                    if (totalMatcher.find()) {
                        String totalInWords = totalMatcher.group(1).trim();
                        try {
                            extractedData.put("Total", String.valueOf(wordToNum(totalInWords.toLowerCase())));
                        } catch (Exception e) {
                            extractedData.put("Total", "Conversion Error");
                        }
                    }

                    dataList.add(extractedData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Convert dataList to CSV and print (or save to file)
            try (Writer writer = new FileWriter("extracted_data.csv");
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(dataList.get(0).keySet().toArray(new String[0])))) {
                for (Map<String, String> data : dataList) {
                    csvPrinter.printRecord(data.values());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please upload PDF files to proceed.");
        }
    }