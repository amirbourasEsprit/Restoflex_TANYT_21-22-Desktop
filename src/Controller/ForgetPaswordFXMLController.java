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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author boura
 */
public class ForgetPaswordFXMLController implements Initializable {

    @FXML
    private AnchorPane forget_pwd;
    @FXML
    private Label email_label;
    @FXML
    private Button envoyer_mail_btn;
    @FXML
    private Button annuler_btn;
    @FXML
    private TextField email_text;
    @FXML
    private TextField mdp_text;
    @FXML
    private TextField cmdp_text;
    @FXML
    private Label mdp_label;
    @FXML
    private Label cmdp_label;
    @FXML
    private Button mdp_btn;
 boolean verification =false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visibility(false);
    }    
    //pop up
    private String affichage_box_code(String code_random) throws InterruptedException {
        int i = 0;
        boolean test = false;

        while (i <= 2 && !test) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Code Verification");
            dialog.setContentText("Un code de verification est envoyé consulter votre boite mail!");
            String code_saisie;

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {

                code_saisie = result.get();

                if (code_saisie.equals(code_random)) {
                   
                    return "true";

                } else if (!code_saisie.equals(code_random) && i < 2) {
                    if (i == 0) {
                        test = true;
                        test = alert_Box_verif_code("Code Incorrect", "Il vous reste deux tentatives");

                    } else if (i == 1) {
                        test = true;

                        test = alert_Box_verif_code("Code Incorrect", "Il vous reste une seule  tentative");
                    }

                    i++;

                } else {

                    return "cancel";
                }

            } else {
                return "close";
            }
        }
        return "cancel";

    }

    @FXML
    private void envoyer_mail_btn_action(ActionEvent event) throws InterruptedException, IOException {
        UtilisateurService su = new UtilisateurService();

        SendMail sm = new SendMail();
        String code_random = "";

      
        String email = email_text.getText();
        String resultat = "";

        utilisateur a = new utilisateur();
        if (email.isEmpty()) {
            alert_Box("Verifier Vos Données", "Veuillez remplir tous les champs!");

        } else if (su.validateMail(email)==null) {
            alert_Box("Verifier Verifier Vos Données", "les informations que vous avez saisies sont incorrectes. vérifiez vos informations et réessayez");

        } else { //email and id sonts correct
            code_random = code_random();

            sm.envoyerMail(email, "Mail Pour Verification", "Voice Votre Code de Verification :" + code_random);
            resultat = affichage_box_code(code_random);

            if ("true".equals(resultat)) {
                
                information_Box("Code Correct", "Votre Code est Correct Veuillez modifier votre mot de passe");
               when_code_correct();
                this.verification = true;

            } else if (!"close".equals(resultat)) {
                alert_Box("Code Incorrect", "Vous avez atteint toutes vos tentaives,Ressayez Plus Tard");

                this.verification = false;
                when_code_incorrect_3times();
            }

        }
    }
    public boolean alert_Box_verif_code(String title, String message) throws InterruptedException {

        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.WARNING;

        Alert alert = new Alert(Type, "");
        alert.setTitle(title);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
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
    
       private String code_random() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();

    }
       public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
       public void when_code_correct(){
           email_text.setVisible(false);
           email_label.setVisible(false);
           envoyer_mail_btn.setVisible(false);
           visibility(true);
       }
       
        public void when_code_incorrect_3times() throws IOException{
        forget_pwd.getScene().getWindow().hide();
        Parent root= FXMLLoader.load(getClass().getResource("../GUI/LoginRestoflex.fxml"));
        Stage stage =new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        }
        
        public void visibility(boolean test){
        mdp_label.setVisible(test);
        mdp_btn.setVisible(test);
       // envoyer_mail_btn.setVisible(test);
        cmdp_label.setVisible(test);
        cmdp_text.setVisible(test);
        mdp_text.setVisible(test);
        
        
        }

    @FXML
    private void annuler_btn_action(ActionEvent event) throws IOException {
        forget_pwd.getScene().getWindow().hide();
        Parent root= FXMLLoader.load(getClass().getResource("../GUI/LoginRestoflex.fxml"));
        Stage stage =new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    private void mdp_btn_action(ActionEvent event) throws SQLException, IOException {
     String mdp = mdp_text.getText();
     String cmdp=cmdp_text.getText();
     UtilisateurService us=new UtilisateurService();
     
     if(!us.test_Password(mdp)){
         alert_Box("Verifier", "votre mot de passe doit contenir au minimum 8 caractères\\n à savoir : au moins une lettre minuscule,une lettre majuscule et un chiffre");
   
     }else if(!mdp.equals(cmdp)) {
         alert_Box("Attention", "Le mot de passe et le confirmer mot de passe doivent être identiquent ");
     }
     else{
         utilisateur.current_user.setMdp(us.crypter_password(mdp));
         us.modifier(utilisateur.current_user.getId_utilisateur(), utilisateur.current_user);
         information_Box("Succés", "Votre mot de passe est modifier");
                forget_pwd.getScene().getWindow().hide();
        Parent root= FXMLLoader.load(getClass().getResource("../GUI/LoginRestoflex.fxml"));
        Stage stage =new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

     }
    }
}
