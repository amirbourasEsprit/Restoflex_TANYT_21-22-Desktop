/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.reclamation;
import entities.type_reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import service.NotificationService;
import service.ReclamationService;
import service.type_reclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class TypeRecFXMLController implements Initializable {

    @FXML
    private Button IdBAjout;
    @FXML
    private Button IdBSupp;
    @FXML
    private TableView<type_reclamation> IdTable;
    @FXML
    private TableColumn<type_reclamation, String> ColTypeRec;
    @FXML
    private TextField IdNomTR;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       affichage();
       
     
    }    
        //pour remplir table view role
    public void affichage(){
    type_reclamationService trs= new type_reclamationService();
    ObservableList<type_reclamation> listTR =FXCollections.observableArrayList(trs.afficherTypeReclamation());
   ColTypeRec.setCellValueFactory(new PropertyValueFactory("nom_type_reclamation"));
  IdTable.setItems(listTR);
 //   visibility(false);
    }
 


    @FXML
    private void BtnAjouter(ActionEvent event) throws SQLException {
          type_reclamationService TRS=new type_reclamationService();
        type_reclamation tr=new type_reclamation();
        if(IdNomTR.getText().isEmpty())
        {alert_Box("ERREUR", "Remplir le champs!");}
             else {
                boolean check= Suppression_Box("Verification", "Vous êtes sûr d'ajouter ce nouveau type");
                if(check){
                    tr.setNom_type_reclamation(IdNomTR.getText());
                    TRS.ajouterTypeReclamation(tr);
        new NotificationService().Notification("Sucées", "Type Reclamation Ajoutée" );                    affichage();
                    }
      
                    }
    }
     public void alert_Box(String title, String message) {
    Alert dg = new Alert(Alert.AlertType.WARNING);
    dg.setTitle(title);
    dg.setContentText(message);
    dg.show();
    }
     public boolean Suppression_Box(String title, String message) {
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
    private void BtnSupprimer(ActionEvent event) throws SQLException {
         type_reclamationService TRS=new type_reclamationService();
        type_reclamation tr=IdTable.getSelectionModel().getSelectedItem();
        boolean check=Suppression_Box("verification", "vous etes sur");
         if(check){
                TRS.supprimerTypeReclamation(tr.getId_type_reclamation());
               // visibility(false);
        affichage();
    }
    }
    
            
        
    

}