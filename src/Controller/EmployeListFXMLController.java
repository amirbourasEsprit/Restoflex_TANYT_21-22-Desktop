/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.utilisateur;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class EmployeListFXMLController implements Initializable {

    @FXML
    private TableView<utilisateur> tv_emp;
    @FXML
    private TableColumn<utilisateur, String> nom_emp_col;
    @FXML
    private TableColumn<utilisateur, String> prenom_emp_col;
    @FXML
    private TableColumn<utilisateur, String> cin_emp_col;
    @FXML
    private TableColumn<utilisateur, String> email_emp_col;
    @FXML
    private TableColumn<utilisateur, String> num_tel_emp_col;
    @FXML
    private TableColumn<utilisateur, Date> dnaissance_emp_col;
    @FXML
    private TableColumn<utilisateur, String> addresse_emp_col;
    @FXML
    private TableColumn<utilisateur, Integer> salaire_emp_col;
    @FXML
    private TableColumn<utilisateur, Integer> solde_conge_emp_col;
    @FXML
    private TableColumn<utilisateur, String> poste_emp_col;
    @FXML
    private TextField salaire_emp_text;
    @FXML
    private TextField solde_cong_text;
    @FXML
    private TextField poste_employ_text;
    @FXML
    private Button modf_btn_emp;
    @FXML
    private Button supprim_emp_btn;
    @FXML
    private Hyperlink vider_aff_id;
    @FXML
    private Label salairetxt;
    @FXML
    private Label congetxt;
    @FXML
    private Label postetxt;
    @FXML
    private Button verifierEmploye;
    @FXML
    private Button nonVerifierEmploye;
    @FXML
    private TableColumn<utilisateur, String> status_emp_col;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        visibility(false);
        nonVerifierEmploye.setVisible(false);
        verifierEmploye.setVisible(false);
    }

    public void show() {
        int user_session_res_id = utilisateur.current_user.getId_rest();
        UtilisateurService us = new UtilisateurService();
        ObservableList<utilisateur> data = FXCollections.observableArrayList(us.afficherEmp(user_session_res_id));
        // TODO
        nom_emp_col.setCellValueFactory(new PropertyValueFactory("nom"));
        prenom_emp_col.setCellValueFactory(new PropertyValueFactory("prenom"));
        cin_emp_col.setCellValueFactory(new PropertyValueFactory("cin"));
        email_emp_col.setCellValueFactory(new PropertyValueFactory("email"));
        num_tel_emp_col.setCellValueFactory(new PropertyValueFactory("num_tel"));
        dnaissance_emp_col.setCellValueFactory(new PropertyValueFactory("date_naissance"));
        addresse_emp_col.setCellValueFactory(new PropertyValueFactory("adresse"));
        salaire_emp_col.setCellValueFactory(new PropertyValueFactory("Salaire"));
        solde_conge_emp_col.setCellValueFactory(new PropertyValueFactory("solde_conge"));
        poste_emp_col.setCellValueFactory(new PropertyValueFactory("poste_employe"));
        status_emp_col.setCellValueFactory(new PropertyValueFactory("Status_compte"));

        tv_emp.setItems(data);
    }

    @FXML
    
    private void modifier_emp(ActionEvent event) throws SQLException {
        UtilisateurService us = new UtilisateurService();
        utilisateur u = tv_emp.getSelectionModel().getSelectedItem();
        if (!isNumeric(salaire_emp_text.getText()) || !isNumeric(solde_cong_text.getText())) {
            alert_Box("ERREUR", "verifier les champs");
        } else {
            boolean check = Suppression_Box("verification", "vous etes sur");
            if (check) {
                u.setPoste_employe(poste_employ_text.getText());
                u.setSalaire(Integer.parseInt(salaire_emp_text.getText()));
                u.setSolde_conge(Integer.parseInt(solde_cong_text.getText()));

                us.modifier(u.getId_utilisateur(), u);
                show();
                visibility(false);
            }
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    @FXML
    private void supprimer_emp(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        utilisateur u = tv_emp.getSelectionModel().getSelectedItem();
        boolean check = Suppression_Box("verification", "vous etes sur");
        if (check) {
            us.supprimer(u.getId_utilisateur());
            visibility(false);
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

    private void visibility(boolean test) {
        poste_employ_text.setVisible(test);
        solde_cong_text.setVisible(test);
        salaire_emp_text.setVisible(test);
        modf_btn_emp.setVisible(test);
        supprim_emp_btn.setVisible(test);
        vider_aff_id.setVisible(test);
        salairetxt.setVisible(test);
        congetxt.setVisible(test);
        postetxt.setVisible(test);
   

    }

    @FXML
    private void temp_tab_click(MouseEvent event) {
        visibility(true);

        utilisateur u = tv_emp.getSelectionModel().getSelectedItem();

        poste_employ_text.setText(u.getPoste_employe());
        solde_cong_text.setText(String.valueOf(u.getSolde_conge()));
        salaire_emp_text.setText(String.valueOf(u.getSalaire()));

        if ("non_verifier".equals(u.getStatus_compte())) {
            verifierEmploye.setVisible(true);
            nonVerifierEmploye.setVisible(false);

        } else if ("verifier".equals(u.getStatus_compte())) {
            nonVerifierEmploye.setVisible(true);
            verifierEmploye.setVisible(false);


        }

    }

    @FXML
    private void vider_aff(ActionEvent event) {
        visibility(false);
    }

  
    
      public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    @FXML
    private void verifierEmploye(ActionEvent event) throws SQLException {
        
         boolean check = Suppression_Box("Verification", "vous etes sur de verifier cet employe");
        if (check) {
            utilisateur user = tv_emp.getSelectionModel().getSelectedItem();
        user.setStatus_compte("verifier");
        
        
        UtilisateurService utilisateurService= new UtilisateurService();
        
        
        utilisateurService.modifier(user.getId_utilisateur(), user);
            information_Box("Verification", "Succes");
             nonVerifierEmploye.setVisible(false);
            verifierEmploye.setVisible(false);
            show();
            

        }
        
        
        
    }

    @FXML
    private void nonVerifierEmploye(ActionEvent event) throws SQLException {
        
          boolean check = Suppression_Box("Verification", "vous etes sur de verifier cet employe");
        if (check) {
            utilisateur user = tv_emp.getSelectionModel().getSelectedItem();
            user.setStatus_compte("non_verifier");
        
        
        UtilisateurService utilisateurService= new UtilisateurService();
        
        
        utilisateurService.modifier(user.getId_utilisateur(), user);
            information_Box("Verification", "Succes");
             nonVerifierEmploye.setVisible(false);
            verifierEmploye.setVisible(false);
            show();
        
        
    }}

    @FXML
    private void recherche(KeyEvent event) {
        int user_session_res_id = utilisateur.current_user.getId_rest();
        UtilisateurService us = new UtilisateurService();
        String recherche=this.recherche.getText();
        ObservableList<utilisateur> data = FXCollections.observableArrayList(us.rechercheEmployee(user_session_res_id,recherche));
        // TODO
        nom_emp_col.setCellValueFactory(new PropertyValueFactory("nom"));
        prenom_emp_col.setCellValueFactory(new PropertyValueFactory("prenom"));
        cin_emp_col.setCellValueFactory(new PropertyValueFactory("cin"));
        email_emp_col.setCellValueFactory(new PropertyValueFactory("email"));
        num_tel_emp_col.setCellValueFactory(new PropertyValueFactory("num_tel"));
        dnaissance_emp_col.setCellValueFactory(new PropertyValueFactory("date_naissance"));
        addresse_emp_col.setCellValueFactory(new PropertyValueFactory("adresse"));
        salaire_emp_col.setCellValueFactory(new PropertyValueFactory("Salaire"));
        solde_conge_emp_col.setCellValueFactory(new PropertyValueFactory("solde_conge"));
        poste_emp_col.setCellValueFactory(new PropertyValueFactory("poste_employe"));
        status_emp_col.setCellValueFactory(new PropertyValueFactory("Status_compte"));

        tv_emp.setItems(data);
    }

}
