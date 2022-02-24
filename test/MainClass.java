/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.*;
import java.util.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import service.*;
/**
 *
 * @author boura
 */
public class MainClass {
    public static void main(String[] args) throws SQLException, ParseException {
      
        
       //TEST CRUD ROLE
      /*  RoleService rs=new RoleService();
        role r1= new role("Gérant");
        role r2= new role("Employée");
        role r3= new role("Utilisateur_fournisseur");
        rs.ajouter(r1);
        rs.modifier(2, r2);
        rs.ajouter(r3);

        //test CRUD UTILISATEUR
       System.out.println(rs.afficher());*/
      UtilisateurService ut=new UtilisateurService();
     //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    //Date d1 = df.parse("1998-05-02"); 
  Calendar C = new GregorianCalendar(1998,05-1,02);
      Date  birthday=C.getTime();
    java.sql.Date date= new java.sql.Date(birthday.getTime());
       
        //System.out.println(birthday);
      
    //  utilisateur u1=new utilisateur("amir", "bouras", "07477323", "amirbouras", "amir@gmail.com", "95379411", date, "hedi noira", 1200, 15, "cuisine");
  //   utilisateur u2=new utilisateur("anis", "benhmida", "00258421", "anisbenhmida", "anis@gmail.com", "55376927", date, "zahra", 1500, 25, "terrase");
    // ut.ajouterEmploye(u1);
   // ut.supprimer(1);
        System.out.println(ut.afficher());
    // ut.modifier(3, u2);
     
    }
}
