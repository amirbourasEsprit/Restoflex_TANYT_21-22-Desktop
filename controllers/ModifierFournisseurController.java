/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.FournisseurService;
import entities.fournisseur;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abyrm
 */
public class ModifierFournisseurController implements Initializable {

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
    private TextField email_fournisseur;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button annulerbtn;

     int IDFournisseur  ;
 boolean update ;
 FournisseurService frs = new FournisseurService();
    @FXML
    private TextField logo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Update(ActionEvent event) {
               
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
                  else {
              f.setId_fournisseur(IDFournisseur);
          f.setNom_fournisseur(nom_fournisseur.getText());
       f.setMatricule_fiscale(matricule_fiscale.getText());
       f.setDomaine_fournisseur(domaine_fournisseur.getText());
       f.setNum_tel_fournisseur(num_tel_fournisseur.getText());
       f.setEmail_fournisseur(email_fournisseur.getText());
       f.setAdresse_fournisseur(adresse_fournisseur.getText());
          f.setId_fournisseur(IDFournisseur);
         f.setLogo(logo.getText());
  
          
          try{
    frs.modifierFournisseur(IDFournisseur, f);
    JOptionPane.showMessageDialog(null,"le fournisseur a été modifier avec succes");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Fournisseur.fxml"));
     Parent root = loader.load();
            nom_fournisseur.getScene().setRoot(root);
    }catch(Exception e)
    {JOptionPane.showMessageDialog(null, e);}
                  }
                  }
    

    @FXML
    private void Cancel(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Fournisseur.fxml"));
            Parent root = loader.load();
            nom_fournisseur.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    void setTextField(int id_fournisseur, String nomF, String matricule, String domaineF, String num_tel, String emailF, String adresseF , String log) {
        IDFournisseur=id_fournisseur  ;
         nom_fournisseur.setText(nomF);
         matricule_fiscale.setText(matricule);
         domaine_fournisseur.setText(domaineF);
         num_tel_fournisseur.setText(num_tel);
         email_fournisseur.setText(emailF);
         adresse_fournisseur.setText(adresseF);
         logo.setText(log);
         
    }
    
  void setUpdate(boolean b) {
        this.update = b;

    }
  
  
  
  public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
      return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }   
        }
        
    };
}    
        

public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}
    
}
