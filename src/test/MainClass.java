/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.restaurant;
import java.sql.SQLException;
import service.RestaurantService;

/**
 *
 * @author boura
 */
public class MainClass {
    public static void main(String[] args) throws SQLException {
        RestaurantService rs =new RestaurantService();
        restaurant r1= new restaurant("baila", "pizzaria", "ariana", "bail@gmail.com", "95379411");
        restaurant r2= new restaurant("cozzyfood", "pizzaria", "ariana", "cozyfood@gmail.com", "95379411");

        //rs.ajouter(r1);
       // rs.modifier(1, r2);
      // rs.supprimer(1);
        System.out.println(rs.afficher()); 
       
    }
}
