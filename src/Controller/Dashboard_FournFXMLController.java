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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
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
    
}
