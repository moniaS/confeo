package com.example.confeo.pdf;

import com.example.confeo.model.Event;
import com.example.confeo.model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfGenerator {

    public static ByteArrayInputStream generateInvoice(Event event, User user, String invoiceNumber) throws IOException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.CP1250, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);
            document.open();
            document.addTitle("Faktura nr " + invoiceNumber);
            Paragraph paragraph = new Paragraph("Faktura nr " + invoiceNumber, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);;
            Paragraph paragraphBuyer = new Paragraph("SPRZEDAWCA:   " + user.getFirstname() + " " +  user.getLastname(), font);
            paragraphBuyer.setAlignment(Element.ALIGN_LEFT);
            Paragraph paragraphSeller = new Paragraph("NABYWCA:   " + event.getOrganiser().getFirstname() + " " + event.getOrganiser().getLastname(), font);
            paragraphSeller.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraphSeller);
            document.add(Chunk.NEWLINE);;
            document.add(paragraphBuyer);
            document.add(Chunk.NEWLINE);;
            document.add(new Paragraph("DATA WYSTAWIENIA:   " + LocalDate.now(), font));
            document.add(Chunk.NEWLINE);;
            document.add(new Paragraph("WYDARZENIE:\n" + event.getName() + "\n" + event.getStartDate() + " - " + event.getEndDate(), font));
            if(event.getAddress() != null && event.getAddress().getStreetNumber() != null && event.getAddress().getStreetName() != null && event.getAddress().getCityName() != null) {
                document.add(new Paragraph(event.getAddress().getCityName() + ", " + event.getAddress().getStreetName() + " " + event.getAddress().getStreetNumber(), font));
            }
            DecimalFormat df = new DecimalFormat("0.00##");
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("CENA:   " + df.format(event.getPricePerParticipant()) + " zł", font));

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}