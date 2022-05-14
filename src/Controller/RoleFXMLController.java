/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.role;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import service.RoleService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class RoleFXMLController implements Initializable {

    @FXML
    private TableView<role> tv_role;
    @FXML
    private Button ajout_role;
    @FXML
    private Button supprimer_role;
    @FXML
    private TableColumn<role, String> Nom_role_col;
    @FXML
    private TextField nom_rol_text;
    @FXML
    private Label nom_role_lab;
    @FXML
    private Button show_ajout_role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        
    }    
    //pour remplir table view role
    public void show(){
    RoleService rs= new RoleService();
    ObservableList<role> data_Role=FXCollections.observableArrayList(rs.afficher());
    Nom_role_col.setCellValueFactory(new PropertyValueFactory("nom_role"));
    tv_role.setItems(data_Role);
    visibility(false);
    }
//pour ajouter un role
    @FXML
    private void ajout_btn(ActionEvent event) throws SQLException {
        RoleService rs=new RoleService();
        role r=new role();
        if(Nom_role_col.getText().isEmpty())
        {alert_Box("ERREUR", "Remplir le champs!");}
             else {
                boolean check= Suppression_Box("Verification", "Vous êtes sûr d'ajouter ce nouveau type");
                if(check){
                    r.setNom_role((nom_rol_text.getText()));
                    rs.ajouter(r);
                    show();
                    visibility(false);
                    }
                 }
    }
//pour supprimer un role
    @FXML
    private void supprimer_btn(ActionEvent event) throws SQLException {
        RoleService rs=new RoleService();
        role r=tv_role.getSelectionModel().getSelectedItem();
       boolean check=Suppression_Box("verification", "vous etes sur");
         if(check){
                rs.supprimer(r.getId_role());
                visibility(false);
        show();
    }
         
    }
       @FXML
    private void role_tabV(MouseEvent event) {
        visibility(false);
        supprimer_role.setVisible(true);
    }
    public void visibility (boolean test){
    ajout_role.setVisible(test);
    supprimer_role.setVisible(test);
    nom_rol_text.setVisible(test);
    nom_role_lab.setVisible(test);
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
     public void alert_Box(String title, String message) {
    Alert dg = new Alert(Alert.AlertType.WARNING);
    dg.setTitle(title);
    dg.setContentText(message);
    dg.show();
    }


    @FXML
    private void show_ajout_role(ActionEvent event) {
        visibility(true);
        supprimer_role.setVisible(false);
        
    }
 
    
}
