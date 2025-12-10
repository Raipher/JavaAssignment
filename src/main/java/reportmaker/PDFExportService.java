/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportmaker;

/**
 *
 * @author WIN11PC
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class PDFExportService {

    public void createPdf(String filename, String content) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            
            // Add Title
            Font titleFont = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
            Paragraph title = new Paragraph("Official Academic Report", titleFont);
            title.setSpacingAfter(20);
            document.add(title);

            // Add Content (We use Courier to keep the table alignment from your String)
            Font contentFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
            Paragraph body = new Paragraph(content, contentFont);
            document.add(body);

            document.close();
            System.out.println("[System] PDF saved successfully to: " + filename);
            
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("[Error] Could not create PDF: " + e.getMessage());
        }
    }
}