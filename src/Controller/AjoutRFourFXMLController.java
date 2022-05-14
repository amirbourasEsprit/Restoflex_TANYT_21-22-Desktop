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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import Service.ServiceNotification;
import javafx.scene.layout.AnchorPane;
import service.ReclamationService;
import service.RestaurantService;
import service.UtilisateurService;
import service.type_reclamationService;
import util.JavaMail;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutRFourFXMLController implements Initializable {

    @FXML
    private Button IdBtnConf;
    @FXML
    private Button IdBtnAnn;
    @FXML
    private ChoiceBox<type_reclamation> IdTypeRec;
    @FXML
    private ChoiceBox<restaurant> IdRestaurant;
    @FXML
    private TextArea IdDescri;
    @FXML
    private TextField IdDesti;
     ReclamationService ps = new ReclamationService();
     utilisateur util =utilisateur.current_user;
    /**
     * Initializes the controller class.
     */
     
      int current_user = util.getId_utilisateur();
     String UserName =util.getPrenom();
    UtilisateurService us=new UtilisateurService();
    String email =us.mailGerant(util.getId_rest()) ;
     int role =util.getId_role();
    @FXML
    private AnchorPane pane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //choice box
        type_reclamationService trs= new type_reclamationService();
        List<type_reclamation> ltr = trs.afficherTypeReclamation(); //id+nom
         ObservableList list = FXCollections.observableArrayList(ltr); //traja3 ken nom observable
         IdTypeRec.setItems(list);
         
         ReclamationService rs = new ReclamationService();
         List<restaurant> nomRest = rs.chercherRestaurant(util.getId_fournisseur(),util.getCin());
        ObservableList lr = FXCollections.observableArrayList(nomRest);
        IdRestaurant.setItems(lr);
        IdRestaurant.setOnAction(this::getPrenomGerant);
        
         
    }   
    
     private void getPrenomGerant(ActionEvent event) {
        
        ReclamationService rs = new ReclamationService();
         restaurant r= IdRestaurant.getSelectionModel().getSelectedItem();
           utilisateur u = rs.chercherPrenomGerant(r.getId_rest());
       // System.out.println(r.toString());
         IdDesti.setText(u.getNom());
       
     }

    @FXML
    private void BtnConfirmer(ActionEvent event) throws SQLException, IOException {
     
         java.sql.Date current_Date= new java.sql.Date(Calendar.getInstance().getTime().getTime()); //instance:local,getTime:date bkol chy .. gettime :ayamet
        reclamation r = new reclamation();
      r.setId_type_reclamation(IdTypeRec.getSelectionModel().getSelectedItem().getId_type_reclamation()); //ye5u nom type reclamation oyraja3 id
        r.setDescription(IdDescri.getText());
        r.setDestinataire(IdDesti.getText());
        r.setId_utilisateur(current_user);
        r.setDate_reclamation(current_Date);
        r.setStatut_reclamation("En cours");
     ps.ajouterReclamation(r); 
     new ServiceNotification().Notification("Sucées", "Reclamation Ajoutée" );
     JOptionPane.showMessageDialog(null, "Reclamation Ajoutée");
     JavaMail mail = new JavaMail();
        mail.recipient = email;
        mail.UserName = UserName;
        mail.start();
     
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../gui/ReclamationFXML.fxml"));
                       Parent root = loader.load();
                         pane.getChildren().add(root);
    }
    
    
    

    @FXML
    private void BtnAnnuler(ActionEvent event) throws IOException {
        IdTypeRec.setValue(null);
        IdRestaurant.setValue(null);
        IdDescri.setText(null);
        IdDesti.setText(null);
        
    }
    
}
