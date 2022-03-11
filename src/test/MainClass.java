/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.facture;
import entities.fournisseur_restaurant;
import entities.restaurant;
import java.sql.SQLException;
import service.FactureService;
import service.FrnsRestService;
import service.RestaurantService;
import util.MyConnection;

/**
 *
 * @author boura
 */
public class MainClass {
    public static void main(String[] args) throws SQLException {
        
        
        FactureService fs = new FactureService();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        facture f1 = new facture(sqlDate, 1700, "non payée", 1, 3);
        //fs.ajouterFacture(f1);
       // fs.modifierFacture(4, f1);
       // fs.supprimerFacture(5);
       // System.out.println(fs.afficherFacture()); 
       // fs.rechercherFournisseur(3);
       // fs.afficherFactureRest(3);
       // fs.calculTotal(2);
       //fs.nbFacturePayee();
      // fs.nbFactureNPayee();
      //fs.afficherCommande(3, 1);
   
        
       // FrnsRestService fr = new FrnsRestService();
       // fournisseur_restaurant frt = new fournisseur_restaurant(3, 1);
       //fr.ajouterFrnsRest(frt);
       //fr.modifierFrnsRest(2, frt);
       //fr.supprimerFrnsRest(2);
     //  fr.afficherFrnsRest();
          
       /* RestaurantService rs = new RestaurantService();
        restaurant r = new restaurant("716", "salon de thé", "lac 2", "716@gmail.com", "71548236");
        rs.ajouterRestaurant(r);
        rs.afficherRestaurant();
        rs.modifierRestaurant(2, r);
        rs.supprimerRestaurant(2);*/
        
       
       
    }
}
