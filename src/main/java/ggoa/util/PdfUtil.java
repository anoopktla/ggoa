package ggoa.util;

import ggoa.model.Transaction;
import ggoa.model.Villa;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PdfUtil {
    public static InputStream getPdf(Villa villa, Transaction txn) throws Exception {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 700);
        contentStream.drawString("Hello World");
        contentStream.moveTextPositionByAmount(100, 750);
        contentStream.drawString("Dear "+villa.getName()+" monthly maintainance fee for your "+villa.getNumber());
        contentStream.moveTextPositionByAmount(100, 800);
        contentStream.drawString("of amount "+txn.getBalance()+" received on "+txn.getTimeStamp()+"Thanks");
        contentStream.endText();

// Make sure that the content stream is closed:
        contentStream.close();


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        document.close();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());


        return in;

    }
}
