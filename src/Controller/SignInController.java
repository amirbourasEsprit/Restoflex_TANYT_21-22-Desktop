/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.SendMail;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class SignInController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button connecter;
    private Label wrongLogin;
    @FXML
    private Label wrongLogIn;
    @FXML
    private Label mdp_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }
    
    private void checkLogin() throws IOException{
            UtilisateurService us=new UtilisateurService();

      String currentEmail=email.getText();
      String currentPassword= us.crypter_password(mdp.getText());
        
       boolean t= us.validateLogin(currentEmail,currentPassword);
      //  System.out.println(t);
       // System.out.println(currentEmail);
       // System.out.println(currentPassword);
       // System.out.println( us.crypter_password(currentPassword));
        
       if( t == true ){
        //wrongLogIn.setText("Success");
        String user_session_role_id=String.valueOf(utilisateur.current_user.getId_role());
        
        if(utilisateur.current_user.getStatus_compte().equals("verifier"))
                {
               
        
          //redirection vers le dashbord
          switch (user_session_role_id) {
              case "1":
                  {
                      System.out.println("Gérant");
                      int user_session_id=utilisateur.current_user.getId_utilisateur();
                      //System.out.println(user_session_id);
                      //System.out.println(utilisateur.current_user.getId_role());
                      connecter.getScene().getWindow().hide();
                      Parent root= FXMLLoader.load(getClass().getResource("../GUI/Dashboard_GerantFXML.fxml"));
                      Stage stage =new Stage();
                      Scene scene = new Scene(root);
                      scene.setFill(Color.TRANSPARENT);
                      stage.setScene(scene);
                      stage.initStyle(StageStyle.TRANSPARENT);
                      stage.show();
                      break;
                  }
              case "2":
                  {
                      System.out.println("Employée");
                      int user_session_id=utilisateur.current_user.getId_utilisateur();
                      System.out.println(user_session_id);
                      //change scene
                      /* FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Dashboard_GerantFXML.fxml"));
                      Parent root = loader.load();
                      email.getScene().setRoot(root);*/
                      connecter.getScene().getWindow().hide();
                      Parent root= FXMLLoader.load(getClass().getResource("../GUI/Dashboard_EmploFXML.fxml"));
                      Stage stage =new Stage();
                      Scene scene = new Scene(root);
                      scene.setFill(Color.TRANSPARENT);
                      stage.setScene(scene);
                      stage.initStyle(StageStyle.TRANSPARENT);
                      stage.show();
                      break;
                  }
              case "3":
                  {
                      System.out.println("Utilisateur_Fournisseur");
                      int user_session_id=utilisateur.current_user.getId_utilisateur();
                      System.out.println(user_session_id);
                      System.out.println(user_session_role_id);
                      System.out.println(utilisateur.current_user.getId_fournisseur());
                     
                      //change scene
                      /* FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Dashboard_GerantFXML.fxml"));
                      Parent root = loader.load();
                      email.getScene().setRoot(root);*/
                      connecter.getScene().getWindow().hide();
                      Parent root= FXMLLoader.load(getClass().getResource("../GUI/Dashboard_FournFXML.fxml"));
                      Stage stage =new Stage();
                      Scene scene = new Scene(root);
                      scene.setFill(Color.TRANSPARENT);
                      stage.setScene(scene);
                      stage.initStyle(StageStyle.TRANSPARENT);
                      stage.show();
                      break;
                  }
                   case "4":
                  {
                      System.out.println("admin");
                      int user_session_id=utilisateur.current_user.getId_utilisateur();
                  //    System.out.println(user_session_id);
                     // System.out.println(user_session_role_id);
                    //  System.out.println(utilisateur.current_user.getId_fournisseur());
                     
                      //change scene
                      /* FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Dashboard_GerantFXML.fxml"));
                      Parent root = loader.load();
                      email.getScene().setRoot(root);*/
                      connecter.getScene().getWindow().hide();
                      Parent root= FXMLLoader.load(getClass().getResource("../GUI/Dashboard_Admin_restoflex.fxml"));
                      Stage stage =new Stage();
                      Scene scene = new Scene(root);
                      scene.setFill(Color.TRANSPARENT);
                      stage.setScene(scene);
                      stage.initStyle(StageStyle.TRANSPARENT);
                      stage.show();
                      break;
                  }
                  
              default:
                  break;
          } }
        else{
            alert_Box("Attention", "Votre compte n'est pas encore verifié");
        }
        
        }
       
        else if(currentEmail.isEmpty()){
         wrongLogIn.setText("Remplir le champs Email!");
    }   
        else if (currentPassword.isEmpty()){
        wrongLogIn.setText("Remplir le champs Mot de Passe!");
        }
        
        else {
        wrongLogIn.setText("Email ou Mot de Passe Incorrect!");
        }
    }

    @FXML
    private void mdp_oublie(MouseEvent event) throws IOException {
        SendMail se=new SendMail();
        //se.envoyerMail("bourasamir68@gmail.com", "rese ", "test");
         Parent root= FXMLLoader.load(getClass().getResource("../GUI/forgetPaswordFXML.fxml"));
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
