/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Commande;
import entities.fournisseur;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class AjoutercommandeController implements Initializable {

    private TextField idx;
    @FXML
    private TextField statut;
    

    private TextField datecmd;
    @FXML
    private TableView<Commande> tableview;
    @FXML
    private TableColumn<Commande, Integer> ida;
    @FXML
    private TableColumn<Commande, String> statutcommande;
    @FXML
    private TableColumn<Commande, String> date_cmd;
    @FXML
    private TableColumn<Commande, String> date_liv;
    @FXML
    private TableColumn<Commande, Float> qnt;
    @FXML
    private TableColumn<Commande, String> id_usercomm;
   
    @FXML
    private Button supprimer;
    @FXML
    private Button ajou;
    CommandeCRUD sp= new CommandeCRUD();
    int idc=-1;
    @FXML
    private DatePicker datelivraison;
    @FXML
    private Label prod;
    @FXML
    private Label user;
    @FXML
    private ComboBox<fournisseur> fournL;
    @FXML
    private TableColumn<Commande, String> fourn;
    @FXML
    private TableColumn<Commande, String> prods;
    @FXML
    private Label quant;
    @FXML
    private TextField rech;
    @FXML
    private Slider slider;
    @FXML
    private TextField pmin;
    @FXML
    private TextField pmax;
private int p=0;
private String ch="";
private int current_user_rest=utilisateur.current_user.getId_rest();
    @FXML
    private Label statutlabel;
    @FXML
    private Label datelivraisonlabel;
    @FXML
    private Label fournLlabes;
    @FXML
    private Button confirmerCommande;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List commande =sp.getCommandes(ch,p,current_user_rest);
        ObservableList list =FXCollections.observableArrayList(commande);
        tableview.setItems(list);
        ida.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        statutcommande.setCellValueFactory(new PropertyValueFactory<>("statut"));
        date_cmd.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
        date_liv.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        qnt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        id_usercomm.setCellValueFactory(new PropertyValueFactory<>("nomUtil"));
        prods.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        fourn.setCellValueFactory(new PropertyValueFactory<>("nomFour"));
        fournisseur f=new fournisseur();
        f.setId_fournisseur(-1);
        f.setNom_fournisseur("None");
        fournL.setValue(f);
        fournL.setItems(sp.getfournisseur());
        datelivraison.setValue(LocalDate.now());
        pmax.setText(String.valueOf(sp.getMax()));
        pmax.setDisable(true);
        pmin.setDisable(true);
       slider.valueProperty().addListener(new ChangeListener<Number>(){
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
              p=(int) slider.getValue();
              pmin.setText(String.valueOf((int) slider.getValue()));
              tableview.setItems(sp.getCommandes(ch,p,current_user_rest));
           }
       });
       slider.setMax(sp.getMax());
     //   showBox();
       init();
       ajou.setVisible(false);
        visibility(false);
       statutlabel.setVisible(false);
       statut.setVisible(false);


    }
    

    private void selectdl(MouseEvent event) {

        /*DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd ");
        String strDate = dateFormat.format(currentDate);
        Commande evt = tableview.getSelectionModel().getSelectedItem();
        statut.setText(evt.getStatut());
        datecmd.setText(evt.getDate_cmd());
        datelivraison.setDayCellFactory(date_liv ,evt.getDate_livraison());
        String c = Float.toString(evt.getQuantite());

        String a = Integer.toString((int) evt.getId_cmd());
        idx.setText(a);
        System.out.println(Integer.parseInt(idx.getText()));
        String b = Integer.toString((int) evt.getId_utilisateur());
        idutilisateur.setText(b);*/
    }

    public void showBox() {
        Commande r = new Commande();

        CommandeCRUD sp = new CommandeCRUD();
        List Commande = sp.getCommandes(ch,p,current_user_rest);
        ObservableList list = FXCollections.observableArrayList(Commande);
        tableview.setItems(list);
        ida.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        statutcommande.setCellValueFactory(new PropertyValueFactory<>("statut"));
        date_cmd.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
        date_liv.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        qnt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        id_usercomm.setCellValueFactory(new PropertyValueFactory<>("nomUtil"));
        prods.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        fourn.setCellValueFactory(new PropertyValueFactory<>("nomFour"));
    }

   /* @FXML
    private void add(ActionEvent event) throws SQLException {
        java.sql.Date currentDate=new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Commande r = new Commande();
        CommandeCRUD su = new CommandeCRUD();
//            int i = Integer.parseInt(tfID.getText());
//           int j = Integer.parseInt(idutilisateur.getText());
        Float j = Float.parseFloat(quantité.getText());
        long id_util=1;
        Commande u = new Commande(statut.getText(), currentDate, Date.valueOf(datelivraison.getValue()), j,id_util);
        su.ajouterCommande(u);
        JOptionPane.showMessageDialog(null, "Vous avez ajouter une commande");
     /  List Commande = su.afficher();
        ObservableList list = FXCollections.observableArrayList(Commande);
        tableview.setItems(list);
        ida.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        statutcommande.setCellValueFactory(new PropertyValueFactory<>("statut"));
        date_cmd.setCellValueFactory(new PropertyValueFactory<>("date_cmd"));
        date_liv.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        qnt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     //   id_usercomm.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));

    }*/

   


    @FXML
    private void supp(ActionEvent event) {
       ajou.setDisable(false);



       Commande s =tableview.getSelectionModel().getSelectedItem();


        try{

           sp.supprimerCommande(s.getId_cmd());
           init();

            JOptionPane.showMessageDialog(null,"la commande a été supprimer avec succes");
            //sp.afficher();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        showBox();
    }

    @FXML
    private void select(MouseEvent event) {
        datelivraison.setDisable(true);
        visibility(true);
                ajou.setDisable(true);

        Commande c=new Commande();
        c.setId_cmd(-1);
          c = tableview.getSelectionModel().getSelectedItem();
          if(c.getId_cmd()!=-1){
              fournL.setValue(fournL.getSelectionModel().getSelectedItem());
              Date date_livra= c.getDate_livraison();
              LocalDate date_livra_loc= date_livra.toLocalDate();
              
              
          if("en cours".equals(c.getStatut())){
              confirmerCommande.setVisible(true);
          }
          else if("Confirmer".equals(c.getStatut()))
          {
          confirmerCommande.setVisible(false);
          }
          else if("En Livraison".equals(c.getStatut()))
          {
              confirmerCommande.setVisible(false);
          }
          
         
             
           supprimer.setDisable(false);
         
           statut.setText(c.getStatut());
         //  datecmd.setDisable(true);
          datelivraison.setValue(date_livra_loc);
          // idutilisateur.setDisable(true);
           idc=c.getId_cmd();
    }
    }
    private void init(){
       supprimer.setDisable(true);
  
       statut.setText("");


    }
    public void visibility(boolean test){
    
    datelivraisonlabel.setVisible(test);
    datelivraison.setVisible(test);
    fournL.setVisible(test);
    fournLlabes.setVisible(test);
  
    supprimer.setVisible(test);
//    annuler.setVisible(test);
    confirmerCommande.setVisible(test);
    }
private boolean controleSaisie(){
    int test=0;
    String text="";
    
    if(datelivraison.getValue().compareTo(LocalDate.now())<0){
        text="date commande doit etre superieur a date d aujourd hui \n";
        test=1;
        }
    
    if(statut.getText().equals("")){
      test=2;
      statut.setFocusTraversable(true);
      text+=" le champ statut est obligatoire \n";
    }   
    if(fournL.getValue().getId_fournisseur()==-1){
      test=3;
      text+=" il faut choisir un fournisseur \n";
    }  
   if (test==0)
       return true;
   else{JOptionPane.showMessageDialog(null,text);
       return false;
       }
}
 
      
/*public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }*/

    void showInformation(String string,int Name,String q) {
        
        prod.setText(string);
        user.setText(String.valueOf(Name));
        quant.setText(q);
    }

    @FXML
    private void chercher(ActionEvent event) {
        ch=rech.getText();
        tableview.setItems(sp.getCommandes(ch,p,current_user_rest));
    }

    @FXML
    private void confirmerCommande(ActionEvent event) {
        Commande c=tableview.getSelectionModel().getSelectedItem();
        CommandeCRUD cr=new CommandeCRUD();
        c.setStatut("Confirmer");
        c.setDate_cmd(c.getDate_livraison());
        c.setId_four((int)fournL.getSelectionModel().getSelectedItem().getId_fournisseur());
        cr.modifierCommande(c, idc);
         List Commande = sp.getCommandes(ch,p,current_user_rest);
        ObservableList list = FXCollections.observableArrayList(Commande);
        tableview.setItems(list);
        init();
        
        
    }

   @FXML
    private void add_cmd(ActionEvent event) throws SQLException, Exception {
         java.sql.Date currentDate=new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Commande r = new Commande();
        CommandeCRUD su = new CommandeCRUD();
        Float j = Float.parseFloat(quant.getText());
        long id_util=Integer.parseInt(user.getText());
        long id_prod=Integer.parseInt(prod.getText());
        if(controleSaisie()==true){
        Commande u = new Commande(statut.getText(), currentDate, Date.valueOf(datelivraison.getValue()), j,id_util,id_prod,fournL.getValue().getId_fournisseur());
        su.ajouterCommande(u);
        su.modifierProd(u.getQuantite(), u.getId_produit());
       // mailUtil.sendMail("aniisbh123@gmail.com", "ilyes.bensaid@esprit.tn", "bacmath2017", "notification", "Votre commande est ajoute", null);
        JOptionPane.showMessageDialog(null, "Vous avez ajouter une commande");
        showBox();}
    }





}
