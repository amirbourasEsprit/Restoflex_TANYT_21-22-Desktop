/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.reclamation;
import entities.type_reclamation;
import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import service.ReclamationService;
import service.type_reclamationService;
/**
 *
 * @author boura
 */
public class MainClass {
    
    //private static Object date1;
    public static void main(String[] args) throws SQLException, ParseException {
    //ReclamationService RS= new ReclamationService();
   //java.util.Date date=new java.util.Date();
   //java.sql.Date sqlDate=new java.sql.Date(date.getTime());
    //reclamation r1=new reclamation("Tas", "dsjhdsoifzeh", "traitée",sqlDate,1,3);
      //RS.ajouterReclamation(r1);
     //RS.supprimerReclamation(4);
    // RS.modifierReclamation(4, r1);
   //System.out.println(RS.afficherReclamation());
      
   
    type_reclamationService TRS=new type_reclamationService();
    type_reclamation tr1=new type_reclamation("produit top");
  // TRS.ajouterTypeReclamation(tr1);
  TRS.supprimerTypeReclamation(2);
 //TRS.modifierTypeReclamation(3, tr1);
 //System.out.println(TRS.afficherTypeReclamation());
    }
}
           
      









 
       
  
