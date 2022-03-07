/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.type_conge;
import interfaces.I_TypeCongeService;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyConnection;

/**
 *
 * @author Yosr Belaam
 */
public class TypeCongeService implements I_TypeCongeService<type_conge> {
  private Connection con;
  private Statement ste;
  private PreparedStatement prst;
    public TypeCongeService() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterTypeConge(type_conge Tcong) throws SQLException { 
        String req = "INSERT INTO `type_conge` (`id_type_conge`,`nom_type_conge`) VALUE (?,?)";
        try{
        prst= con.prepareStatement(req);
        prst.setInt(1, Tcong.getId_type_conge());
        prst.setString(2, Tcong.getNom_type_conge());
        prst.executeUpdate();
        System.out.println("Type de congé créé avec succée");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierTypeConge(type_conge Tcong) throws SQLException {
        String req = "UPDATE type_conge SET id_type_conge=?,nom_type_conge=? WHERE id_type_conge =" + Tcong.getId_type_conge() ;
        try {
             prst= con.prepareStatement(req);
             prst.setInt(1, Tcong.getId_type_conge());
             prst.setString(2, Tcong.getNom_type_conge());
             
            prst.executeUpdate();
            System.out.println("Type Congé modifié avec succée!");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
 
    }
    
    @Override
    public void supprimerTypeConge(int id) throws SQLException {
        String delete= "DELETE FROM type_conge where id_type_conge= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setInt(1,id);
            prst.executeUpdate();
            System.out.println("suppression du type de conge avec succée");
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public ObservableList<type_conge> afficherTypeConge() {
      ObservableList<type_conge>TypeList= FXCollections.observableArrayList();
      String select="SELECT * FROM type_conge";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                 type_conge Tcong = new type_conge();
                 Tcong.setId_type_conge(rs.getInt(1));
                 Tcong.setNom_type_conge(rs.getString(2));
                TypeList.add(Tcong);
                System.out.println("affichage avec succée");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    
        //System.out.println(TypeList);
        return TypeList;
    } 
public ObservableList<type_conge> RechercherTypeConge(String nom) {
          ObservableList<type_conge> congList = FXCollections.observableArrayList();
      String select="SELECT * FROM `type_conge` where nom_type_conge= '"+nom+"'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                type_conge Tcong = new type_conge();
                 Tcong.setId_type_conge(rs.getInt(1));
                 Tcong.setNom_type_conge(rs.getString(2));

                congList.add(Tcong);
                System.out.println("Rechercher fait ");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return congList;
    }   

}
