import java.io.*;
import java.util.*;
import java.util.regex.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import com.opencsv.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main class for PDF Data Extraction Tool
public class PDFDataExtractionTool extends JFrame {
    private JComboBox<String> genreComboBox;
    private JTextArea textArea;
    private JButton uploadButton, downloadButton;
    private List<Map<String, String>> dataList;

    public PDFDataExtractionTool() {
        setTitle("PDF Data Extraction Tool");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        genreComboBox = new JComboBox<>(new String[]{"visawaale", "Jetsave"});
        textArea = new JTextArea();
        uploadButton = new JButton("Upload PDF files");
        downloadButton = new JButton("Download Extracted Data as CSV");
        dataList = new ArrayList<>();

        // Add components to the frame
        add(genreComboBox, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(uploadButton);
        buttonPanel.add(downloadButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        uploadButton.addActionListener(e -> uploadFiles());
        downloadButton.addActionListener(e -> downloadCSV());

        setVisible(true);
    }

    // Method to upload and process PDF files
    private void uploadFiles() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            String selectedGenre = (String) genreComboBox.getSelectedItem();
            if ("visawaale".equals(selectedGenre)) {
                processVisawaale(files);
            } else if ("Jetsave".equals(selectedGenre)) {
                processJetsave(files);
            }
        }
    }

    // Method to process "visawaale" PDFs
    private void processVisawaale(File[] files) {
        dataList.clear();
        Pattern[] patterns = {
            Pattern.compile("Reference No\\. & Date\\.\\s*([\\w-]+)"),
            Pattern.compile("Other References\\s*([\\w\\d]+)"),
            Pattern.compile("Service Charges?.*\\s([\\d,]+\\.\\d{2})"),
            Pattern.compile("IGST @\\d+%.*?([\\d,]+\\.\\d{2})"),
            Pattern.compile("IGST @(\\d+%)"),
            Pattern.compile("([A-Za-z]+)\\s*Visa\\s*Fee")
        };

        for (File file : files) {
            try (PDDocument document = PDDocument.load(file)) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String pdfText = pdfStripper.getText(document);
                Map<String, String> extractedData = new HashMap<>();
                for (Pattern pattern : patterns) {
                    Matcher matcher = pattern.matcher(pdfText);
                    if (matcher.find()) {
                        extractedData.put(pattern.pattern(), matcher.group(1).trim());
                    }
                }
                dataList.add(extractedData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        displayData();
    }

    // Method to process "Jetsave" PDFs
    private void processJetsave(File[] files) {
        dataList.clear();
        for (File file : files) {
            try (PDDocument document = PDDocument.load(file)) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String pdfText = pdfStripper.getText(document);
                Map<String, String> invoiceDetails = parseTextToMap(pdfText);
                dataList.add(invoiceDetails);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        displayData();
    }

    // Method to parse text into a structured map for "Jetsave"
    private Map<String, String> parseTextToMap(String text) {
        Map<String, String> invoiceDetails = new HashMap<>();
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            if (line.contains("Invoice No")) {
                invoiceDetails.put("invoice_number", line.split(":")[1].trim().replace("DEL", ""));
            } else if (line.contains("Date :")) {
                invoiceDetails.put("date", line.split(":")[1].trim());
            } else if (line.contains("Pax Name :")) {
                invoiceDetails.put("pax_name", line.split(":")[1].trim());
            } else if (line.contains("VISA Fee")) {
                String[] parts = line.split(" ");
                invoiceDetails.put("country", parts[0]);
            } else if (line.contains("Corporate") || line.startsWith("IN")) {
                invoiceDetails.put("corporate", line.split(":")[1].trim().split("/")[0].trim());
            } else if (line.startsWith("Total")) {
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
    private void displayData() {
        StringBuilder sb = new StringBuilder();
        for (Map<String, String> data : dataList) {
            sb.append(data.toString()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    // Method to download extracted data as CSV
    private void downloadCSV() {
        try {
            FileWriter writer = new FileWriter("extracted_data.csv");
            CSVWriter csvWriter = new CSVWriter(writer);
            if (!dataList.isEmpty()) {
                // Write header
                csvWriter.writeNext(dataList.get(0).keySet().toArray(new String[0]));
                // Write data
                for (Map<String, String> data : dataList) {
                    csvWriter.writeNext(data.values().toArray(new String[0]));
                }
            }
            csvWriter.close();
            JOptionPane.showMessageDialog(this, "CSV file saved as extracted_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PDFDataExtractionTool::new);
    }