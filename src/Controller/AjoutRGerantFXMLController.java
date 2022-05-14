/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.reclamation;
import entities.restaurant;
import entities.type_reclamation;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Service.ServiceNotification;
import service.ReclamationService;
import service.type_reclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutRGerantFXMLController implements Initializable {

    @FXML
    private ChoiceBox<type_reclamation> IdTypeRec;
    @FXML
    private ChoiceBox<utilisateur> IdDesti;
    @FXML
    private TextArea IdDesc;
    @FXML
    private Button IdBtnConf;
    @FXML
    private Button IdBtnAnn;
    @FXML
    private RadioButton IdEmploye;
    @FXML
    private RadioButton IdFourn;
  //  ReclamationService ps = new ReclamationService();
        utilisateur util=utilisateur.current_user;
      int current_user = util.getId_utilisateur();
        ReclamationService rs = new ReclamationService();
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_reclamationService trs= new type_reclamationService();
        List<type_reclamation> ltr = trs.afficherTypeReclamation(); //id+nom
        ObservableList list = FXCollections.observableArrayList(ltr); //traja3 ken nom observable
        IdTypeRec.setItems(list);
      
           
   
    }   
  

    @FXML
    private void BtnConfirmer(ActionEvent event) throws SQLException, IOException {
         java.sql.Date current_Date= new java.sql.Date(Calendar.getInstance().getTime().getTime()); //instance:local,getTime:date bkol chy .. gettime :ayamet
   
        reclamation r = new reclamation();
      r.setId_type_reclamation(IdTypeRec.getSelectionModel().getSelectedItem().getId_type_reclamation()); //ye5u nom type reclamation oyraja3 id
        r.setDescription(IdDesc.getText());
        r.setDestinataire(IdDesti.getSelectionModel().getSelectedItem().getPrenom());
        r.setId_utilisateur(current_user);
        r.setDate_reclamation(current_Date);
        r.setStatut_reclamation("En cours");
        
     rs.ajouterReclamation(r); 
      new ServiceNotification().Notification("Sucées", "Reclamation Ajoutée" );
      JOptionPane.showMessageDialog(null, "Reclamation Ajoutée");
      IdBtnConf.getScene().getWindow().hide();
                        Parent root= FXMLLoader.load(getClass().getResource("../gui/ReclamationFXML.fxml"));
                        Stage stage =new Stage();
                        Scene scene = new Scene(root);
                      //   scene.setFill(Color.TRANSPARENT);
                             stage.setScene(scene);
                           //  stage.initStyle(StageStyle.TRANSPARENT);
                             stage.show();
    }
    

    @FXML
    private void BtnAnnuler(ActionEvent event) throws IOException {
          try { 
                FXMLLoader loader =new FXMLLoader(getClass().getResource("../gui/ReclamationFXML.fxml"));
                Parent root = loader.load();
                pane.getChildren().add(root);
            
        } catch (IOException ex) {
              JOptionPane.showMessageDialog(null, ex);
        }
          
    }

    @FXML
    private void RBEmploye(ActionEvent event) {
        
  ReclamationService rs = new ReclamationService();
        if(IdEmploye.isSelected()){
            IdFourn.setSelected(false);
        //employe    
        List <utilisateur> ue = rs.chercherNomEmploye(util.getId_rest());
        ObservableList lue = FXCollections.observableArrayList(ue);
        IdDesti.setItems(lue);
          
        }
    }

    @FXML
    private void RBFournisseur(ActionEvent event) {
         ReclamationService rs = new ReclamationService();
          if(IdFourn.isSelected()){
          IdEmploye.setSelected(false);        
         //fournisseur
        List <utilisateur> uf = rs.chercherNomFournisseur(util.getId_rest());
        ObservableList luf = FXCollections.observableArrayList(uf);
        IdDesti.setItems(luf);
        }
    }
    
    
}


