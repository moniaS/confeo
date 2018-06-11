package com.example.confeo.pdf;

import com.example.confeo.model.Event;
import com.example.confeo.model.User;
import com.itextpdf.text.*;
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
            document.open();
            document.addTitle("Faktura nr " + invoiceNumber);
            // Add the header
            Paragraph paragraph = new Paragraph("Faktura nr " + invoiceNumber);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);;
            Paragraph paragraphBuyer = new Paragraph("SPRZEDAWCA:   " + user.getFirstname() + " " +  user.getLastname());
            paragraphBuyer.setAlignment(Element.ALIGN_LEFT);
            Paragraph paragraphSeller = new Paragraph("NABYWCA:   " + event.getOrganiser().getFirstname() + " " + event.getOrganiser().getLastname());
            paragraphSeller.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraphSeller);
            document.add(Chunk.NEWLINE);;
            document.add(paragraphBuyer);
            document.add(Chunk.NEWLINE);;
            document.add(new Paragraph("DATA WYSTAWIENIA:   " + LocalDate.now()));
            document.add(Chunk.NEWLINE);;
            document.add(new Paragraph("WYDARZENIE:\n" + event.getName() + "\n" + event.getStartDate() + " - " + event.getEndDate()));
            if(event.getAddress() != null && event.getAddress().getStreetNumber() != null && event.getAddress().getStreetName() != null && event.getAddress().getCityName() != null) {
                document.add(new Paragraph(event.getAddress().getCityName() + ", " + event.getAddress().getStreetName() + " " + event.getAddress().getStreetNumber()));
            }
            DecimalFormat df = new DecimalFormat("0.00##");
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("CENA:   " + df.format(event.getPricePerParticipant()) + " zł"));

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}