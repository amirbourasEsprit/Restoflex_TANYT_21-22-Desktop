/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import entities.stock;
import entities.utilisateur;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
//import service.FournisseurService;
import  service.StockService;

/**
 * FXML Controller class
 *
 * @author abyrm
 */
public class AjouterStockController implements Initializable {

    @FXML
    private TextField nom_stock;
    @FXML
    private TextField prix_unitaire;
    @FXML
    private TextField quantite;
    StockService stk = new StockService();
    @FXML
    private Button Cancel;
  utilisateur util =utilisateur.current_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          nom_stock.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
            prix_unitaire.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
            quantite.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
     
        // TODO
    }    

    @FXML
    private void ajouterStock(ActionEvent event) throws SQLException {
        int current_user=util.getId_fournisseur();//current_user
        stock st= new stock();
        if (nom_stock.getText().isEmpty()||prix_unitaire.getText().isEmpty()||quantite.getText().isEmpty() )
        {Alert alert = new Alert (Alert.AlertType.WARNING);
          alert.setTitle("Alert");
          alert.setHeaderText(null);
          alert.setContentText("Veuillez remplir tous les champs ! ");
          alert.showAndWait();
        }
        else
        {
               st.setNom_stock(nom_stock.getText());
        st.setPrix_unitaire(Float.parseFloat(prix_unitaire.getText()));
        st.setQuantite(Float.parseFloat(quantite.getText()));
         st.setId_fournisseur(current_user);

   
       try{
           stk.ajouterStock(st);
   
    JOptionPane.showMessageDialog(null,"le produit a été ajouté avec succes");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Dashboard_FournFXML.fxml"));
     Parent root = loader.load();
            nom_stock.getScene().setRoot(root);
    }catch(Exception e)
    {JOptionPane.showMessageDialog(null, e);}
       }
     
    }

    @FXML
    private void Cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Dashboard_FournFXML.fxml"));
            Parent root = loader.load();
            nom_stock.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
      return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }   
        }
        
    };
}    
        

public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}
}
