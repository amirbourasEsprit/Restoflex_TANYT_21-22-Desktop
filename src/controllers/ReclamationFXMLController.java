/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.ReclamationRecuController.r;
import entities.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.NotificationService;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ReclamationFXMLController implements Initializable {

    @FXML
    private Button IdBtnAjouter;
    @FXML
    private Button IdBtnSupp;
    ReclamationService ps = new ReclamationService();
    @FXML
    private TableView<reclamation> tableRec;
    @FXML
    private TableColumn<reclamation, String> ColDest;
    @FXML
    private TableColumn<reclamation, String> ColStat;
    @FXML
    private TableColumn<reclamation, Date> ColDate;

    ObservableList<reclamation> listR;
    @FXML
    private DatePicker recherche;
    @FXML
    private Button IdDetails;

    static reclamation r;

    /**
     * Initializes the controller class.
     */
    int current_user = 8;
    String UserName = "Salma";
    int role = 3;
    @FXML
    private Button IdDetails1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ReclamationService rs = new ReclamationService();

        listR = FXCollections.observableArrayList();
        List<reclamation> Al = rs.afficherReclamationParId(current_user);

        Al.stream().forEach((j) -> {
            listR.add(j);
        });

        ColDest.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
        ColStat.setCellValueFactory(new PropertyValueFactory<>("statut_reclamation"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));

        tableRec.setItems(listR);

    }

    @FXML
    private void BtnAjouterRec(ActionEvent event) throws Exception {

        switch (role) {
            case 1:
                try {
                    IdBtnAjouter.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("../gui/AjoutRGerantFXML.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }   break;
            case 2:
                try {
                    IdBtnAjouter.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("../gui/AjoutREmpFXML.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }   break;
            case 3:
                try {
                    IdBtnAjouter.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("../gui/AjoutRFourFXML.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }   break;
            default:
                break;
        }

    }

    @FXML
    private void BtnSuppRec(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        reclamation rc = tableRec.getSelectionModel().getSelectedItem();

        reclamation rec = rs.chercherReclamation(rc.getNum_reclamation());

        try {
            //  rs.supprimerReclamation(r.getNum_reclamation());
            boolean check = Suppression_Box("Vérification!", "Vous êtes sur?");
            if (check) {
                rs.supprimerReclamation(rec.getNum_reclamation());
            }
            // visibility(false);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
          JOptionPane.showMessageDialog(null, "Réclamation Supprimée");
        new NotificationService().Notification("Sucées", "Reclamation supprimée" );
        ActualiserTable();

    }

    private void ActualiserTable() {

        listR.clear();
        ReclamationService rs = new ReclamationService();

        ObservableList<reclamation> listR = FXCollections.observableArrayList();
        List<reclamation> Al = rs.afficherReclamationParId(current_user);

        Al.stream().forEach((j) -> {
            listR.add(j);
        });

        ColDest.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
        ColStat.setCellValueFactory(new PropertyValueFactory<>("statut_reclamation"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));

        tableRec.setItems(listR);
    }

    private boolean Suppression_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;
    }

    @FXML
    private void BtnDetails(ActionEvent event) {
        try {
            r = tableRec.getSelectionModel().getSelectedItem();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../gui/DetailsRecFXML.fxml"));
            
            Parent parent = (Parent) loader1.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            
            stage.show();
            DetailsRecFXMLController details = loader1.getController();
            details.setReservation(r);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void recherche(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        String recherche = this.recherche.getValue().toString();

        Date DPCurrentDate1 = Date.valueOf(recherche);

        ObservableList<reclamation> data = FXCollections.observableArrayList(rs.rechercherParDate(current_user, DPCurrentDate1.toString()));
        // TODO
        ColDest.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
        ColStat.setCellValueFactory(new PropertyValueFactory<>("statut_reclamation"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));

        tableRec.setItems(data);
    }

    @FXML
    private void btnReclamationReçu(ActionEvent event) {
        try {
            IdBtnAjouter.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ReclamationRecu.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
