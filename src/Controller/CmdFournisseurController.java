/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Commande;
import entities.facture;
import entities.stock;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.CommandeCRUD;
import service.FactureService;
import service.StockService;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class CmdFournisseurController implements Initializable {

    @FXML
    private TableView<Commande> tableCmd;
    @FXML
    private TableColumn<Commande, Long> colNumCmd;
    @FXML
    private TableColumn<Commande, Date> colDateCmd;
    @FXML
    private TableColumn<Commande, Date> colDateLivr;
    @FXML
    private TableColumn<Commande, Float> colQuantite;
    @FXML
    private TableColumn<Commande, String> colPdt;

    protected static  Commande c;
    utilisateur u=utilisateur.current_user;
    @FXML
    private AnchorPane pane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FactureService fs = new FactureService();
        affichCmd();
        
  }  
    
     public void affichCmd(){
        
        int user_session_res_id=u.getId_rest(); //on remplace current user id_rest
        int user_session_fournisseur_id=u.getId_fournisseur();//on remplace current user fournisseur id
    
        FactureService fs = new FactureService();
        ObservableList<Commande> data = FXCollections.observableArrayList(fs.afficherCommande(user_session_res_id, user_session_fournisseur_id));
        
        colNumCmd.setCellValueFactory(new PropertyValueFactory("id_cmd"));
        colDateCmd.setCellValueFactory(new PropertyValueFactory("date_cmd"));
        colDateLivr.setCellValueFactory(new PropertyValueFactory("date_livraison"));
        colQuantite.setCellValueFactory(new PropertyValueFactory("quantite"));
        colPdt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande,String>,ObservableValue<String>>(){
            
        @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande,String> param){
            return new SimpleObjectProperty(param.getValue().produit.getNom_pdt());
           }
         });
      
        tableCmd.setItems(data);
        
    }
    
    @FXML
    private void ajouterFact(ActionEvent event) {
             int user_session_res_id=u.getId_rest(); //on remplace current user id_rest
            int user_session_fournisseur_id=u.getId_fournisseur();//on remplace current user fournisseur id
            
            c = tableCmd.getSelectionModel().getSelectedItem();
            FactureService fs = new FactureService();
            CommandeCRUD commande=new CommandeCRUD();
            Commande cmd=new Commande();
            StockService stockService=new StockService();
            System.out.println(c.getQuantite()+c.produit.getNom_pdt());
             stockService.modifierQuantiteStock(c.getQuantite(),user_session_fournisseur_id,c.produit.getNom_pdt());
            cmd.setStatut("En Livraison");
            commande.modifierCommandeStatut(cmd, c.getId_cmd());
            float totalFacture = fs.calculTotal(c.getId_cmd());
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            
            facture fct = new facture(sqlDate, totalFacture, "non payée", user_session_fournisseur_id, user_session_res_id);
            fs.ajouterFacture(fct);
           try { 
            
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation d'ajout");
            a.setContentText("Facture crée avec succès !");
            a.show();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ListeFacture.fxml"));
            Parent root = loader.load();
             pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(CmdFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   

}
    

