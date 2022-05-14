/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.utilisateur;
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
          
           

           
          String query = "SELECT COUNT(*), s.nom_stock FROM stock s where s.id_fournisseur= "+utilisateur.current_user.getId_fournisseur()+" GROUP BY nom_stock";
          System.out.println(query.toString());
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("nom_stock"),rs.getInt("COUNT(*)")));
            }     
      
      
        piechart.setTitle("**Statistiques quantités des produits**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }

}