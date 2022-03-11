/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DetailsRecFXMLController implements Initializable {

    @FXML
    private Label Destinataire;
    @FXML
    private Label Statut;
    @FXML
    private Label Date;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
    
    private reclamation r;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {     
    }
     
        
    
       
  public void setReservation (reclamation res){  
      ReclamationService rs =new ReclamationService();
       this.r=res;
        Destinataire.setText(r.getDestinataire());
        description.setText(r.getDescription());
    Statut.setText(r.getStatut_reclamation());
    Date.setText(""+r.getDate_reclamation());
    }
}
    

