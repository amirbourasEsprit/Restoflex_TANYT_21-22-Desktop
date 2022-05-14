/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.EmployeListFXMLController.isNumeric;
import static Controller.SignUpController.isNumeric;
import entities.restaurant;
import entities.utilisateur;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import service.RestaurantService;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class Gerant_modifier_profileFXMLController implements Initializable {

    @FXML
    private AnchorPane profil_greant;
    @FXML
    private TextField nom_rest;
    @FXML
    private TextField Email_rest;
    @FXML
    private TextField spec_rest;
    @FXML
    private TextField adresse_rest;
    @FXML
    private TextField tel_rest;
    @FXML
    private Button udp_rest_btn;
    @FXML
    private Label nom_res_lab;
    @FXML
    private Label adresse_res_lab;
    @FXML
    private Label spec_res_lab;
    @FXML
    private Label tel_res_lab;
    @FXML
    private Label email_res_lab;
    @FXML
    private TextField adresse_ger;
    @FXML
    private TextField tel_ger;
    @FXML
    private TextField nom_ger;
    @FXML
    private Label nom_ger_lab;
    @FXML
    private Label tel_ger_lab;
    @FXML
    private Label adresse_ger_lab;
    @FXML
    private Button udp_ger_btn;
    @FXML
    private TextField prenom_ger;
    @FXML
    private Label prenom_ger_lab;
    @FXML
    private Label pwd_ger_lab;
    @FXML
    private PasswordField pwd_ger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // utilisateur u =new utilisateur();
        UtilisateurService us= new UtilisateurService();
        
        ObservableList<utilisateur> data = FXCollections.observableArrayList(us.afficherCurrentuser(utilisateur.current_user.getId_utilisateur()));
        System.out.println(data);
        nom_ger.setText(data.get(0).getNom());
        prenom_ger.setText(data.get(0).getPrenom());
        pwd_ger.setText(data.get(0).getMdp());
        tel_ger.setText(data.get(0).getNum_tel());
        adresse_ger.setText(data.get(0).getAdresse());
  
        
       nom_rest.setText(data.get(0).restaurant.getNom());
       spec_rest.setText(data.get(0).restaurant.getSpecialite() );
       adresse_rest.setText(data.get(0).restaurant.getAdresse());
       Email_rest.setText(data.get(0).restaurant.getEmail());
       tel_rest.setText(data.get(0).restaurant.getNum_tel());
    }    
//pour modifier mon restaurant
    @FXML
    private void update_rest(ActionEvent event) throws SQLException {
        
        
        RestaurantService rs= new RestaurantService();
        restaurant r=new restaurant();
        UtilisateurService us=new UtilisateurService();
        ObservableList<utilisateur> data = FXCollections.observableArrayList(us.afficherCurrentuser(utilisateur.current_user.getId_utilisateur()));
        if (nom_rest.getText().isEmpty() || spec_rest.getText().isEmpty()|| adresse_rest.getText().isEmpty() ||Email_rest.getText().isEmpty()||tel_rest.getText().isEmpty())
            {alert_Box("ERREUR", "Remplir tous les champs!");}
         else 
       { if(!verifphone(tel_rest.getText())||!test_Email(Email_rest.getText())){
           alert_Box("ERREUR", "verifier le champ télèphone ou Email!");
       }else{
           boolean check=Suppression_Box("verification", "Vous êtes sur de modifier le profile de votre restaurant!");
          if(check){
           
                r.setNom(nom_rest.getText());
                r.setSpecialite(spec_rest.getText());
                r.setNum_tel(tel_rest.getText());
                r.setEmail(Email_rest.getText());
                r.setAdresse(adresse_rest.getText());
                rs.modifier(data.get(0).restaurant.getId_rest(), r);
                alert_Box("SUCCESS", "Vous avez modifier les informations de votre restaurant!");
          }
       }
    }

    
    }
//pour le mise a jour de mon profile comme gerant
    @FXML
    private void update_ger(ActionEvent event) throws SQLException {
        UtilisateurService us=new UtilisateurService();
       ObservableList<utilisateur> data = FXCollections.observableArrayList(us.afficherCurrentuser(utilisateur.current_user.getId_utilisateur()));
       utilisateur u = data.get(0);
       if (nom_ger.getText().isEmpty() || prenom_ger.getText().isEmpty()|| pwd_ger.getText().isEmpty() ||tel_ger.getText().isEmpty()||adresse_ger.getText().isEmpty())
       {alert_Box("ERREUR", "Remplir tous les champs!");}
         else 
       { if(!verifphone(tel_ger.getText())){
           alert_Box("ERREUR", "verifier le champ télèphone!");
       }else{
           boolean check=Suppression_Box("verification", "Vous êtes sur de modifier votre profile!");
          if(check){
             if ((pwd_ger.getText()).equals(u.getMdp()))
             {
                u.setNom(nom_ger.getText());
                u.setPrenom(prenom_ger.getText());
               // u.setMdp(us.crypter_password(pwd_ger.getText()));
                u.setNum_tel(tel_ger.getText());
                u.setAdresse(adresse_ger.getText());
                us.modifier(utilisateur.current_user.getId_utilisateur(), u);
                alert_Box("SUCCESS", "Vous avez modifier vos informations!");
              }
             else{ boolean checkpwd=Suppression_Box("verification", "Vous êtes sur de modifier votre mot de passe!");
                      if(checkpwd)
                      {
                          if(!us.test_Password(pwd_ger.getText())){
                           alert_Box("Verifier", "votre mot de passe doit contenir au minimum 8 caractères\\n à savoir : au moins une lettre minuscule,une lettre majuscule et un chiffre");
                          }else{ u.setNom(nom_ger.getText());
                            u.setPrenom(prenom_ger.getText());
                            u.setMdp(us.crypter_password(pwd_ger.getText()));
                            u.setNum_tel(tel_ger.getText());
                            u.setAdresse(adresse_ger.getText());
                            us.modifier(utilisateur.current_user.getId_utilisateur(), u);
                            pwd_ger.setText(us.crypter_password(pwd_ger.getText()));
                            alert_Box("SUCCESS", "Vous avez modifier vos informations!");
                      }
                    }
                }
       }
      }
    }
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
    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
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
}
    

