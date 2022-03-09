/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.fournisseur;
import entities.restaurant;
import entities.utilisateur;
import interfaces.I_utilisateur;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.DatatypeConverter;
import util.MyConnection;

/**
 *
 * @author boura
 */
public class UtilisateurService implements I_utilisateur <utilisateur> {
  private final Connection con;
  private Statement ste;
  private PreparedStatement prst;
   //pour initialiser la connexion
    public UtilisateurService() {
        con = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(utilisateur u) throws SQLException {
      String insert = "INSERT INTO `utilisateur` (`nom`,`prenom`,`cin`,`mdp`,`email`,`num_tel`,`date_naissance`,`adresse`,`Salaire`,`solde_conge`,`poste_employe`,`id_role`,`id_rest`,`id_fournisseur`) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            prst=con.prepareStatement(insert);
                prst.setString(1, u.getNom());
                prst.setString(2, u.getPrenom());
                prst.setString(3, u.getCin());
                prst.setString(4, u.getMdp());
                prst.setString(5, u.getEmail());
                prst.setString(6, u.getNum_tel());
                prst.setDate(7, u.getDate_naissance());
                prst.setString(8, u.getAdresse());
                prst.setInt(9, u.getSalaire());
                prst.setInt(10, u.getSolde_conge());
                prst.setString(11, u.getPoste_employe());
                prst.setInt(12, u.getId_role());
                prst.setInt(13, u.getId_rest());
                prst.setInt(14, u.getId_fournisseur());
                
                prst.executeUpdate();
                System.out.println("Utilisateur ajouter");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
 public void ajouterEmploye(utilisateur u) throws SQLException {
        String insert = "INSERT INTO `utilisateur` (`nom`,`prenom`,`cin`,`mdp`,`email`,`num_tel`,`date_naissance`,`adresse`,`poste_employe`,`id_role`,`id_rest`) VALUE (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            prst=con.prepareStatement(insert);
                prst.setString(1, u.getNom());
                prst.setString(2, u.getPrenom());
                prst.setString(3, u.getCin());
                prst.setString(4, u.getMdp());
                prst.setString(5, u.getEmail());
                prst.setString(6, u.getNum_tel());
                prst.setDate(7, u.getDate_naissance());
                prst.setString(8, u.getAdresse());
               // prst.setInt(9, u.getSalaire());
               // prst.setInt(10, u.getSolde_conge());
                prst.setString(9, u.getPoste_employe());
                prst.setInt(10, u.getId_role());
                prst.setInt(11, u.getId_rest());
         
                
                prst.executeUpdate();
                System.out.println("Employée ajouter");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @Override
 public void ajouterFournisseur(utilisateur u) throws SQLException {
         String insert = "INSERT INTO `utilisateur` (`nom`,`prenom`,`cin`,`mdp`,`email`,`num_tel`,`date_naissance`,`adresse`,`id_role`,`id_rest`,`id_fournisseur`) VALUE (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            prst=con.prepareStatement(insert);
                prst.setString(1, u.getNom());
                prst.setString(2, u.getPrenom());
                prst.setString(3, u.getCin());
                prst.setString(4, u.getMdp());
                prst.setString(5, u.getEmail());
                prst.setString(6, u.getNum_tel());
                prst.setDate(7, u.getDate_naissance());
                prst.setString(8, u.getAdresse());
                prst.setInt(9, u.getId_role());
                prst.setInt(10, u.getId_rest());
                prst.setInt(11, u.getId_fournisseur());

         
                
                prst.executeUpdate();
                System.out.println("Fournisseur ajouter");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(long id, utilisateur u) throws SQLException {
 String update = "UPDATE `utilisateur` SET "
                +"`nom`=?,`prenom`=?,`cin`=?,`mdp`=?,`email`=?,`num_tel`=?,`date_naissance`=?,`adresse`=?,`Salaire`=?,`solde_conge`=?,`poste_employe`=?,`Status_compte`=?"
                + "WHERE id_utilisateur = '" +id+ "'";
 //,`id_role`=?,`id_rest`=?,`id_fournisseur`=?
        try {
             prst= con.prepareStatement(update);
              prst.setString(1, u.getNom());
                prst.setString(2, u.getPrenom());
                prst.setString(3, u.getCin());
                prst.setString(4, u.getMdp());
                prst.setString(5, u.getEmail());
                prst.setString(6, u.getNum_tel());
                prst.setDate(7, u.getDate_naissance());
                prst.setString(8, u.getAdresse());
                prst.setInt(9, u.getSalaire());
                prst.setInt(10, u.getSolde_conge());
                prst.setString(11, u.getPoste_employe());
                prst.setString(12, u.getStatus_compte());

               // prst.setInt(12, u.getId_role());
               // prst.setInt(13, u.getId_rest());
               // prst.setInt(14, u.getId_fournisseur());

            prst.executeUpdate();
            System.out.println("Restaurant modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(long id) {
         String delete= "DELETE FROM utilisateur where id_utilisateur= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression utilisateur avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

   @Override
    public List<utilisateur> afficher() {
        List<utilisateur>utilList=new ArrayList<>();
         String select="SELECT * FROM `utilisateur`";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setMdp(rs.getString(5));
                u.setEmail(rs.getString(6));                
                u.setNum_tel(rs.getString(7));
                u.setDate_naissance(rs.getDate(8));
                u.setAdresse(rs.getString(9));
                u.setSalaire(rs.getInt(10));
                u.setSolde_conge(rs.getInt(11));
                u.setPoste_employe(rs.getString(12));
                u.setId_role(rs.getInt(13));
                u.setId_rest(rs.getInt(14));
                u.setId_fournisseur(rs.getInt(15));
                u.setStatus_compte(rs.getString(16));
             
                utilList.add(u);
                System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return utilList;
    }
    public boolean validateLogin(String Email, String mdp){
        
         String selectL="SELECT * FROM `utilisateur` WHERE email= ? and mdp = ?";
        try {
            prst=con.prepareStatement(selectL);
            prst.setString(1,Email);
            prst.setString(2, mdp);
               ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                  utilisateur u = new utilisateur();
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setMdp(rs.getString(5));
                u.setEmail(rs.getString(6));                
                u.setNum_tel(rs.getString(7));
                u.setDate_naissance(rs.getDate(8));
                u.setAdresse(rs.getString(9));
                u.setSalaire(rs.getInt(10));
                u.setSolde_conge(rs.getInt(11));
                u.setPoste_employe(rs.getString(12));
                u.setId_role(rs.getInt(13));
                u.setId_rest(rs.getInt(14));
                u.setId_fournisseur(rs.getInt(15));
                u.setStatus_compte(rs.getString(16));

              //current user
                utilisateur.current_user=u;
                
                return true;
            }
                
            }catch(SQLException e) {
                System.err.println(e.getMessage());
            }                
    return false;
    }
    public String crypter_password(String password) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (NoSuchAlgorithmException e) {
        }

     //   return hashValue;
     return hashValue;
    
    }
    
  
    public List<utilisateur> afficherEmp(int id_rest_current) {
        ObservableList<utilisateur>utilList=FXCollections.observableArrayList();
         String select="SELECT * FROM `utilisateur` WHERE  id_role=2 AND id_rest="+id_rest_current;
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                utilisateur u = new utilisateur();
               
                //r.setNom_role(rs.gets);
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setMdp(rs.getString(5));
                u.setEmail(rs.getString(6));                
                u.setNum_tel(rs.getString(7));
                u.setDate_naissance(rs.getDate(8));
                u.setAdresse(rs.getString(9));
                u.setSalaire(rs.getInt(10));
                u.setSolde_conge(rs.getInt(11));
                u.setPoste_employe(rs.getString(12));
                u.setId_role(rs.getInt(13));
                u.setId_rest(rs.getInt(14));
                u.setId_fournisseur(rs.getInt(15));
                u.setStatus_compte(rs.getString(16));
             
                utilList.add(u);
                System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return utilList;
    }
    public List<utilisateur> afficherfour(int id_rest_current) {
    
        ObservableList<utilisateur> List = FXCollections.observableArrayList();
        try {
       String query2="select * from utilisateur join fournisseur on utilisateur.id_fournisseur=fournisseur.id_fournisseur where id_role=3 and id_rest="+id_rest_current;
                PreparedStatement smt = con.prepareStatement(query2);
                System.out.println(smt);
                utilisateur u;
                fournisseur r;
               
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                 r=new fournisseur(rs.getString("nom_fournisseur"));
                u=new utilisateur(rs.getInt("id_utilisateur"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("mdp"), rs.getString("email"), rs.getString("num_tel"), rs.getDate("date_naissance"), rs.getString("adresse"), rs.getInt("id_role"),rs.getInt("id_rest"), rs.getInt("id_fournisseur"),rs.getString("Status_compte"));
               
                u.fournisseur = r;
                //System.out.println(r);
                List.add(u);
                //   l.add(r);
                }
             //   System.out.println(List);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return List;
    
}
     public List<utilisateur> afficherCurrentuser(int id_current_user) {
        ObservableList<utilisateur>utilList=FXCollections.observableArrayList();
         String select="select * from utilisateur join restaurant on utilisateur.id_rest=restaurant.id_rest where id_utilisateur="+id_current_user;
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                utilisateur u = new utilisateur();
                restaurant r= new restaurant();
                //r.setNom_role(rs.gets);
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setMdp(rs.getString(5));
                u.setEmail(rs.getString(6));                
                u.setNum_tel(rs.getString(7));
                u.setDate_naissance(rs.getDate(8));
                u.setAdresse(rs.getString(9));
                u.setSalaire(rs.getInt(10));
                u.setSolde_conge(rs.getInt(11));
                u.setPoste_employe(rs.getString(12));
                u.setId_role(rs.getInt(13));
                u.setId_rest(rs.getInt(14));
                u.setId_fournisseur(rs.getInt(15));
                u.setStatus_compte(rs.getString(16));
                r.setId_rest(rs.getInt(17));
                r.setNom(rs.getString(18));
                r.setSpecialite(rs.getString(19));
                r.setAdresse(rs.getString(20));
                r.setEmail(rs.getString(21));
                r.setNum_tel(rs.getString(22));
                u.restaurant=r;
                utilList.add(u);
                System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return utilList;
    }
     public utilisateur validateMail(String Email){
        
         String selectL="SELECT * FROM `utilisateur` WHERE email=?";
        try {
            prst=con.prepareStatement(selectL);
            prst.setString(1,Email);
         
               ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                  utilisateur u = new utilisateur();
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setMdp(rs.getString(5));
                u.setEmail(rs.getString(6));                
                u.setNum_tel(rs.getString(7));
                u.setDate_naissance(rs.getDate(8));
                u.setAdresse(rs.getString(9));
                u.setSalaire(rs.getInt(10));
                u.setSolde_conge(rs.getInt(11));
                u.setPoste_employe(rs.getString(12));
                u.setId_role(rs.getInt(13));
                u.setId_rest(rs.getInt(14));
                u.setId_fournisseur(rs.getInt(15));
              //current user
                utilisateur.current_user=u;
                
                return u;
            }
                
            }catch(SQLException e) {
                System.err.println(e.getMessage());
            }                
    return null;
    }
     public List<utilisateur> rechercheEmployee(int id_rest_current,String recherche) {
        ObservableList<utilisateur>utilList=FXCollections.observableArrayList();
         String select="SELECT * FROM `utilisateur` WHERE id_role=2 AND id_rest="+id_rest_current+" AND nom LIKE '%"+recherche+"%'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                utilisateur u = new utilisateur();
               
                //r.setNom_role(rs.gets);
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setMdp(rs.getString(5));
                u.setEmail(rs.getString(6));                
                u.setNum_tel(rs.getString(7));
                u.setDate_naissance(rs.getDate(8));
                u.setAdresse(rs.getString(9));
                u.setSalaire(rs.getInt(10));
                u.setSolde_conge(rs.getInt(11));
                u.setPoste_employe(rs.getString(12));
                u.setId_role(rs.getInt(13));
                u.setId_rest(rs.getInt(14));
                u.setId_fournisseur(rs.getInt(15));
                u.setStatus_compte(rs.getString(16));
             
                utilList.add(u);
                System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return utilList;
    }
     
    public boolean test_Password(String password) {

        int nombre_Maj = 0;
        int nombre_Entier = 0;
        int nombre_Min = 0;

        int ascii;

        for (int i = 0; i < password.length(); i++) {
            ascii = password.charAt(i);

            if ((ascii >= 65) && (ascii <= 90)) {
                nombre_Maj++;
            }
            if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                nombre_Entier++;
            }
            if ((ascii >= 97) && (ascii <= 122)) {
                nombre_Min++;
            }

        }
        if ((nombre_Entier >= 1) && (nombre_Maj >= 1) && (nombre_Min >= 1) && (password.length() >= 8)) {
            return true;
        }
        return false;

    }
    
}
