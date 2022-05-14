/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class Dashboard_FournFXMLController implements Initializable {

    @FXML
    private Button btn_parametre_profile;
    @FXML
    private Button btn_Logout;
    @FXML
    private BorderPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Loadpage("../gui/stockChart");
        } catch (IOException ex) {
            Logger.getLogger(Dashboard_FournFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }    

    @FXML
    private void Logout(ActionEvent event) {
        try{
            btn_Logout.getScene().getWindow().hide();
             Parent root= FXMLLoader.load(getClass().getResource("../GUI/LoginRestoflex.fxml"));
             Stage stage =new Stage();
             Scene scene = new Scene(root);
               scene.setFill(Color.TRANSPARENT);
                      stage.setScene(scene);
                      stage.initStyle(StageStyle.TRANSPARENT);
                      stage.show();

        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    private void close_window(ActionEvent event) {
            System.exit(0);

    }

    @FXML
    private void btn_facture_fourni(ActionEvent event) throws IOException {
        Loadpage("../gui/ListeFacture");
        
    }
      private void Loadpage(String page) throws IOException {
    Parent root =null;
    root =FXMLLoader.load(getClass().getResource(page+".fxml"));
    contentArea.setCenter(root);
    }

    @FXML
    private void commande_fournisseur(ActionEvent event) throws IOException {
        Loadpage("../gui/CmdFournisseur");
    }

    @FXML
    private void reclamationFournisseur(ActionEvent event) throws IOException {
         Loadpage("../gui/ReclamationFXML");
    }

   
    @FXML
    private void Stock_fournisseur(ActionEvent event) throws IOException {
        Loadpage("../gui/Stock");
    }

    @FXML
    private void btn_parametre_profile(ActionEvent event) throws IOException {
        Loadpage("../gui/Modifier_Profile_utilisateur_fournisseur");
    }
}
