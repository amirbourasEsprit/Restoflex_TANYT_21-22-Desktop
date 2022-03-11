/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.type_conge;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.TypeCongeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Yosr Belaam
 */
public class Ajout_Type_CongFXMLController implements Initializable {

    @FXML
    private Label nomL;
    @FXML
    private Button effacer;
    @FXML
    private Button valider;
    @FXML
    private Button annuler;
    @FXML
    private TextField nom_Tcong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void effacer(ActionEvent event) {
        nom_Tcong.clear();
    }
    public void alert_Box(String title, String message) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setTitle(title);
         alert.setContentText(message);
         alert.show();
    }
    @FXML
    private void valider(ActionEvent event) throws SQLException {
        // controle de saisie
        if (nom_Tcong.getText().isEmpty()) {
            alert_Box("Vérifiez Votre id", "Veuillez remplir le champ id");

        } else {System.out.println("Yes");
    //ajout du type
        TypeCongeService Tcong =new TypeCongeService();
        type_conge tc= new type_conge();
        tc.setNom_type_conge(nom_Tcong.getText());
        Tcong.ajouterTypeConge(tc);
       TrayNotification tray= new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Congé supprimé avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
              //((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    }
    
    @FXML
    private void annuler(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}

  
