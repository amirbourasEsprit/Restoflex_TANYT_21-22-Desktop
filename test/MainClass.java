/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Conge;
import entities.type_conge;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.CongeService;
import service.TypeCongeService;

   
public class MainClass {
    public static void main(String[] args) throws SQLException {
      /* pour le congé 
       CongeService cong =new CongeService();
       Calendar cal1= new GregorianCalendar(2022,02,20);
       java.sql.Date date_deb = new java.sql.Date(cal1.getTime().getTime());
       Calendar cal2= new GregorianCalendar(2022,02,24);
       java.sql.Date date_fin = new java.sql.Date(cal2.getTime().getTime());
       Conge c = new Conge(date_deb,date_fin,28,"refuse",1,3);
       CongeService cong =new CongeService();
       cong.ajouterConge(c);  
       cong.modifierConge(4,c); 
       cong.supprimerConge(3);
       System.out.println(cong.afficherConge()); 
      TypeCongeService Tcong =new TypeCongeService();
      type_conge c = new type_conge(2,"sans solde");
      Tcong.ajouterTypeConge(c);
      System.out.println(Tcong.afficherTypeConge());
      Tcong.supprimerTypeConge(2);
      Tcong.modifierTypeConge(0,c); 
      */
    }
}