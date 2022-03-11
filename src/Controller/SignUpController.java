/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.Admin_rest_listeController.verifphone;
import Service.MyLocation;
import Service.ServiceNotification;
import entities.MyAddress;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import service.RestaurantService;
import entities.restaurant;
import entities.role;
import entities.utilisateur;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import service.RoleService;
import service.UtilisateurService;
/**
 * FXML Controller class
 *
 * @author boura
 */
public class SignUpController implements Initializable {

    @FXML
    private ChoiceBox<role> choicebox_type_user;
    private String [] fournisseur={"mazeraa","chahia"}; 
    @FXML
    private ChoiceBox< String > liste_four_emp;
    @FXML
    private ChoiceBox<restaurant> choicebox_resto;
    RestaurantService rs=new RestaurantService();
    @FXML
    private TextField Nom;
    @FXML
    private TextField prenom;
    
    @FXML
    private TextField CIN;
    @FXML
    private TextField mdp;
    @FXML
    private TextField tel;
    @FXML
    private TextField adresse;
    @FXML
    private DatePicker datebirth;
    @FXML
    private TextField Email;
    @FXML
    private TextField poste_emp;
    @FXML
    private Button btn_sign_up;
    @FXML
    private Label wrongSignup;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //pour la liste restaurant
        RestaurantService rss=new RestaurantService();
        List<restaurant> restaurant=rss.afficher();
       
        ObservableList list_restaurant=FXCollections.observableArrayList(restaurant);
        
        //pour le choice box role
        
        RoleService rs =new RoleService();
        List<role> role=rs.afficher();
        
        ObservableList list_role=FXCollections.observableArrayList(role);
            //add elementsto choice box
        choicebox_type_user.setItems(list_role);
        choicebox_type_user.setOnAction(this::getRole);
        
        
        //pour le choice des fournisseurs
        liste_four_emp.getItems().addAll(fournisseur);
        //liste_four_emp.setOnAction(this::get_id_fournisseur);
        
        //hide if he is un employees
        liste_four_emp.setVisible(false);
        poste_emp.setVisible(false);

        //add name of restaurant
        choicebox_resto.setItems(list_restaurant);
        choicebox_resto.setOnAction(this::get_id_resto);
        //liste des fournisseurs
        
        //autoset adresse
        adresse.setText(MyAdress());
    }   
    private String MyAdress() {

        MyLocation location = new MyLocation();
        MyAddress adresse = new MyAddress();
        try {
            String MyIp = location.MyIpAdress();
            adresse = location.CurrentLocation(MyIp);

        } catch (SocketException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(adresse.toString());
        return adresse.getCity();
    }
  public void getRole(ActionEvent event){
      ServiceNotification sn=new ServiceNotification();
     // System.out.println(choicebox_type_user.getSelectionModel().getSelectedItem().getId_role());
      long type_util= choicebox_type_user.getSelectionModel().getSelectedItem().getId_role();
    if (type_util==3){
         
         liste_four_emp.setVisible(true);
         poste_emp.setVisible(false);
          btn_sign_up.setVisible(true);
          wrongSignup.setText(null);
    }
    else if(type_util==2){
         
         liste_four_emp.setVisible(false);
         poste_emp.setVisible(true);
         btn_sign_up.setVisible(true);
         wrongSignup.setText(null);

    }
     else{
         liste_four_emp.setVisible(false);
         poste_emp.setVisible(false);
         btn_sign_up.setVisible(false);
          sn.Notification("Erreur", "vous ne pouvez pas creez un compte avec ce Rôle!");
        }
      System.out.println(type_util);
    }
  public void get_id_resto(ActionEvent event){
      System.out.println(choicebox_resto.getSelectionModel().getSelectedItem().getId_rest());
  }
  public void get_id_fournisseur(ActionEvent event){
     String nom_fournisseur=liste_four_emp.getValue();
      
     if (nom_fournisseur.equals("mazeraa")){
      
         System.out.println(1);
     }else if(nom_fournisseur.equals("chahia")){
         System.out.println(2);
     }
         
  }

    @FXML
    private void sign_up(ActionEvent event) throws SQLException, IOException {
        ServiceNotification sn = new ServiceNotification();
        utilisateur util= new utilisateur();
        UtilisateurService utilSer=new UtilisateurService();
        String role = String.valueOf(choicebox_type_user.getValue());
       if(Nom.getText().isEmpty()||prenom.getText().isEmpty()||Email.getText().isEmpty()||CIN.getText().isEmpty()||mdp.getText().isEmpty()||tel.getText().isEmpty()
          ||adresse.getText().isEmpty()||datebirth.getValue()==null||String.valueOf(choicebox_resto.getValue())==null||role == "null")
       { wrongSignup.setText("Remplir tous les champs!"); }
       else{
           if (!verifphone(tel.getText())|| !test_Email(Email.getText())||!verifphone(CIN.getText()))
                 { wrongSignup.setText("Verifier les champs télèphone ou email ou cin !  ");}
            else {
               System.err.println("test");
                if(role.equals("Utilisateur_fournisseur") )
                   {
                       //System.out.println("succes");
                       if(!utilSer.test_Password(mdp.getText())){
                           alert_Box("Verifier", "votre mot de passe doit contenir au minimum 8 caractères\\n à savoir : au moins une lettre minuscule,une lettre majuscule et un chiffre");
                          }else{
                           int fournisseur_id=2;
                           util.setNom(Nom.getText());
                           util.setPrenom(prenom.getText());
                           util.setEmail(Email.getText());
                           util.setCin(CIN.getText());
                           util.setMdp(utilSer.crypter_password(mdp.getText()));
                           util.setNum_tel(tel.getText());
                           util.setAdresse(adresse.getText());
                           util.setDate_naissance(Date.valueOf(datebirth.getValue()));
                           util.setId_rest((int) choicebox_resto.getSelectionModel().getSelectedItem().getId_rest());
                           util.setId_fournisseur(fournisseur_id);
                           util.setId_role((int) choicebox_type_user.getSelectionModel().getSelectedItem().getId_role());
                         

                           try {
                               utilSer.ajouterFournisseur(util);
                               sn.Notification("Felicitation", "Vous avez creez un compte utilisateur fournisseur");
                               mainpage();
                           } catch (SQLException e) {
                               JOptionPane.showMessageDialog(null, e);
                           }
                          // }else{wrongSignup.setText("Choisir votre fournisseur! "); }
                   } }
               else if(role.equals("Employée")){
                   if(poste_emp.getText().isEmpty())
                       {wrongSignup.setText("Tapez votre poste dans le restaurant! ");}
                        
                   else{
                        if(!utilSer.test_Password(mdp.getText())){
                           wrongSignup.setText("votre mot de passe doit contenir au minimum 8 caractères\\n à savoir : au moins une lettre minuscule,une lettre majuscule et un chiffre");
                          }else{
                            util.setNom(Nom.getText());
                            util.setPrenom(prenom.getText());
                            util.setEmail(Email.getText());
                            util.setCin(CIN.getText());
                            util.setMdp(utilSer.crypter_password(mdp.getText()));
                            util.setNum_tel(tel.getText());
                            util.setAdresse(adresse.getText());
                            util.setDate_naissance(Date.valueOf(datebirth.getValue()));
                            util.setPoste_employe(poste_emp.getText());
                            util.setStatus_compte("non_verifer");

                            util.setId_rest((int) choicebox_resto.getSelectionModel().getSelectedItem().getId_rest());

                            util.setId_role((int) choicebox_type_user.getSelectionModel().getSelectedItem().getId_role());

                            try {
                                utilSer.ajouterEmploye(util);
                                sn.Notification("Felicitation", "Vous avez creez un compte employer");
                                mainpage();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                         } 
               }}
               else if (role.equals("Gérant")){
                   sn.Notification("Erreur", "gerant il doit être ajouter manuellement");
                       mainpage();
               }
               else if (role.equals("admin")){
                    sn.Notification("Erreur", "tu peut pas être l'administrateur de cette application");
                    mainpage();
               //btn_sign_up.setVisible(false);
               }

           }
       }
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
     public void mainpage() throws IOException{
       btn_sign_up.getScene().getWindow().hide();
        Parent root= FXMLLoader.load(getClass().getResource("../GUI/LoginRestoflex.fxml"));
        Stage stage =new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
     }
      public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
    
    
}
