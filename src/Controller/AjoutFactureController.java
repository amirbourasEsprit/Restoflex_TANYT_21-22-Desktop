/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.facture;
import entities.fournisseur;
import entities.restaurant;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.FactureService;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class AjoutFactureController implements Initializable {

    @FXML
    private Label numFacture;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblEtat;
    @FXML
    private Label lblnomFrs;
    @FXML
    private Label lblnomRest;
    
    protected static  facture p;
    private  FileChooser Fc= new FileChooser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        facture p = ListeFactureController.p;
        FactureService fs = new FactureService();
        facture fact = fs.rechercherFacture(p.getNum_facture());
        fournisseur four = fs.rechercherFournisseur(p.getNum_facture());
        restaurant rst = fs.rechercherRestaurant(p.getNum_facture());
        
        numFacture.setText(""+fact.getNum_facture());
        lblDate.setText(""+fact.getDate_facture());
        lblTotal.setText(""+fact.getTotal());
        lblEtat.setText(fact.getStatut());
        lblnomFrs.setText(four.getNom_fournisseur());
        lblnomRest.setText(rst.getNom());
        
    }    

    @FXML
    private void downloadPDF(ActionEvent event) {
        
        facture p = ListeFactureController.p;
        FactureService fs = new FactureService();
        fournisseur four = fs.rechercherFournisseur(p.getNum_facture());
        restaurant rst = fs.rechercherRestaurant(p.getNum_facture());
       
        Fc.setTitle("Download File");
        Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        Fc.setInitialDirectory(new File("C:\\Users\\Nesrine\\Desktop\\FacturePDF"));
       File selectedFile = Fc.showSaveDialog(new Stage());
        
        /////////////
        if (selectedFile != null){
            String filePath = selectedFile.getPath();
         
             Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();
           
            Image img = Image.getInstance("C:\\Users\\Nesrine\\Documents\\NetBeansProjects\\Pi_DEV_TANYT_21-22\\src\\image\\logoRed.png");
            img.scaleAbsoluteWidth(200);
            img.scaleAbsoluteHeight(180);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);
          
            doc.add(new Phrase("Facture n°:"+numFacture.getText(), FontFactory.getFont("Cambria", 22)));
            doc.add(new Paragraph("  "));
            
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            PdfPCell cell;
            
            cell = new PdfPCell(new Phrase("\nFournisseur :"+lblnomFrs.getText()+"\n\n"+four.getAdresse_fournisseur(), FontFactory.getFont("Cambria", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth(0);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\nFacturé à :\n\n" +lblnomRest.getText(), FontFactory.getFont("Cambria", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth(0);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\nEnvoyé à :\n\n" +rst.getAdresse(), FontFactory.getFont("Cambria", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth(0);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\nDate :      "+lblDate.getText()+"\n\nCommande n° :"+"\n\nDate livraison :\n\n", FontFactory.getFont("Cambria", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth(0);
            table.addCell(cell);
            
            doc.add(table);
            
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            
            PdfPTable table1 = new PdfPTable(1);
            table.setWidthPercentage(100);
            PdfPCell cell1;
            
            cell1 = new PdfPCell(new Phrase("TOTAL de la facture :                               " +lblTotal.getText()+"DT", FontFactory.getFont("Cambria", 16)));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorderWidthTop(2);
            cell1.setBorderWidthBottom(2);
            cell1.setBorderWidthLeft(0);
            cell1.setBorderWidthRight(0);
            cell1.setBorderColor(BaseColor.RED);
            cell1.setPaddingTop(16);
            cell1.setPaddingBottom(16);
            table1.addCell(cell1);
            
            doc.add(table1);
            
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            doc.add(new Phrase("Conditions et modalités de paiement", FontFactory.getFont("Cambria", 13)));
            doc.add(new Paragraph("Le paiement aura lieu à la livraison", FontFactory.getFont("Comic Sans MS", 10)));
       
            doc.close();
            Desktop.getDesktop().open(new File(filePath));
       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CmdFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(CmdFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CmdFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    }
    
   


}
