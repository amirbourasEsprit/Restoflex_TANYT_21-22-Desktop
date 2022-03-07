/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Conge;
import entities.utilisateur;
import interfaces.I_CongeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyConnection;

/**
 *
 * @author Yosr Belaam
 */
public class CongeService implements I_CongeService<Conge>{
    private Connection con;
    private Statement ste;
    private PreparedStatement prst;
    public CongeService() {
        con = MyConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterConge(Conge cong) throws SQLException {
        String req = "INSERT INTO `conge` (`date_deb`,`date_fin`,`solde_restant`,`etat`,`id_type_conge`,`id_utilisateur`) VALUE (?,?,?,?,?,?)";
        try{
        prst= con.prepareStatement(req);
        
        prst.setDate(1, cong.getDate_deb());
        prst.setDate(2, cong.getDate_fin());
        prst.setInt(3, cong.getSolde_restant());
        prst.setString(4, cong.getEtat());
        prst.setInt(5, cong.getId_type_conge());
        prst.setInt(6, cong.getId_utilisateur());
        prst.executeUpdate();
        System.out.println("Congé créé avec succée ");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public void modifierConge(Conge cong) throws SQLException {
      
        try {
             String req = "UPDATE conge SET date_deb=?,date_fin=?,solde_restant=?,etat=?,id_type_conge=?,id_utilisateur=? WHERE id_conge =? ";
             prst= con.prepareStatement(req);
           
             prst.setDate(1, cong.getDate_deb());
             prst.setDate(2, cong.getDate_fin());
             prst.setInt(3, cong.getSolde_restant());
             prst.setString(4, cong.getEtat());
             prst.setInt(5, cong.getId_type_conge());
             prst.setInt(6, cong.getId_utilisateur());
             prst.setInt(7, cong.getId_conge());
            prst.executeUpdate();
            System.out.println("Congé modifié avec succée!");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public void supprimerConge(int id) throws SQLException {
        String delete= "DELETE FROM conge where id_conge= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setInt(1,id);
            prst.executeUpdate();
            System.out.println("suppression de conge avec succée");
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public ObservableList<Conge> afficherConge() {
          ObservableList<Conge> congList = FXCollections.observableArrayList();
      String select="SELECT * FROM `conge`";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                Conge cong = new Conge();
                 cong.setId_conge(rs.getInt(1));
                 cong.setDate_deb(rs.getDate(2));
                 cong.setDate_fin(rs.getDate(3));
                 cong.setSolde_restant(rs.getInt(4));
                 cong.setEtat(rs.getString(5));
                 cong.setId_type_conge(rs.getInt(6));
                 cong.setId_utilisateur(rs.getInt(7));
                congList.add(cong);
                System.out.println("affichage avec succée");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return congList;
    }   
    public utilisateur getuser(int i) {
      String select="SELECT * FROM `utilisateur` where id_utilisateur = '"+i+"'";
                      utilisateur cong = new utilisateur();

        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                 cong.setId_utilisateur(rs.getInt(1));
                 cong.setNom(rs.getString(2));
                 cong.setPrenom(rs.getString(3));
                System.out.println("user in");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(cong.getNom());
             
                        return cong;

    }
    public void approuverConge(Conge cong) throws SQLException {
      
        try {
             String req = "UPDATE conge SET etat=?WHERE id_conge =? ";
             prst= con.prepareStatement(req);
           
             prst.setString(1,"approuvé");
             prst.setInt(2, cong.getId_conge());

            prst.executeUpdate();
            System.out.println("aprouvé");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    
    }
    public void refuserConge(Conge cong) throws SQLException {
      
        try {
             String req = "UPDATE conge SET etat=?WHERE id_conge =? ";
             prst= con.prepareStatement(req);
           
             prst.setString(1,"refusé");
             prst.setInt(2, cong.getId_conge());

            prst.executeUpdate();
            System.out.println("refusé");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    
    }
      
    public ObservableList<Conge> RechercherConge(int id) {
          ObservableList<Conge> congList = FXCollections.observableArrayList();
      String select="SELECT * FROM `conge` where id_utilisateur= '"+id+"'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                Conge cong = new Conge();
                 cong.setId_conge(rs.getInt(1));
                 cong.setDate_deb(rs.getDate(2));
                 cong.setDate_fin(rs.getDate(3));
                 cong.setSolde_restant(rs.getInt(4));
                 cong.setEtat(rs.getString(5));
                 cong.setId_type_conge(rs.getInt(6));
                 cong.setId_utilisateur(rs.getInt(7));
                congList.add(cong);
                System.out.println("Rechercher fait ");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return congList;
    }   

    public void soldeConge(int id, int nb) throws SQLException {
      
        try {
             String req = "UPDATE utilisateur SET solde_conge= solde_conge- '"+nb+"' WHERE id_utilisateur = ? ";
             prst= con.prepareStatement(req);
           
           //  prst.setInt(1,4);
             prst.setInt(1, id);

            prst.executeUpdate();
            System.out.println("solde --");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    
    }


   
    public ObservableList<Conge> afficherConge2()  { //non utilisé mais khaliha
        ObservableList<Conge> congList = FXCollections.observableArrayList();
      String select="SELECT u.nom,c.date_deb,c.date_fin,c.etat from utilisateur u "
              + "inner join conge c on u.id_utilisateur=c.id_utilisateur";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                Conge cong = new Conge();
                utilisateur u= new utilisateur();
                 u.setNom(rs.getString(1));
                 cong.setDate_deb(rs.getDate(2));
                 cong.setDate_fin(rs.getDate(3));
                 cong.setSolde_restant(rs.getInt(4));
                 cong.setEtat(rs.getString(5));
                 cong.setId_type_conge(rs.getInt(6));
                 cong.setId_utilisateur(rs.getInt(7));
                congList.add(cong);
                System.out.println("affichage avec succée");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return congList;
    }

}
