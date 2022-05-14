/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.SendMailYosr;
import entities.Conge;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.CongeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Yosr Belaam
 */
public class Afficher_Conge_GerantController implements Initializable {

    @FXML
    private TableColumn<Conge, Date> Date_deb;
    @FXML
    private TableColumn<Conge, Date> Date_fin;
    @FXML
    private TableColumn<Conge, String> etat;
    @FXML
    private TableView<Conge> TableConge;
    @FXML
    private Button supprimer;
    @FXML
    private Label nom_cong;
    @FXML
    private DatePicker date_deb_cong;
    @FXML
    private DatePicker date_fin_cong;
    @FXML
    private ComboBox<String> type_emp_cong;
    @FXML
    private Label prenom_cong;
    @FXML
    private Button modifier;
    @FXML
    private TableColumn<Conge, String> nom_emp;
    @FXML
    private Button validemodif;
    @FXML
    private Label nomL;
    @FXML
    private Label preL;
    @FXML
    private Label typecL;
    @FXML
    private Label DDL;
    @FXML
    private Label DFL;
    @FXML
    private TextField id_emp_cong;
    @FXML
    private TextField id_cong;
    @FXML
    private Button Approuver;
    @FXML
    private Button Refuser;
    @FXML
    private TextField Rechercher;
    @FXML
    private Button Rechercherb;
    @FXML
    private Button Rafraichir;
    @FXML
    private TableColumn<Conge, String> prenom_emp;
    @FXML
    private TableColumn<Conge, String> email_emp;
    @FXML
    private Button Afficher_type_congB;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type_emp_cong.getItems().add("maladie");
        type_emp_cong.getItems().add("sans solde");
        champfalse();
        Load();
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        Conge cong = TableConge.getSelectionModel().getSelectedItem();
        //System.out.println(cong.getId_conge());
        CongeService c = new CongeService();
        c.supprimerConge(cong.getId_conge());
        Load();
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Congé supprimé avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }

    private void Load() {
        CongeService cong = new CongeService();
        //ObservableList<Conge> c =  cong.afficherConge2();
        int id_current_user = utilisateur.current_user.getId_rest();
        ObservableList<Conge> c = cong.afficherConge(id_current_user);//id user connecté bel intégration 
        Date_deb.setCellValueFactory(new PropertyValueFactory<Conge, Date>("date_deb"));
        Date_fin.setCellValueFactory(new PropertyValueFactory<Conge, Date>("date_fin"));
        etat.setCellValueFactory(new PropertyValueFactory<Conge, String>("etat"));
        nom_emp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Conge, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Conge, String> param) {
                return new SimpleObjectProperty(param.getValue().user.getNom());
            }
        });
        prenom_emp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Conge, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Conge, String> param) {
                return new SimpleObjectProperty(param.getValue().user.getPrenom());
            }
        });
        email_emp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Conge, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Conge, String> param) {
                return new SimpleObjectProperty(param.getValue().user.getEmail());
            }
        });

        TableConge.setItems(c);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Conge cong = TableConge.getSelectionModel().getSelectedItem();
        champtrue();
        fill(cong);
    }

    private void fill(Conge cong) throws SQLException {

        String query = "select * from conge where  id_conge='" + cong.getId_conge() + "'";
        Connection con;
        Statement ste;
        PreparedStatement prst;
        con = MyConnection.getInstance().getCnx();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        CongeService sc = new CongeService();

        utilisateur u = sc.getuser(cong.getId_utilisateur());
        while (rs.next()) {
            id_emp_cong.setText(rs.getString("id_utilisateur"));
            id_cong.setText(rs.getString("id_conge"));
            nom_cong.setText(u.getNom());
            prenom_cong.setText(u.getPrenom());
            (date_deb_cong.getEditor()).setText(rs.getString("date_deb"));
            (date_fin_cong.getEditor()).setText(rs.getString("date_fin"));

        }
        rs.close();

    }

    @FXML
    private void validemodif(ActionEvent event) throws SQLException {
        CongeService cong = new CongeService();
        Conge c = new Conge();
        c.setDate_deb(Date.valueOf(date_deb_cong.getValue()));
        c.setDate_fin(Date.valueOf(date_fin_cong.getValue()));
        c.setEtat("en cours");
        if (type_emp_cong.getSelectionModel().getSelectedItem().equals("maladie")) {
            c.setId_type_conge(1);
        } else {
            c.setId_type_conge(2);
        }
        c.setId_utilisateur(Integer.parseInt(id_emp_cong.getText()));
        c.setId_conge(Integer.parseInt(id_cong.getText()));
        c.setSolde_restant(30);
        cong.modifierConge(c);
        System.out.println(" modif done");
        c.toString();
        System.out.println(c.getId_conge());
        System.out.println(Date.valueOf(date_deb_cong.getValue()));
        champfalse();
        Load();
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Congé modifié avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));

    }

    private void champfalse() {
        id_emp_cong.setVisible(false);
        nomL.setVisible(false);
        preL.setVisible(false);
        id_cong.setVisible(false);
        typecL.setVisible(false);
        DDL.setVisible(false);
        DFL.setVisible(false);
        prenom_cong.setVisible(false);
        nom_cong.setVisible(false);
        type_emp_cong.setVisible(false);
        date_deb_cong.setVisible(false);
        date_fin_cong.setVisible(false);

        validemodif.setVisible(false);
    }

    private void champtrue() {
        nomL.setVisible(true);
        preL.setVisible(true);
        typecL.setVisible(true);
        DDL.setVisible(true);
        DFL.setVisible(true);
        prenom_cong.setVisible(true);
        nom_cong.setVisible(true);
        type_emp_cong.setVisible(true);
        date_deb_cong.setVisible(true);
        date_fin_cong.setVisible(true);
        validemodif.setVisible(true);
    }

    @FXML
    private void Approuver(ActionEvent event) throws SQLException {
        String current_user_mail = utilisateur.current_user.getEmail();
        Conge cong = TableConge.getSelectionModel().getSelectedItem();
        System.out.println(cong);
        System.out.println(cong.user);
        String destinataire = cong.user.getEmail();
        System.out.println(destinataire);
        CongeService sc = new CongeService();
        Date d1 = cong.getDate_deb();
        Date d2 = cong.getDate_fin();
        int diff = diffdate(d1, d2);
        System.out.println(diff);
        System.out.println(cong.user.getSolde_conge());
          if(diff<cong.user.getSolde_conge())
          {
                sc.approuverConge(cong);
                sc.soldeConge(cong.user.getId_utilisateur(),diff);
        
        //sc.approuverConge(cong);
        Load();

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Congé approuvé");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
        SendMailYosr sn = new SendMailYosr(); 
        String corps = cong.user.getNom()+" "+cong.user.getPrenom()+"\n"
                + "Vous m’avez demandé, par lettre recommandée , à bénéficier d’un congé du "+cong.getDate_deb()+" au "+cong.getDate_fin()+".\n"
                + "Par la présente, je vous informe que j’accepte votre demande.\n"
                + "Je vous confirme également vos dates d’absence ; du (précisez la date) au (précisez la date).\n"
                + "Je vous prie d’agréer mes respectueuses salutations. \n" + "Cordialement";
        sn.envoyerMail(destinataire, "Congé Approuvé", corps);
          } else {
              JOptionPane.showMessageDialog(null,"solde congé insuffisant");}
    }

    @FXML
    private void Refuser(ActionEvent event) throws SQLException {
        String current_user_mail = utilisateur.current_user.getEmail();
        Conge cong = TableConge.getSelectionModel().getSelectedItem();
        CongeService sc = new CongeService();
        String destinataire = cong.user.getEmail();
        System.out.println(destinataire);
        sc.refuserConge(cong);
        Load();
        //notifications
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Notifications");
        tray.setMessage("Congé refusé");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
        SendMailYosr sn = new SendMailYosr();
        String corps = cong.user.getNom()+" "+cong.user.getPrenom()+"\n"
                + "Vous m’avez demandé, par lettre recommandée , à bénéficier d’un congé du "+cong.getDate_deb()+" au "+cong.getDate_fin()+".\n"
                + "Par la présente, je vous informe que je refuse votre demande.\n"
                + "Je vous prie d’agréer mes respectueuses salutations. \n" + "Cordialement";
        sn.envoyerMail(destinataire, "Congé Refusé", corps);
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        CongeService cong = new CongeService();
        ObservableList<Conge> c = cong.RechercherConge(Rechercher.getText());

        //nom_emp.setCellFactory(new PropertyValueFactory<utilsateur, String>("nom"));
        Date_deb.setCellValueFactory(new PropertyValueFactory<Conge, Date>("date_deb"));
        Date_fin.setCellValueFactory(new PropertyValueFactory<Conge, Date>("date_fin"));
        etat.setCellValueFactory(new PropertyValueFactory<Conge, String>("etat"));
        nom_emp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Conge, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Conge, String> param) {
                return new SimpleObjectProperty(param.getValue().user.getNom());
            }
        });

        TableConge.setItems(c);
    }

    @FXML
    private void Rafraichir(ActionEvent event) {
        Load();
    }

    private int diffdate(Date d1, Date d2) {

        long diff = d2.getTime() - d1.getTime();
        int res = (int) (diff / (1000 * 60 * 60 * 24));
        System.out.println("Nombre de jours entre les deux dates est: " + res);
        return res;

    }

    /*private void Ajouter_Type(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("../gui/Ajout_Type_CongeFXML.fxml"));
        Parent root = loader.load();
        pane.getChildren().add(root);
    }*/

    @FXML
    private void Afficher_Type_Cong(ActionEvent event) throws IOException {
         FXMLLoader loader =new FXMLLoader(getClass().getResource("../GUI/Afficher_Type_CongeFXML.fxml"));
         Parent root = loader.load();
         pane.getChildren().add(root);
    }

}
