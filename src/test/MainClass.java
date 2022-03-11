/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Thinkpad
 */
public class MainClass extends Application {
      @Override
    public void start(Stage primaryStage) {
    
        
        try {
            //  Scene scene = new Scene(root, 300, 250);
       //Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajoutercommande.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("../GUI/categorie.fxml"));
            
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion commande");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

