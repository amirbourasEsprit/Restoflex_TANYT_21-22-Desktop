/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.fournisseur;
import java.sql.SQLException;
import service.FournisseurService;
import entities.stock;
import service.StockService;
/**
 *
 * @author boura
 */
public class MainClass {
    public static void main(String[] args) throws SQLException {
        FournisseurService four =new FournisseurService();
        fournisseur f1= new fournisseur("abir","restau","tunis","a","a","a","a ");
      fournisseur f2= new fournisseur("cozzyfood", "pizzaria", "ariana", "cozyfood@gmail.com", "95379411","a","a");

      four.ajouterFournisseur(f1);
       four.ajouterFournisseur(f2);
       // four.modifier(1, f2); 
      //four.supprimerFournisseur(6);ss
     // System.out.println(four.afficherFournisseur()); 
      
     // StockService st =new StockService(); 
     // stock s1= new stock("abir",1.5F,1.5F,8);
     // st.ajouterStock(s1);
 //System.out.println(st.afficherStock()); 
     
       
    }
}
