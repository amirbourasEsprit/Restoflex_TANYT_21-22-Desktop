/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Commande;
import entities.produit_restaurant;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.CategorieCRUD;
import service.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class CategorieController implements Initializable {

    @FXML
    private ImageView imageviande;
    @FXML
    private Button consulterviande;
    @FXML
    private ImageView imagefruit;
    @FXML
    private Button consulterfruit;
    @FXML
    private ImageView imagenettoyage;
    @FXML
    private Button consulternettoyage;
    
    @FXML
    private ComboBox<produit_restaurant> produitViande;
    @FXML
    private ComboBox<produit_restaurant> produitFruit;
    @FXML
    private ComboBox<produit_restaurant> produitN;
    @FXML
    private TextField kgN;
    @FXML
    private TextField kgV;
    @FXML
    private TextField kgF;
    @FXML
    private Label iduser;
    int idu;
    @FXML
    private DatePicker datelivraison;
    @FXML
    private Button ajou;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    show();
         
    }
    private void show ()
    {
        idu=Integer.parseInt(iduser.getText());
       // kgN.setDisable(true);
       // kgV.setDisable(true);
       // kgF.setDisable(true);
         CategorieCRUD cc=new CategorieCRUD();
         produit_restaurant p=new produit_restaurant();
         p.setId_pdrest(-1);
         p.setNom_pdt("None");
         p.setQuantit_pdt(0);
         produitViande.setValue(p);
         produitFruit.setValue(p);
         produitN.setValue(p);
         produitViande.setItems(cc.getProducts("Viande", idu));
         produitFruit.setItems(cc.getProducts("Fruit", idu));
         produitN.setItems(cc.getProducts("Nettoyage", idu));
         consulternettoyage.setVisible(false);
         consulterfruit.setVisible(false);
         consulterviande.setVisible(false);
         System.out.println(kgV.getText());
         System.out.println(produitN.getValue());
    }
private boolean chaineNum(String ch){
    return ch.matches("[+-]?\\d*(\\.\\d+)?");
}    
    @FXML
    public void changeV(ActionEvent a){
        kgN.setDisable(true);
        kgV.setDisable(false);
        kgF.setDisable(true);
       
       
   }
    @FXML
    private void viande(ActionEvent event) throws IOException {
    /*    if((produitViande.getValue().getId_pdrest()!=-1)&&(chaineNum(kgV.getText())==true)&&(kgV.getText().isEmpty()==false))
            {
                float p=Integer.parseInt(kgV.getText());
                if((p<=produitViande.getValue().getQuantit_pdt())&&(p>0))
        {FXMLLoader fxml=new FXMLLoader(getClass().getResource("../gui/ajoutercommande.fxml"));
        Parent root=fxml.load();
        AjoutercommandeController hc=fxml.getController();
        hc.showInformation(String.valueOf(produitViande.getValue().getId_pdrest()),idu,kgV.getText());
         Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();}
            else
                JOptionPane.showMessageDialog(null, "Quantite doit etre un chiffre inferieur ou egale a "+produitViande.getValue().getQuantit_pdt()); 
            }
        else 
            JOptionPane.showMessageDialog(null, "Aucun produit n est choisi");*/
    }

    @FXML
    private void fruit(ActionEvent event) throws IOException {
        /*if((produitFruit.getValue().getId_pdrest()!=-1)&&(chaineNum(kgF.getText())==true))
            {if(Integer.parseInt(kgF.getText())<=produitFruit.getValue().getQuantit_pdt())
        {FXMLLoader fxml=new FXMLLoader(getClass().getResource("../gui/ajoutercommande.fxml"));
        Parent root=fxml.load();
        AjoutercommandeController hc=fxml.getController();
        hc.showInformation(String.valueOf(produitFruit.getValue().getId_pdrest()),idu,kgF.getText());
         Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();}
            else
                JOptionPane.showMessageDialog(null, "Quantite doit etre un chiffre inferieur ou egale a "+produitFruit.getValue().getQuantit_pdt()); 
            }
        else 
            JOptionPane.showMessageDialog(null, "Aucun produit n est choisi");*/
    }

    @FXML
    private void nettoyage(ActionEvent event) throws IOException {
       /* if((produitN.getValue().getId_pdrest()!=-1)&&(chaineNum(kgN.getText())==true))
            {if(Integer.parseInt(kgN.getText())<=produitN.getValue().getQuantit_pdt())
        {FXMLLoader fxml=new FXMLLoader(getClass().getResource("../gui/ajoutercommande.fxml"));
        Parent root=fxml.load();
        AjoutercommandeController hc=fxml.getController();
        hc.showInformation(String.valueOf(produitN.getValue().getId_pdrest()),idu,kgN.getText());
         Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();}
            else
                JOptionPane.showMessageDialog(null, "Quantite doit etre un chiffre inferieur ou egale a "+produitN.getValue().getQuantit_pdt()); 
            }
        else 
            JOptionPane.showMessageDialog(null, "Aucun produit n est choisi");*/
    }


    @FXML
    private void changeF(ActionEvent event) throws IOException {
        kgN.setDisable(true);
        kgV.setDisable(true);
        kgF.setDisable(false);
        
    }

    @FXML
    private void changeN(ActionEvent event) throws IOException {
        kgN.setDisable(false);
        kgV.setDisable(true);
        kgF.setDisable(true);
        System.out.println(kgV.getText());
    }
private long getid_produit (){
  if(produitViande.getValue().getId_pdrest()!=-1)
  {
    return produitViande.getValue().getId_pdrest();
  }
  else if(produitN.getValue().getId_pdrest()!=-1)
  {
      return produitN.getValue().getId_pdrest();
  }
  else if(produitFruit.getValue().getId_pdrest()!=-1) {
  return produitFruit.getValue().getId_pdrest();}
  else  {return -1;}
}
private String getquantite(){
  
    if(!kgV.getText().isEmpty())
  {
    return kgV.getText();
  }
  else if(!kgN.getText().isEmpty())
   {
      return kgN.getText();
  }
    else if(!kgF.getText().isEmpty()) 
    {
        return kgF.getText();
    }
    else{
        return null;
    }    
} 
    @FXML
    private void add_cmd(ActionEvent event) throws Exception {
        
        System.out.println(getquantite());
         java.sql.Date currentDate=new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Commande r = new Commande();
        int current_employee_id=3;
        CommandeCRUD su = new CommandeCRUD();
        Float j = Float.parseFloat(getquantite());
        System.out.println(getid_produit());
        long id_prod=getid_produit();
        String statut ="En Cours";
        int id_fournisseur=1000;
        if(controleSaisie()==true){
        Commande u = new Commande(statut, currentDate, Date.valueOf(datelivraison.getValue()), j,current_employee_id,id_prod,id_fournisseur);
        su.ajouterCommande(u);
        su.modifierProd(u.getQuantite(), u.getId_produit());
        mailUtil.sendMail("aniisbh123@gmail.com", "ilyes.bensaid@esprit.tn", "bacmath2017", "notification", "Votre commande est ajoute", null);
        JOptionPane.showMessageDialog(null, "Vous avez ajouter une commande");
       
        }
        
        
        
    }
private boolean controleSaisie(){
    int test=0;
    String text="";
    
    if(datelivraison.getValue().compareTo(LocalDate.now())<0){
        text="date commande doit etre superieur à la date d\\aujourd hui \n";
        test=1;
        }
   if (test==0)
       return true;
   else{JOptionPane.showMessageDialog(null,text);
       return false;
       }
}
 
    
}
