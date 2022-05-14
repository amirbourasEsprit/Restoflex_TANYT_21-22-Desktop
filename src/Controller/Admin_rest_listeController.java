/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.restaurant;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javax.swing.JOptionPane;
import service.RestaurantService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class Admin_rest_listeController implements Initializable {

    @FXML
    private TableView<restaurant> tv_rest;
    @FXML
    private TextField nom_rest;
    @FXML
    private TextField adresse_rest;
    @FXML
    private TextField spec_rest;
    @FXML
    private Button udp_btn;
    @FXML
    private Button ajout_btn;
    @FXML
    private Button del_btn;
    @FXML
    private TextField tel_rest;
    @FXML
    private TextField Email_rest;
  
    @FXML
    private Label nom_lab;
    @FXML
    private Label adresse_lab;
    @FXML
    private Label spec_lab;
    @FXML
    private Label tel_lab;
    @FXML
    private Label email_lab;
      @FXML
    private TableColumn<restaurant, String> Nom_rest_col;
    @FXML
    private TableColumn<restaurant, String> spec_rest_col;
    @FXML
    private TableColumn<restaurant, String> adresse_rest_col;
    @FXML
    private TableColumn<restaurant, String> email_rest_col;
    @FXML
    private TableColumn<restaurant, String> tel_rest_col;
    @FXML
    private Button btn_add_res;
    @FXML
    private Button annul_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }    
    public void show(){
    RestaurantService rs = new RestaurantService();
    ObservableList<restaurant> data = FXCollections.observableArrayList(rs.afficher());
          // TODO
        Nom_rest_col.setCellValueFactory(new PropertyValueFactory("nom"));
        spec_rest_col.setCellValueFactory(new PropertyValueFactory("specialite"));
        adresse_rest_col.setCellValueFactory(new PropertyValueFactory("adresse"));
        email_rest_col.setCellValueFactory(new PropertyValueFactory("email"));
        tel_rest_col.setCellValueFactory(new PropertyValueFactory("num_tel"));


        tv_rest.setItems(data);
        visibility(false);
        
    }
    @FXML
    private void show_add_rest(ActionEvent event) {
        visibility(true);
         annul_btn.setVisible(false);
         udp_btn.setVisible(false);
         del_btn.setVisible(false);
         clear();
         
    }

    @FXML
    private void update_rest(ActionEvent event) throws SQLException {
        RestaurantService rs= new RestaurantService();
        restaurant r= tv_rest.getSelectionModel().getSelectedItem();
        if(nom_rest.getText().isEmpty()|| spec_rest.getText().isEmpty()||adresse_rest.getText().isEmpty()
           || Email_rest.getText().isEmpty()|| tel_rest.getText().isEmpty())
            {alert_Box("ERREUR", "remplir tous les champs");}
        else
        {
            if(!verifphone(tel_rest.getText())||!test_Email(Email_rest.getText()))
            { alert_Box("ERREUR", "verifier les champs");}
                else{ 
                      boolean check=Suppression_Box("verification", "vous êtes sûr de modifier? ");
                         if (check)
                         {
                             r.setNom(nom_rest.getText());
                             r.setSpecialite(spec_rest.getText());
                             r.setAdresse(adresse_rest.getText());
                             r.setEmail(Email_rest.getText());
                             r.setNum_tel(tel_rest.getText());
                             rs.modifier(r.getId_rest(), r);
                             show();
                             visibility(false);
                        }
                     } 
               
        }
 }

    @FXML
    private void ajout_rest(ActionEvent event) throws SQLException {
        RestaurantService rs=new RestaurantService();
        restaurant r=new restaurant();
        
        if(nom_rest.getText()==null||spec_rest.getText()==null || adresse_rest.getText()==null||Email_rest.getText()==null||tel_rest.getText()==null )
             {alert_Box("ERREUR", "remplir tous les champs! "); 
             }
         
        else 
            {
             if(!verifphone(tel_rest.getText())||!test_Email(Email_rest.getText()))
                 { alert_Box("ERREUR", "verifier les champs télèphone et email! ");}
              else {     
                  boolean check=Suppression_Box("verification", "vous êtes sûr d'ajouter nouveau restaurant? ");
                  if (check)       
                {
                         r.setNom(nom_rest.getText());
                         r.setSpecialite(spec_rest.getText());
                         r.setAdresse(adresse_rest.getText());
                         r.setEmail(Email_rest.getText());
                         r.setNum_tel(tel_rest.getText());
                         rs.ajouter(r);
                         show();
                         visibility(false);
                }
            }
         }
    }

    @FXML
    private void delete_rest(ActionEvent event) throws SQLException {
         RestaurantService rs=new RestaurantService();
        restaurant r=tv_rest.getSelectionModel().getSelectedItem();
         boolean check=Suppression_Box("verification", "vous etes sur");
         if(check){
                rs.supprimer(r.getId_rest());
                visibility(false);
        show();
    }
    }

     private void visibility(boolean test){
    nom_rest.setVisible(test);
    nom_lab.setVisible(test);
    adresse_rest.setVisible(test);
    adresse_lab.setVisible(test);
    tel_lab.setVisible(test);
    tel_rest.setVisible(test);
    Email_rest.setVisible(test);
    email_lab.setVisible(test);
    udp_btn.setVisible(test);
    ajout_btn.setVisible(test);
    del_btn.setVisible(test);
    spec_rest.setVisible(test);
    spec_lab.setVisible(test);
    annul_btn.setVisible(test);

    }
      @FXML
    private void annuler_operation(ActionEvent event) {
        clear();
        btn_add_res.setVisible(true);
        visibility(false);
    }


    @FXML
    private void temp_tab_click(MouseEvent event) {
       
         visibility(true);
        btn_add_res.setVisible(false);
        ajout_btn.setVisible(false);
        
 
      
        restaurant r= tv_rest.getSelectionModel().getSelectedItem();
        
        nom_rest.setText(r.getNom());
        spec_rest.setText(r.getSpecialite() );
        adresse_rest.setText(r.getAdresse());
        Email_rest.setText(r.getEmail());
        tel_rest.setText(r.getNum_tel());
    }
    public void clear(){
        nom_rest.setText(null);
        spec_rest.setText(null );
        adresse_rest.setText(null);
        Email_rest.setText(null);
        tel_rest.setText(null);
    }
    public void alert_Box(String title, String message) {
    Alert dg = new Alert(Alert.AlertType.WARNING);
    dg.setTitle(title);
    dg.setContentText(message);
    dg.show();
    }
        //test num tel
        public static boolean verifphone (String tel){

            return isNumeric(tel)&& tel.length()==8;
         }
        
      public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //test mail
     public boolean test_Email(String mail) {
        int test = 0;
        int position = 0;
        int test2 = 0;
        String[] tab = {"/", ";", ",", ":", "'", "&", "=", ">", "-", "_", "+", " ","!"};

        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == "@".charAt(0)) {
                test++;
                position = i;
            }

        }
        for (int k = 0; k < mail.length(); k++) {

            for (String tab1 : tab) {
                if (mail.charAt(k) == tab1.charAt(0)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < mail.length(); i++) {
            if ((test == 1) && (mail.charAt(i) == ".".charAt(0))) {

                if (((mail.length() > i + 2) && (i > position + 4))) {
                    for (int j = position; j < mail.length(); j++) {
                        if (mail.charAt(j) == ".".charAt(0)) {
                            test2++;

                        }
                    }
                    if (test2 > 1) {
                        return false;
                    }

                    return true;
                }

            }

        }
        return false;
    }
      public boolean Suppression_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

      }  
}
