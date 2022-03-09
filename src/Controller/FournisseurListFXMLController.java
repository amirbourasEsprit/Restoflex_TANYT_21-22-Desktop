/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.utilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.util.Callback;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class FournisseurListFXMLController implements Initializable {

    @FXML
    private TableView<utilisateur> tv_fourn;
    @FXML
    private TableColumn<utilisateur, String> nom_four_col;
    @FXML
    private TableColumn<utilisateur, String> prenom_four_col;
    @FXML
    private TableColumn<utilisateur, String> cin_four_col;
    @FXML
    private TableColumn<utilisateur, String> email_four_col;
    @FXML
    private TableColumn<utilisateur, String> tel_four_col;
    @FXML
    private TableColumn<utilisateur, String> date_four_col;
    @FXML
    private TableColumn<utilisateur, String> adresse_four_col;
    @FXML
    private TableColumn<utilisateur, String> nom__four_col;
    @FXML
    private Button supprim_four_btn;
    @FXML
    private TableColumn<utilisateur, String> status_four_col;
    @FXML
    private Button verifierFournisseur;
    @FXML
    private Button nonVerifierFournisseur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        nonVerifierFournisseur.setVisible(false);
        verifierFournisseur.setVisible(false);
    }    

    
     public void show()
    { int user_session_res_id=utilisateur.current_user.getId_rest(); 
        UtilisateurService us = new UtilisateurService();
    ObservableList<utilisateur> data = FXCollections.observableArrayList(us.afficherfour(user_session_res_id));
          // TODO
        nom_four_col.setCellValueFactory(new PropertyValueFactory("nom"));
        prenom_four_col.setCellValueFactory(new PropertyValueFactory("prenom"));
        cin_four_col.setCellValueFactory(new PropertyValueFactory("cin"));
        email_four_col.setCellValueFactory(new PropertyValueFactory("email"));
        tel_four_col.setCellValueFactory(new PropertyValueFactory("num_tel"));
        date_four_col.setCellValueFactory(new PropertyValueFactory("date_naissance"));
        adresse_four_col.setCellValueFactory(new PropertyValueFactory("adresse"));
        nom__four_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<utilisateur,String>,ObservableValue<String>>(){
            
            
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<utilisateur,String> param){
            return new SimpleObjectProperty(param.getValue().fournisseur.getNom_fournisseur());
           
            }
        } );
        status_four_col.setCellValueFactory(new PropertyValueFactory("Status_compte"));

        supprim_four_btn.setVisible(false);
        tv_fourn.setItems(data);
    }
       public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
       @FXML
    private void supprimer_four(ActionEvent event) {
         UtilisateurService us=new UtilisateurService();
        utilisateur u= tv_fourn.getSelectionModel().getSelectedItem();
          boolean check=Suppression_Box("verification", "vous etes sur");
          if(check){
                us.supprimer(u.getId_utilisateur());
        show();
    }
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
    private void temp_tab_click(MouseEvent event) {
        supprim_four_btn.setVisible(true);
        utilisateur u = tv_fourn.getSelectionModel().getSelectedItem();
         if ("non_verifier".equals(u.getStatus_compte())) {
        nonVerifierFournisseur.setVisible(false);
        verifierFournisseur.setVisible(true);

        } else if ("verifier".equals(u.getStatus_compte())) {
        nonVerifierFournisseur.setVisible(true);
        verifierFournisseur.setVisible(false);


        }
    }

    @FXML
    private void close_window(ActionEvent event) {
         System.exit(0);
    }
    
      public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    @FXML
    private void verifierFournisseur(ActionEvent event) throws SQLException {
           boolean check = Suppression_Box("Verification", "vous etes sur de verifier cet employe");
        if (check) {
            utilisateur user = tv_fourn.getSelectionModel().getSelectedItem();
        user.setStatus_compte("verifier");
        
        
        UtilisateurService utilisateurService= new UtilisateurService();
        
        
        utilisateurService.modifier(user.getId_utilisateur(), user);
            information_Box("Verification", "Succes");
             nonVerifierFournisseur.setVisible(false);
            verifierFournisseur.setVisible(false);
            show();
            

        }
    }

    @FXML
    private void nonVerifierFournisseur(ActionEvent event) throws SQLException {
                   boolean check = Suppression_Box("Verification", "vous etes sur de verifier cet employe");
        if (check) {
            utilisateur user = tv_fourn.getSelectionModel().getSelectedItem();
        user.setStatus_compte("non_verifier");
        
        
        UtilisateurService utilisateurService= new UtilisateurService();
        
        
        utilisateurService.modifier(user.getId_utilisateur(), user);
            information_Box("Verification", "Succes");
             nonVerifierFournisseur.setVisible(false);
            verifierFournisseur.setVisible(false);
            show();
            

        }
    }
}
