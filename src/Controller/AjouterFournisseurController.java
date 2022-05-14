/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.stock;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import entities.fournisseur;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import service.FournisseurService;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import java.util.regex.*; 
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.TransferMode;
import  javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.D;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author abyrm
 */
public class AjouterFournisseurController implements Initializable {
 private boolean verificationUserEmail=true;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField nom_fournisseur;
    @FXML
    private TextField matricule_fiscale;
    @FXML
    private TextField domaine_fournisseur;
    @FXML
    private TextField num_tel_fournisseur;
    @FXML
    private TextField adresse_fournisseur;
    @FXML
    private Button Annuler;
    @FXML
    private TextField email_fournisseur;
 FournisseurService fr = new FournisseurService();
    @FXML
    private Button Ajouter;
    @FXML
    private Button browse;
   // private ImageView image_v;
    private String Filename ;
    
    @FXML
    private Label labelEmail;
    @FXML
    private TextField logo;
   private  File selectedFile ;
    @FXML
    private Label labelEmail1;
    @FXML
    private ImageView Imagev;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      }    

    @FXML
    private void Ajouter(ActionEvent event) {
          fournisseur f = new fournisseur();
        if (nom_fournisseur.getText().isEmpty()||matricule_fiscale.getText().isEmpty()||domaine_fournisseur.getText().isEmpty()||num_tel_fournisseur.getText().isEmpty()||email_fournisseur.getText().isEmpty()||adresse_fournisseur.getText().isEmpty()||logo.getText().isEmpty())
        {Alert alert = new Alert (Alert.AlertType.WARNING);
          alert.setTitle("Alert");
          alert.setHeaderText(null);
          alert.setContentText("Veuillez remplir tous les champs ! ");
          alert.showAndWait();
        }
       else if
                (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email_fournisseur.getText())){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be created !!! ");
                   alert.setContentText("Email format is not correct !!! ");
                   alert.show(); 
               }
                  else if((!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", num_tel_fournisseur.getText()))){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be created !!! ");
                   alert.setContentText(" Phone must be formed with numbers only ( 8 numbers to be specific) !!! ");
                   alert.show(); 
               }
                  
    else 
        {
            
       f.setNom_fournisseur(nom_fournisseur.getText());
       f.setMatricule_fiscale(matricule_fiscale.getText());
       f.setDomaine_fournisseur(domaine_fournisseur.getText());
       f.setNum_tel_fournisseur(num_tel_fournisseur.getText());
       f.setEmail_fournisseur(email_fournisseur.getText());
       f.setAdresse_fournisseur(adresse_fournisseur.getText());
       f.setLogo(logo.getText());
    //   f.setLogo(Filename);
   
       try{
           fr.ajouterFournisseur(f);
   
    JOptionPane.showMessageDialog(null,"le fournisseur a été ajouté avec succes");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Dashboard_Admin_restoflex.fxml"));
     Parent root = loader.load();
     nom_fournisseur.getScene().setRoot(root);
    }catch(Exception e)
    {JOptionPane.showMessageDialog(null, e);}
       }

    }

    @FXML
    private void Cancel(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Dashboard_Admin_restoflex.fxml"));
            Parent root = loader.load();
            nom_fournisseur.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void browse(ActionEvent event) throws IOException {
        

    JFileChooser chooser=new JFileChooser();
    
    chooser.showOpenDialog(null);
    File F=chooser.getSelectedFile();
     Filename=F.getAbsolutePath();
    //image.setText(Filename);
    Image getAbsolutePeth=null;
    BufferedImage bf;
    bf = ImageIO.read(F);
    //ImageIcon icon = new ImageIcon (Filename);
 WritableImage image = SwingFXUtils.toFXImage(bf, null);
 Imagev.setImage(image);
    }

    
  
    @FXML
    private void verifEmail(InputMethodEvent event) throws SQLException {
              if (fr.chercherFournisseurByEmail(email_fournisseur.getText()) == true) {
            labelEmail.setText("Email Existe déja");
            verificationUserEmail = false;
        }
        if (fr.chercherFournisseurByEmail(email_fournisseur.getText()) == false) {

            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email_fournisseur.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelEmail.setVisible(false);
                labelEmail.setText("Email valide !");
                verificationUserEmail = true;

            } else {
                labelEmail.setVisible(true);
                labelEmail.setText("Email Format invalide !");
                verificationUserEmail = false;

            }
    }  
}
    
    

}
