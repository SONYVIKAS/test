import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import org.apache.commons.csv.*;
import javax.swing.*;
import java.awt.event.*;

public class PDFDataExtractionTool {

    public static void main(String[] args) {
        // Create a JFrame for the GUI
        JFrame frame = new JFrame("PDF Data Extraction Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create radio buttons for genre selection
        JRadioButton visawaaleButton = new JRadioButton("visawaale");
        JRadioButton jetsaveButton = new JRadioButton("Jetsave");
        ButtonGroup group = new ButtonGroup();
        group.add(visawaaleButton);
        group.add(jetsaveButton);

        // Create a file chooser for PDF file selection
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        // Create a button to trigger file upload
        JButton uploadButton = new JButton("Upload PDF files");

        // Create a text area to display extracted data
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Add components to the frame
        frame.getContentPane().add(visawaaleButton, "North");
        frame.getContentPane().add(jetsaveButton, "Center");
        frame.getContentPane().add(uploadButton, "South");
        frame.getContentPane().add(new JScrollPane(textArea), "East");

        // Action listener for the upload button
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File[] files = fileChooser.getSelectedFiles();
                    if (visawaaleButton.isSelected()) {
                        // Extract data for "visawaale"
                        List<Map<String, String>> dataList = new ArrayList<>();
                        Pattern[] patterns = {
                            Pattern.compile("Reference No\\. & Date\\.\\s*([\\w-]+)"),
                            Pattern.compile("Other References\\s*([\\w\\d]+)"),
                            Pattern.compile("Service Charges?.*\\s([\\d,]+\\.\\d{2})"),
                            Pattern.compile("IGST @\\d+%.*?([\\d,]+\\.\\d{2})"),
                            Pattern.compile("IGST @(\\d+%)"),
                            Pattern.compile("([A-Za-z]+)\\s*Visa\\s*Fee")
                        };
                        for (File file : files) {
                            try {
                                PdfReader reader = new PdfReader(new FileInputStream(file));
                                StringBuilder pdfText = new StringBuilder();
                                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                                    pdfText.append(PdfTextExtractor.getTextFromPage(reader, i));
                                }
                                reader.close();
                                Map<String, String> extractedData = new HashMap<>();
                                for (Pattern pattern : patterns) {
                                    Matcher matcher = pattern.matcher(pdfText);
                                    if (matcher.find()) {
                                        extractedData.put(pattern.pattern(), matcher.group(1).trim());
                                    }
                                }
                                Matcher totalInWordsMatcher = Pattern.compile("INR\\s*(.*?)\\s*Only", Pattern.CASE_INSENSITIVE).matcher(pdfText);
                                if (totalInWordsMatcher.find()) {
                                    String totalInWords = totalInWordsMatcher.group(1).trim();
                                    try {
                                        extractedData.put("Total", String.valueOf(wordToNum(totalInWords.toLowerCase())));
                                    } catch (Exception ex) {
                                        extractedData.put("Total", "Conversion Error");
                                    }
                                }
                                dataList.add(extractedData);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        displayData(textArea, dataList);
                    } else if (jetsaveButton.isSelected()) {
                        // Extract data for "Jetsave"
                        List<Map<String, String>> allInvoiceDetails = new ArrayList<>();
                        for (File file : files) {
                            try {
                                String pdfText = extractTextFromPdf(file);
                                Map<String, String> invoiceDetails = parseTextToDataframe(pdfText);
                                allInvoiceDetails.add(invoiceDetails);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        displayData(textArea, allInvoiceDetails);
                    }
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }

    // Method to extract text from a PDF file
    private static String extractTextFromPdf(File file) throws IOException {
        PdfReader reader = new PdfReader(new FileInputStream(file));
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            text.append(PdfTextExtractor.getTextFromPage(reader, i)).append("\n");
        }
        reader.close();
        return text.toString();
    }

    // Method to parse extracted text into structured data
    private static Map<String, String> parseTextToDataframe(String text) {
        String[] lines = text.split("\n");
        Map<String, String> invoiceDetails = new HashMap<>();
        for (String line : lines) {
            if (line.contains("Invoice No")) {
                invoiceDetails.put("invoice_number", line.split(":")[1].trim().replace("DEL", ""));
            } else if (line.contains("Date :")) {
                invoiceDetails.put("date", line.split(":")[1].trim());
            } else if (line.contains("Pax Name :")) {
                invoiceDetails.put("pax_name", line.split(":")[1].trim());
            } else if (line.contains("VISA Fee")) {
                String[] descriptionParts = line.split(" ");
                invoiceDetails.put("country", descriptionParts[0]);
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

    // Method to display extracted data in the text area
    private static void displayData(JTextArea textArea, List<Map<String, String>> dataList) {
        StringBuilder displayText = new StringBuilder();
        for (Map<String, String> data : dataList) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                displayText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            displayText.append("\n");
        }
        textArea.setText(displayText.toString());
    }

    // Dummy method to convert words to numbers (replace with actual implementation)
    private static int wordToNum(String words) {
        // Implement word to number conversion logic
        return 0;
    }