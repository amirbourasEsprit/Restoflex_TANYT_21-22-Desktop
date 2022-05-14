/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.stock;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.StockService;
import interfaces.L_StockService;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import static javafx.scene.input.KeyEvent.KEY_TYPED;
import javafx.stage.Modality;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abyrm
 */
public class ModifierStockController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField nom_stock;
    @FXML
    private TextField prix_unitaire_txt;
    @FXML
    private TextField quantite_txt;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button annulerbtn;
 int IDstock ;
 boolean update ;
               StockService stoc = new StockService ();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
         nom_stock.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
            prix_unitaire_txt.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
            quantite_txt.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
  

    }    

    @FXML
    private void Update(ActionEvent event) {
              stock st = new stock();
              st.setId_stock(IDstock);
            st.setNom_stock(nom_stock.getText());
          st.setPrix_unitaire(Float.parseFloat(prix_unitaire_txt.getText()));
          st.setQuantite(Float.parseFloat(quantite_txt.getText()));

          //st.setId_fournisseur(1);
  
          
          try{
              boolean check=Suppression_Box("verification", "vous etes surde modifier ce fournisseur");
          if(check){
    stoc.modifierStock(IDstock, st);
    JOptionPane.showMessageDialog(null,"le produit a été modifier avec succes");
          }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Stock.fxml"));
     Parent root = loader.load();
     pane.getChildren().add(root);
    }catch(Exception e)
    {JOptionPane.showMessageDialog(null, e);}
      
    }

    @FXML
    private void Cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Stock.fxml"));
            Parent root = loader.load();
            pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setTextField(int id ,String nomS,float prixU, float quantiteS ) {
        IDstock=id;  
        nom_stock.setText(nomS);
        prix_unitaire_txt.setText(Float.toString(prixU));
        quantite_txt.setText(Float.toString(quantiteS));
   
    }
    
  void setUpdate(boolean b) {
        this.update = b;

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

    
}
