/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.ServiceNotification;
import entities.reclamation;
import entities.type_reclamation;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.NotificationService;
import service.ReclamationService;
import service.type_reclamationService;
import sun.util.calendar.CalendarDate;
import sun.util.resources.zh.CalendarData_zh;
import util.JavaMail;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutREmpFXMLController implements Initializable {

    @FXML
    private Button IdBtnConf;
    @FXML
    private Button IdBtnAnn;
    @FXML
    private ChoiceBox<type_reclamation> IdTypeRec;
    @FXML
    private TextArea idDesc;
    @FXML
    private TextField idGerant;
    ReclamationService ps = new ReclamationService();
            NotificationService ns= new NotificationService();


    /**
     * Initializes the controller class.
     */
     
     int current_user=3;
     String UserName ="anis"; //User name of current user
    // email of destinataire
     int role =2; // role de current user
      int cureent_user_resto=2;
     
        String email = "tasnim.abidi@esprit.tn";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //choice box
        type_reclamationService trs= new type_reclamationService();
        List<type_reclamation> ltr = trs.afficherTypeReclamation(); //id+nom
         ObservableList list = FXCollections.observableArrayList(ltr); //traja3 ken nom observable
         
         IdTypeRec.setItems(list);
         
         //champ gérant
          utilisateur u=ps.chercherPrenomGerant(cureent_user_resto);  
         idGerant.setText(u.getNom());
             
    }    

    @FXML
    private void BtnConfirmer(ActionEvent event) throws SQLException, IOException{
        java.sql.Date current_Date= new java.sql.Date(Calendar.getInstance().getTime().getTime()); //instance:local,getTime:date bkol chy .. gettime :ayamet
      //  int current_user =13;
        System.out.println(IdTypeRec.getSelectionModel().getSelectedItem().getId_type_reclamation());
        reclamation r = new reclamation();
        r.setId_type_reclamation(IdTypeRec.getSelectionModel().getSelectedItem().getId_type_reclamation()); //ye5u nom type reclamation oyraja3 id
        r.setDescription(idDesc.getText());
        r.setDestinataire(idGerant.getText());
        r.setId_utilisateur(current_user);
        r.setDate_reclamation(current_Date);
        r.setStatut_reclamation("En cours");
        ps.ajouterReclamation(r); 
      JOptionPane.showMessageDialog(null, "Réclamation ajoutée");
        //new NotificationService().Notification("Succès", "Reclamation ajoutée!" );
        JavaMail mail = new JavaMail();
        mail.recipient = email;
        mail.UserName = UserName;
        mail.start();
//////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
    private void BtnAnnuler(ActionEvent event) throws Exception{
          try {
             IdBtnAnn.getScene().getWindow().hide();
                        Parent root= FXMLLoader.load(getClass().getResource("../gui/ReclamationFXML.fxml"));
                        Stage stage =new Stage();
                        Scene scene = new Scene(root);
                      //   scene.setFill(Color.TRANSPARENT);
                             stage.setScene(scene);
                           //  stage.initStyle(StageStyle.TRANSPARENT);
                             stage.show();
                             
        } catch (IOException ex) {
              JOptionPane.showMessageDialog(null, ex);
        }
         
    }
    
}
