/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
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
 * @author Yosr Belaam
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("../gui/Ajout_CongeFXML.fxml"));
        //stage.setTitle("Ajout Congé");
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Afficher_CongeFXML.fxml"));
        stage.setTitle("Afficher Congé");
        //Parent root = FXMLLoader.load(getClass().getResource("../gui/Ajout_Type_CongeFXML.fxml"));
        //stage.setTitle("Ajout Type Congé");
        //Parent root = FXMLLoader.load(getClass().getResource("../gui/Afficher_Type_CongeFXML.fxml"));
        //stage.setTitle("Afficher Type Congé");
       
        stage.setScene(new Scene(root, 1300, 700));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
