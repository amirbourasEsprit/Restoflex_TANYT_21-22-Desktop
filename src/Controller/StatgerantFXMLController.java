/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class StatgerantFXMLController implements Initializable {

    @FXML
    private PieChart statUers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // ServiceUser su = new ServiceUser();
        UtilisateurService us=new UtilisateurService();
       utilisateur u= utilisateur.current_user;
      
        int employee =us.afficherEmp(u.getId_rest()).size();// su.nb_admins();
        int utilisateur_fournisseur =us.afficherfour(u.getId_rest()).size();//su.nb_coachsV();
       
        int all =  employee + utilisateur_fournisseur;

        ObservableList<Data> list_stat = FXCollections.observableArrayList(
                new PieChart.Data("Mes employees: " + (employee * 100) / all + "%", employee),
                new PieChart.Data("Mes fournisseurs:" + (utilisateur_fournisseur * 100) / all + "%", utilisateur_fournisseur)
        );
        statUers.setData(list_stat);
    }    
    
}
