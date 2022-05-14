/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.I_RestaurantService;
import entities.restaurant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyConnection;

/**
 *
 * @author boura
 */
public class RestaurantService implements I_RestaurantService<restaurant>{
  private final Connection con;
  private Statement ste;
  private PreparedStatement prst;
   //pour initialiser la connexion
    public RestaurantService() {
        con = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(restaurant r) throws SQLException {
        String insert = "INSERT INTO `restaurant` (`nom`,`specialite`,`adresse`,`email`,`num_tel`) VALUE (?,?,?,?,?)";
        try{
        prst= con.prepareStatement(insert);
        
        prst.setString(1, r.getNom());
        prst.setString(2, r.getSpecialite());
        prst.setString(3, r.getAdresse());
        prst.setString(4, r.getEmail());
        prst.setString(5, r.getNum_tel());
        prst.executeUpdate();
        System.out.println("Restaurant créer avec succée!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(long id, restaurant r) throws SQLException {
       String update = "UPDATE `restaurant` SET "
                +"`nom`=?,`specialite`=?,`adresse`=?,`email`=?,`num_tel`=?"
                + "WHERE id_rest = '" +id+ "'";
        try {
             prst= con.prepareStatement(update);
             prst.setString(1, r.getNom());
             prst.setString(2, r.getSpecialite());
             prst.setString(3, r.getAdresse());
             prst.setString(4, r.getEmail());
             prst.setString(5, r.getNum_tel());

            prst.executeUpdate();
            System.out.println("Restaurant modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public void supprimer(long id) throws SQLException {
        String delete= "DELETE FROM restaurant where id_rest= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression restaurant avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<restaurant> afficher() {
      List<restaurant>resList;
      resList=new ArrayList<>();
      List<String> List_nom_rest=new ArrayList<String>();
      String select="SELECT * FROM `restaurant`";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                restaurant u = new restaurant();
                u.setId_rest(rs.getLong(1));
                u.setNom(rs.getString(2));
                u.setSpecialite(rs.getString(3));
                u.setAdresse(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setNum_tel(rs.getString(6));
                List_nom_rest.add(u.getNom());
                resList.add(u);
              System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return resList;
    }
    /*public List<String> afficher_list_nom_rest() {
      List<String> List_nom_rest;
      List_nom_rest = new ArrayList<>();
      String select="SELECT `nom` FROM `restaurant`";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                restaurant u = new restaurant();
                u.setNom(rs.getString(1));
                List_nom_rest.add(u.getNom());
                System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return List_nom_rest;
    }*/
    
}
