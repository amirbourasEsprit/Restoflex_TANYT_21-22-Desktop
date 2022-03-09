/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class LoginRestoflexController implements Initializable {

    @FXML
    private VBox vbox;
    private Parent fxml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TranslateTransition t =new TranslateTransition(Duration.seconds(0.7), vbox);
       t.setToX(vbox.getLayoutX()*60);
       t.play();
       t.setOnFinished(e-> {
           try {
               fxml =FXMLLoader.load(getClass().getResource("../GUI/SignIn.fxml"));
               vbox.getChildren().removeAll();
               vbox.getChildren().setAll(fxml);
           } catch (IOException ex) {
               System.err.println(ex.getMessage());
           }
       } ) ;
    }
    //when i click on sign in
       @FXML
       private void open_signin(ActionEvent event)
       {
             TranslateTransition t =new TranslateTransition(Duration.seconds(0.7), vbox);
       t.setToX(vbox.getLayoutX()*60);
       t.play();
       t.setOnFinished(e-> {
           try {
               fxml =FXMLLoader.load(getClass().getResource("../GUI/SignIn.fxml"));
               vbox.getChildren().removeAll();
               vbox.getChildren().setAll(fxml);
           } catch (IOException ex) {
               System.err.println(ex.getMessage());
           }
       } ) ;
       }
       //when i click on sign up
            @FXML
       private void open_signup(ActionEvent event)
       {
             TranslateTransition t =new TranslateTransition(Duration.seconds(0.7), vbox);
       t.setToX(0);
       t.play();
       t.setOnFinished(e-> {
           try {
               fxml =FXMLLoader.load(getClass().getResource("../GUI/SignUp.fxml"));
               vbox.getChildren().removeAll();
               vbox.getChildren().setAll(fxml);
           } catch (IOException ex) {
               System.err.println(ex.getMessage());
           }
       } ) ;
    }

 //close window
    @FXML
    private void close_window(ActionEvent event) {
                 System.exit(0);
    }
 }    
    

