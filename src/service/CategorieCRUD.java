/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.I_categorie;
import entities.categorie;
import entities.produit_restaurant;
import java.sql.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyConnection;

/**
 *
 * @author Thinkpad
 */ 
public class CategorieCRUD implements I_categorie<categorie>{
  private final Connection con;
  private Statement ste;
  private PreparedStatement prst;
    public CategorieCRUD() {
                con = MyConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(categorie categ) throws SQLException {
        String req = "INSERT INTO `categorie` (`nom_categorie`) VALUE (?)";
        try{
        prst= con.prepareStatement(req);
        
        prst.setString(1, categ.getNom_categorie());
        
        prst.executeUpdate();
        System.out.println("Categorie créer avec succée!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(long id, categorie categ) throws SQLException {
     String req = "UPDATE `categorie` SET "
                +"`nom_categorie`=? WHERE id_categorie = '" +id+ "'";
        try {
             prst= con.prepareStatement(req);
             prst.setString(1, categ.getNom_categorie());
           
            prst.executeUpdate();
            System.out.println("Categorie modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(long id) throws SQLException {
          String delete= "DELETE FROM categorie where id_categorie= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression categorie avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<categorie> afficher() {
             List<categorie>categorieList;
      categorieList=new ArrayList<>();
      String select="SELECT * FROM categorie";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                categorie u = new categorie();
                u.setId_categorie(rs.getInt(1));
                u.setNom_categorie(rs.getString(2));
                
                
                categorieList.add(u);
                System.out.println("affichage succées");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return categorieList;

    }

    public ObservableList<produit_restaurant> getProducts(String ch,int i) {
       ObservableList<produit_restaurant>categorieList;
      categorieList=FXCollections.observableArrayList();
      String select="SELECT p.id_pdtrest,p.nom_pdt,p.quantite_pdt,p.id_rest,p.categ FROM produit_restaurant p inner join utilisateur u on p.id_rest=u.id_rest WHERE "
              + "p.categ='"+ch+"' and u.id_utilisateur='"+i+"'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                produit_restaurant u = new produit_restaurant();
                u.setId_pdrest(rs.getInt(1));
                u.setNom_pdt(rs.getString(2));
                u.setQuantit_pdt(rs.getFloat(3));
                u.setId_rest(rs.getInt(4));
                u.setCateg(rs.getString(5));
                
                
                categorieList.add(u);
                System.out.println("affichage succées");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return categorieList;
    }
    
}
