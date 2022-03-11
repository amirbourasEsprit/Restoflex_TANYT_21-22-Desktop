/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Yosr Belaam
 */
public class PDF {
    public void GeneratePdf() throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\admin\\Desktop\\DemandeCongé.pdf"));
        document.open();
        document.add(new Paragraph("Demande de Conge"));
        String corps = "\n" + "Vous m’avez demandé, par lettre recommandée , à bénéficier d’un congé du Date_deb au Date_fin.\n"
                + "Par la présente, je vous informe que votre demande est en cours d'execution.\n"
                + " Votre demande est du Date_deb au Date_fin \n"
                + "Je vous prie d’agréer mes respectueuses salutations. \n" + "Cordialement";
        document.add(new Paragraph(corps));
        System.out.println("Demande faite");
        document.close();
    }
}
