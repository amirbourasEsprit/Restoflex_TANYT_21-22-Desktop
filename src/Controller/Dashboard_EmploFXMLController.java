/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class Dashboard_EmploFXMLController implements Initializable {

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
        // TODO
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
    private void commande_emp_btn(ActionEvent event) throws IOException {
        Loadpage("../GUI/categorie");
        
    }
    private void Loadpage(String page) throws IOException {
    Parent root =null;
    root =FXMLLoader.load(getClass().getResource(page+".fxml"));
    contentArea.setCenter(root);
    }

    @FXML
    private void conge_emp_btn(ActionEvent event) throws IOException {
        Loadpage("../GUI/Afficher_Conge_Emp");
    }

    @FXML
    private void reclamationEmployee(ActionEvent event) throws IOException {
         Loadpage("../gui/ReclamationFXML");
    }

    @FXML
    private void btn_parametre_profile(ActionEvent event) throws IOException {
         Loadpage("../gui/Modifier_Profile_utilisateur_fournisseur");
    }

   
    
}
