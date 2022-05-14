/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.facture;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.FactureService;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class StatFactureController implements Initializable {

    @FXML
    private PieChart pieChartFact;
    @FXML
    private FontAwesomeIconView pagePecedente;
    utilisateur u=utilisateur.current_user;
    @FXML
    private AnchorPane pane;
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FactureService fs = new FactureService();
        List<facture> lf = fs.afficherFacture();
        ObservableList<PieChart.Data> list=FXCollections.observableArrayList(
            new PieChart.Data("Non Payée", fs.nbFactureNPayee(u.getId_rest())),
            new PieChart.Data("Payée", fs.nbFacturePayee(u.getId_rest()))
         );
   
         pieChartFact.setAnimated(true);
         pieChartFact.setData(list);
         
   }    

    @FXML
    private void getBack(MouseEvent event) {
        
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("../gui/ListeFacture.fxml"));
             Parent root = loader.load();
             pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(StatFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
