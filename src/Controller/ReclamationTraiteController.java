/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ReclamationFXMLController.r;
import entities.reclamation;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ReclamationTraiteController implements Initializable {

    @FXML
    private Button IdBtnSupp;
   @FXML
    private TableView<reclamation> tableRec;
        @FXML
    private TableColumn<reclamation, String> ColDest;
    @FXML
    private TableColumn<reclamation, String> ColStat;
    @FXML
    private TableColumn<reclamation, Date> ColDate;
    
    @FXML
    private DatePicker recherche;
    utilisateur util=utilisateur.current_user;

    /**
     * Initializes the controller class.
     */
    static reclamation r;
    ObservableList<reclamation> listR ;
      int current_user =util.getId_utilisateur();
     String UserName =util.getPrenom();
     int role =util.getId_role();
    @FXML
    private Button btnretour;
    @FXML
    private Button btnDetails;
    @FXML
    private AnchorPane pane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ReclamationService rs = new ReclamationService();
        
       listR = FXCollections.observableArrayList();
        List<reclamation> Al = rs.afficherReclamationTraite();
        
        Al.stream().forEach((j)->{
            listR.add(j);
        });
        
         ColDest.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
         ColStat.setCellValueFactory(new PropertyValueFactory<>("statut_reclamation"));
         ColDate.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        
         tableRec.setItems(listR);
         
         
    }    

   

    @FXML
    private void BtnSuppRec(ActionEvent event) {
    ReclamationService rs = new ReclamationService();
      reclamation rc = tableRec.getSelectionModel().getSelectedItem();
      
      reclamation rec = rs.chercherReclamation(rc.getNum_reclamation());
      
        try {
          //  rs.supprimerReclamation(r.getNum_reclamation());
             boolean check=Suppression_Box("verification", "vous etes sur");
         if(check){
                rs.supprimerReclamation(rec.getNum_reclamation());}
               // visibility(false);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ActualiserTable();
        
    }
    
     private void ActualiserTable() {
      
            listR.clear();
            ReclamationService rs = new ReclamationService();
     
       ObservableList<reclamation> listR = FXCollections.observableArrayList();
        List<reclamation> Al = rs.afficherReclamationTraite();
        
        Al.stream().forEach((j)->{
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
    private void recherche(ActionEvent event) {
         ReclamationService rs = new ReclamationService();
        String recherche=this.recherche.getValue().toString();
       
        Date  DPCurrentDate1 =  Date.valueOf(recherche);

        ObservableList<reclamation> data = FXCollections.observableArrayList(rs.rechercherParDate(current_user,DPCurrentDate1.toString()));
        // TODO
          ColDest.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
         ColStat.setCellValueFactory(new PropertyValueFactory<>("statut_reclamation"));
         ColDate.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        
         tableRec.setItems(data);
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ReclamationRecu.fxml"));
           Parent root = loader.load();
           pane.getChildren().add(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void details(ActionEvent event) {
        try {
               r =tableRec.getSelectionModel().getSelectedItem();
           
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../gui/DetailsRecFXML.fxml"));
            Parent root = loader1.load();
             pane.getChildren().add(root);
            DetailsRecFXMLController details = loader1.getController();
            details.setReservation(r);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
