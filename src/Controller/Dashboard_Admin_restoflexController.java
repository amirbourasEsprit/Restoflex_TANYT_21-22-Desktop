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
public class Dashboard_Admin_restoflexController implements Initializable {

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
            Loadpage("../GUI/admin_rest_liste");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void res_btn(ActionEvent event) throws IOException {
        Loadpage("../GUI/admin_rest_liste");
    }


    @FXML
    private void role_restoflex(ActionEvent event) throws IOException {
        Loadpage("../GUI/RoleFXML");
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

     private void Loadpage(String page) throws IOException {
    Parent root =null;
    root =FXMLLoader.load(getClass().getResource(page+".fxml"));
    contentArea.setCenter(root);
    }

    @FXML
    private void fournisseur_admin(ActionEvent event) throws IOException {
     Loadpage("../GUI/Fournisseur");
    }
    
    
}
