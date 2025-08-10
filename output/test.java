import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.parser.*;
import com.itextpdf.kernel.pdf.canvas.parser.listener.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import com.streamlit.Streamlit;
import com.streamlit.components.file_uploader.FileUploader;
import com.streamlit.components.radio.Radio;
import com.streamlit.components.text.Text;
import com.streamlit.components.dataframe.DataFrame;
import com.streamlit.components.download_button.DownloadButton;

public class PDFDataExtractionTool {

    public static void main(String[] args) {
        Streamlit st = new Streamlit();

        // Title of the Streamlit App
        st.title("PDF Data Extraction Tool");

        // Radio button for genre selection
        Radio genre = st.radio("", Arrays.asList("visawaale", "Jetsave"), null);

        if ("visawaale".equals(genre.getValue())) {
            st.title("Visawaale");

            // File uploader for PDF files
            FileUploader uploadedFiles = st.file_uploader("Upload PDF files", "pdf", true);

            if (!uploadedFiles.getFiles().isEmpty()) {
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
                for (InputStream uploadedFile : uploadedFiles.getFiles()) {
                    String pdfText = extractTextFromPdf(uploadedFile);

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
                            extractedData.put("Total", String.valueOf(wordToNum(totalInWords.toLowerCase())));
                        } catch (Exception e) {
                            extractedData.put("Total", "Conversion Error");
                        }
                    }

                    // Append the extracted data to the list
                    dataList.add(extractedData);
                }

                // Create a DataFrame from the extracted data
                DataFrame df = new DataFrame(dataList);

                // Display the extracted data in Streamlit
                st.dataframe(df);

                // Provide an option to download the data as a CSV
                String csv = df.toCsv();
                st.download_button("Download Extracted Data as CSV", csv, "extracted_data.csv", "text/csv");
            } else {
                st.warning("Please upload PDF files to proceed.");
            }
        } else if ("Jetsave".equals(genre.getValue())) {
            st.title("Jetsave");

            // Multi-file uploader
            FileUploader uploadedFiles = st.file_uploader("Upload PDF files", "pdf", true, "jetsave_uploader");

            if (!uploadedFiles.getFiles().isEmpty()) {
                List<Map<String, String>> allInvoiceDetails = new ArrayList<>();

                // Process each uploaded file
                for (InputStream uploadedFile : uploadedFiles.getFiles()) {
                    String pdfText = extractTextFromPdf(uploadedFile);
                    Map<String, String> invoiceDetails = parseTextToDataframe(pdfText);

                    // Store data from each file
                    allInvoiceDetails.add(invoiceDetails);
                }

                // Convert all results into a single DataFrame
                DataFrame combinedInvoiceDetailsDf = new DataFrame(allInvoiceDetails);

                // Display combined results
                st.write("All Invoice Details in a Single Table:");
                st.dataframe(combinedInvoiceDetailsDf);

                // Option to save as CSV
                st.download_button("Download Invoice Details as CSV", combinedInvoiceDetailsDf.toCsv(), "invoice_details.csv", "text/csv");
            }
        }
    }

    // Function to extract text from a PDF
    private static String extractTextFromPdf(InputStream file) {
        StringBuilder text = new StringBuilder();
        try (PdfReader reader = new PdfReader(file)) {
            PdfDocument pdfDoc = new PdfDocument(reader);
            for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                text.append(PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i), new SimpleTextExtractionStrategy())).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    // Function to parse the extracted text into structured JSON
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
    private static int wordToNum(String words) {
        // Implement word to number conversion logic
        return 0; // Placeholder
    }