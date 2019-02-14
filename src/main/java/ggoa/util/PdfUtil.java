package ggoa.util;

import ggoa.model.Transaction;
import ggoa.model.Villa;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import be.quodlibet.boxable.utils.PDStreamUtils;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Locale;

public class PdfUtil {
    public static InputStream getPdf(Villa villa, Transaction txn) throws Exception {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);


        contentStream.setFont(font, 12);
       PDStreamUtils.write(contentStream, "welcome to party animals", font, 8.0f, 50, 725f, Color.BLACK);
       PDStreamUtils.write(contentStream, "Dear "+villa.getName()+" monthly maintainance fee for your "+villa.getNumber(), font, 8.0f, 50, 750f, Color.BLACK);
        PDStreamUtils.write(contentStream, "of amount "+txn.getBalance()+" received on "+txn.getTimeStamp()+"Thanks", font, 8.0f, 50, 775f, Color.BLACK);




// Make sure that the content stream is closed:
        contentStream.close();


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        document.close();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());


        return in;

    }
}
