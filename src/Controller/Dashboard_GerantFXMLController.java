/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import javafx.scene.input.MouseEvent;
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
public class Dashboard_GerantFXMLController implements Initializable {

    @FXML
    private Button btn_Logout;
    @FXML
    private Button btn_parametre_profile;
    @FXML
    private BorderPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Loadpage("../GUI/StatgerantFXML");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void Logout(ActionEvent event) throws Exception{
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

    @FXML
    private void employee_btn(ActionEvent event) throws IOException {
        Loadpage("../GUI/employeListFXML");
    }
    
    private void Loadpage(String page) throws IOException {
    Parent root =null;
    root =FXMLLoader.load(getClass().getResource(page+".fxml"));
    contentArea.setCenter(root);
    }

    @FXML
    private void fournisseur_btn(ActionEvent event) throws IOException {
         Loadpage("../GUI/fournisseurListFXML");
    }

    @FXML
    private void pram_prof(ActionEvent event) throws IOException {
        Loadpage("../GUI/gerant_modifier_profileFXML");
        
    }

    @FXML
    private void dashboard_stats(MouseEvent event) {
          try {
            Loadpage("../GUI/StatgerantFXML");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void commandeGerant_btn(ActionEvent event) {
        try {
            Loadpage("../GUI/ajoutercommande");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }

    @FXML
    private void congeGerant_btn(ActionEvent event) throws IOException {
        Loadpage("../GUI/Afficher_Conge_Gerant");
    }

    @FXML
    private void reclamationGerant(ActionEvent event) throws IOException {
        Loadpage("../gui/ReclamationFXML");
    }

    @FXML
    private void type_reclamation_Gerant(ActionEvent event) throws IOException {
        Loadpage("../gui/TypeRecFXML");
    }

  
    
    
}
