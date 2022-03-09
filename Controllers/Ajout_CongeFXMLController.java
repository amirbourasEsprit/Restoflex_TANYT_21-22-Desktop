/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Conge;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.CongeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Yosr Belaam
 */
public class Ajout_CongeFXMLController implements Initializable {

    @FXML
    private TextField nom_cong;
    @FXML
    private Button valider_cong;
    @FXML
    private Button annuler_cong;
    @FXML
    private Button effacer_cong;
    @FXML
    private DatePicker date_deb_cong;
    @FXML
    private DatePicker date_fin_cong;
    @FXML
    private ComboBox<String> type_emp_cong;
    @FXML
    private TextField prenom_cong;
    @FXML
    private TextField id_emp_cong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type_emp_cong.getItems().add("Maladie");
        type_emp_cong.getItems().add("Sans solde");
    }    
        public void alert_Box(String title, String message) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setTitle(title);
         alert.setContentText(message);
         alert.show();
    }
    @FXML
    private void Ajout_Conge(ActionEvent event) throws SQLException, FileNotFoundException {
        // controle de saisie
       
        if (id_emp_cong.getText().isEmpty()) {
            alert_Box("Vérifiez Votre id", "Veuillez remplir le champ id");

        } else if (date_deb_cong.getEditor().getText().isEmpty()) {
            alert_Box("Vérifiez Votre Date début", "Veuillez remplir le champ Date début");

        } else if (date_fin_cong.getEditor().getText().isEmpty()) {
            alert_Box("Verifier Votre Date fin", "Veuillez remplir le champ Date fin");
        } else if (type_emp_cong.getItems().isEmpty()) {
            alert_Box("Verifier Votre Date fin", "Veuillez remplir le champ Date fin");
    }
    else 
         {    
        System.out.println("Yes");
    
    //ajout du conge
        CongeService cong =new CongeService();
        Conge c= new Conge();
        c.setId_utilisateur(Integer.parseInt(id_emp_cong.getText()));
        c.setDate_deb(Date.valueOf(date_deb_cong.getValue()));
        c.setDate_fin(Date.valueOf(date_fin_cong.getValue()));
        c.setEtat("en cours");
       // a modifier pour les types de congé
        if(type_emp_cong.getSelectionModel().getSelectedItem().equals("maladie"))
        { c.setId_type_conge(1);}
        else 
        {c.setId_type_conge(2);}
         Date d1 = c.getDate_deb();
         Date d2 = c.getDate_fin();
        int diff = diffdate(d1, d2);
        if(diff<c.user.getSolde_conge()){
        cong.ajouterConge(c);
        TrayNotification tray= new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Congé ajouté avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5)); 
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
         }
    }
     private int diffdate(Date d1, Date d2) {

        long diff = d2.getTime() - d1.getTime();
        int res = (int) (diff / (1000 * 60 * 60 * 24));
        System.out.println("Nombre de jours entre les deux dates est: " + res);
        return res;

    }
    
    @FXML
    private void effacertout(ActionEvent event) {
        id_emp_cong.clear();
        nom_cong.clear();
        date_deb_cong.getEditor().clear();
        date_fin_cong.getEditor().clear();
        type_emp_cong.setValue(null);
        prenom_cong.clear();
       
    }

    @FXML
    private void annuler_cong(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
