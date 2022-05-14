/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.type_conge;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.TypeCongeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Yosr Belaam
 */
public class Afficher_Type_CongeFXMLController implements Initializable {

    @FXML
    private Button supprimer;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button rafraichir;
    @FXML
    private Button rechercherB;
    @FXML
    private TextField rechercher;
    @FXML
    private Label idL;
    @FXML
    private Label nomL;
    @FXML
    private Button validermodif;
    @FXML
    private Label id_Tcong;
    @FXML
    private TableView<type_conge> Tab_Tcong;
    @FXML
    private TextField nom;
    @FXML
    private TableColumn<type_conge,String> nom_Tcong;
    @FXML
    private Label Titre;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        champfalse();
        Load();
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        type_conge Tcong= Tab_Tcong.getSelectionModel().getSelectedItem();
        System.out.println( Tcong.getNom_type_conge());
         TypeCongeService tc = new TypeCongeService();
        tc.supprimerTypeConge(Tcong.getId_type_conge());
        Load();
        TrayNotification tray= new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Type supprimé avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }
    private void Load() {
        TypeCongeService Tcong = new TypeCongeService();
        ObservableList<type_conge> tc =  Tcong.afficherTypeConge();
        System.out.println(tc);
        nom_Tcong.setCellValueFactory(new PropertyValueFactory<type_conge, String>("nom_type_conge"));
        Tab_Tcong.setItems(tc);
    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
      
        FXMLLoader loader =new FXMLLoader(getClass().getResource("../GUI/Ajout_Type_CongeFXML.fxml"));
        Parent root = loader.load();
        pane.getChildren().add(root);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         type_conge tcong=  Tab_Tcong.getSelectionModel().getSelectedItem();
        champtrue();
        fill(tcong);
    }
    
    private void fill(type_conge Tcong) throws SQLException {
     
         String query = "select * from type_conge where id_type_conge=" + Tcong.getId_type_conge();
         Connection con;
         Statement ste;
         PreparedStatement prst;
         con = MyConnection.getInstance().getCnx();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(query); 
         TypeCongeService sc = new TypeCongeService();
            while(rs.next()){
               nom.setText(rs.getString("nom_type_conge"));
               id_Tcong.setText(rs.getString("id_type_conge"));
                }
                rs.close();
    }
 
    @FXML
    private void rafraichir(ActionEvent event) {
         Load();
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        TypeCongeService Tcong = new TypeCongeService();
   
        ObservableList<type_conge> tc =  Tcong.RechercherTypeConge(rechercher.getText());
        Tab_Tcong.setItems(tc);
        nom_Tcong.setCellValueFactory(new PropertyValueFactory<type_conge, String>("nom_type_conge"));
        Tab_Tcong.setItems(tc);
    }
    
    @FXML
    private void validermodif(ActionEvent event) throws SQLException {
        TypeCongeService Tcong =new TypeCongeService();
        type_conge tc= Tab_Tcong.getSelectionModel().getSelectedItem() ;
        tc.setNom_type_conge(nom.getText()); 
        Tcong.modifierTypeConge(tc);
        System.out.println(" modif done");
        //tc.toString();
        //System.out.println( tc.getId_type_conge()); 
        champfalse();
        Load();
        TrayNotification tray= new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Modification avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }
    
    private void champfalse()
    {
        Titre.setVisible(false);
        idL.setVisible(false);
        id_Tcong.setVisible(false);
        nomL.setVisible(false);
        nom.setVisible(false);
        validermodif.setVisible(false);
    }
    private void champtrue()
    {
        Titre.setVisible(true);
        idL.setVisible(true);
        id_Tcong.setVisible(true);
        nomL.setVisible(true);
        nom.setVisible(true);
        validermodif.setVisible(true);
    }
}

