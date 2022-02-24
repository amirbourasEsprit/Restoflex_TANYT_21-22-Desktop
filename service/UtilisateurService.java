/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.utilisateur;
import interfaces.I_utilisateur;
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
        String insert = "INSERT INTO `utilisateur` (`nom`,`prenom`,`cin`,`mdp`,`email`,`num_tel`,`date_naissance`,`adresse`,`Salaire`,`solde_conge`,`poste_employe`,`id_role`,`id_rest`) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
         
                
                prst.executeUpdate();
                System.out.println("Employée ajouter");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @Override
 public void ajouterFournisseur(utilisateur u) throws SQLException {
         String insert = "INSERT INTO `utilisateur` (`nom`,`prenom`,`cin`,`mdp`,`email`,`num_tel`,`date_naissance`,`adresse`,`id_role`,`id_rest`) VALUE (?,?,?,?,?,?,?,?,?,?,?)";

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
                prst.setInt(12, u.getId_role());
                prst.setInt(13, u.getId_rest());
                prst.setInt(13, u.getId_rest());

         
                
                prst.executeUpdate();
                System.out.println("Fournisseur ajouter");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(long id, utilisateur u) throws SQLException {
 String update = "UPDATE `utilisateur` SET "
                +"`nom`=?,`prenom`=?,`cin`=?,`mdp`=?,`email`=?,`num_tel`=?,`date_naissance`=?,`adresse`=?,`Salaire`=?,`solde_conge`=?,`poste_employe`=?"
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
    public void supprimer(long id) throws SQLException {
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
                
                utilList.add(u);
                System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return utilList;
    }
    
}
