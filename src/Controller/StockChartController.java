/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.activation.DataSource;
import static javax.swing.UIManager.get;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author abyrm
 */
public class StockChartController implements Initializable {

    @FXML
    private AnchorPane pane;
        private Statement st;
    private ResultSet rs;
    private Connection cnx;
    @FXML
    private PieChart piechart;
    /**
     * Initializes the controller class.
     */
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            cnx=MyConnection.getInstance().getCnx();
            stat();
        } catch (SQLException ex) {
            Logger.getLogger(StockChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     private void stat() throws SQLException
    {
          
           

           
          String query = "SELECT COUNT(*),nom_produit_stock FROM stock GROUP BY nom_produit_stock" ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("nom_produit_stock"),rs.getInt("COUNT(*)")));
            }     
      
      
        piechart.setTitle("**Statistiques nombres des produits**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
   
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Stock.fxml"));
      Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
    
}