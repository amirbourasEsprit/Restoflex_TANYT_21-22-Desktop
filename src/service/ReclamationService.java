/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.reclamation;
import entities.restaurant;
import entities.utilisateur;
import interfaces.I_ReclamationService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyConnection;

/**
 *
 * @author Asus
 */
//executeUpdate(req):c'est pour ajouter,supp et mod
public class ReclamationService implements I_ReclamationService<reclamation>{
     private Connection con;
  private Statement ste;
  private PreparedStatement prst;
    public ReclamationService() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterReclamation(reclamation rec) throws SQLException {
          String req = "INSERT INTO `reclamation` (`destinataire`,`description`,`statut_reclamation`,`date_reclamation`,`id_type_reclamation`,`id_utilisateur`) VALUE (?,?,?,?,?,?)";
        try{ 
        prst= con.prepareStatement(req);       
        prst.setString(1, rec.getDestinataire());
        prst.setString(2, rec.getDescription());
        prst.setString(3, rec.getStatut_reclamation());
        java.sql.Date reclamation=new java.sql.Date(rec.getDate_reclamation().getTime());
        prst.setDate(4, reclamation);
        prst.setLong(5,rec.getId_type_reclamation());
        prst.setLong(6,rec.getId_utilisateur());
        prst.executeUpdate();
        System.out.println("Ajout réclamation avec succés !");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifierReclamation(long num_reclamation, reclamation rec) throws SQLException {
         String req = "UPDATE `reclamation` SET `destinataire`=?,`description`=?,`statut_reclamation`=?,`date_reclamation`=?,`id_type_reclamation`=?,`id_utilisateur`=? WHERE num_reclamation = '" +num_reclamation+ "'";
        try {
             prst= con.prepareStatement(req);
             prst.setString(1, rec.getDestinataire());
             prst.setString(2, rec.getDescription());
             prst.setString(3, rec.getStatut_reclamation());
             prst.setDate(4, rec.getDate_reclamation());
            prst.setLong(5,rec.getId_type_reclamation());
            prst.setLong(6,rec.getId_utilisateur());

            prst.executeUpdate(); 
            System.out.println("Reclamation modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    @Override
    public void supprimerReclamation(long num_reclamation) throws SQLException {
         String delete= "DELETE FROM reclamation where num_reclamation= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setLong(1,num_reclamation);
            prst.executeUpdate();
            System.out.println("suppression reclamation avec succée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<reclamation> afficherReclamation() {
      List<reclamation>recList;
      recList=new ArrayList<>();
      String select="SELECT * FROM `reclamation`";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); 
            while(rs.next()){
                reclamation u = new reclamation();
                u.setNum_reclamation(rs.getLong(1));
                u.setDestinataire(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setStatut_reclamation(rs.getString(4));
                u.setDate_reclamation(rs.getDate(5));
                u.setId_type_reclamation(rs.getInt(6));
                u.setId_utilisateur(rs.getInt(7));
                
                recList.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return recList;
    }
     public List<reclamation> afficherReclamationParId(int id){
          List<reclamation>recList;
      recList=new ArrayList<>();
      String select="SELECT * FROM `reclamation` WHERE id_utilisateur= "+id;
         System.out.println(select);
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); 
            while(rs.next()){
                System.out.println(rs.getLong(1));
                reclamation u = new reclamation();
                u.setNum_reclamation(rs.getLong(1));
                u.setDestinataire(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setStatut_reclamation(rs.getString(4));
                u.setDate_reclamation(rs.getDate(5));
                u.setId_type_reclamation(rs.getInt(6));
                u.setId_utilisateur(rs.getInt(7));
                
                recList.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return recList;
    }
     
       public List<reclamation> afficherReclamationRecu(String nom){
          List<reclamation>recList;
      recList=new ArrayList<>();
      String select="SELECT * FROM `reclamation` WHERE destinataire LIKE'%"+nom+"%'";
         System.out.println(select);
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); 
            while(rs.next()){
                System.out.println(rs.getLong(1));
                reclamation u = new reclamation();
                u.setNum_reclamation(rs.getLong(1));
                u.setDestinataire(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setStatut_reclamation(rs.getString(4));
                u.setDate_reclamation(rs.getDate(5));
                u.setId_type_reclamation(rs.getInt(6));
                u.setId_utilisateur(rs.getInt(7));
                
                recList.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return recList;
    }
       
         public List<reclamation> afficherReclamationTraite(){
          List<reclamation>recList;
      recList=new ArrayList<>();
      String select="SELECT * FROM `reclamation` WHERE statut_reclamation ='Traité'";
         System.out.println(select);
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); 
            while(rs.next()){
                System.out.println(rs.getLong(1));
                reclamation u = new reclamation();
                u.setNum_reclamation(rs.getLong(1));
                u.setDestinataire(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setStatut_reclamation(rs.getString(4));
                u.setDate_reclamation(rs.getDate(5));
                u.setId_type_reclamation(rs.getInt(6));
                u.setId_utilisateur(rs.getInt(7));
                
                recList.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return recList;
    }
       
          public List<reclamation> afficherReclamationRecuNonTraite(String nom){
          List<reclamation>recList;
      recList=new ArrayList<>();
      String select="SELECT * FROM `reclamation` WHERE destinataire LIKE'%"+nom+"%' AND statut_reclamation = 'En cours'";
         System.out.println(select);
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); //mod==>
            while(rs.next()){
                System.out.println(rs.getLong(1));
                reclamation u = new reclamation();
                u.setNum_reclamation(rs.getLong(1));
                u.setDestinataire(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setStatut_reclamation(rs.getString(4));
                u.setDate_reclamation(rs.getDate(5));
                u.setId_type_reclamation(rs.getInt(6));
                u.setId_utilisateur(rs.getInt(7));
                
                recList.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return recList;
    }
      
     

    @Override
    public utilisateur chercherPrenomGerant(long idRest) {
        utilisateur gerant = new utilisateur();
         
        String select="SELECT prenom FROM utilisateur where id_rest='"+idRest+"' AND id_role=1;";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); 
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setNom(rs.getString("prenom"));
               
                
                gerant=u;
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return gerant;
    }

    @Override
    public List<restaurant> chercherRestaurant(int id,String cin ) {
        List<restaurant> LR = new ArrayList<>();
        
        String req2="SELECT r.nom, r.id_rest from restaurant r INNER JOIN utilisateur u ON r.id_rest=u.id_rest where u.id_role=3 and u.id_fournisseur= "+id+" and u.cin= "+cin;
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(req2);
            //System.out.println(rs);
            while(rs.next()){ 
                restaurant r = new restaurant();
                r.setNom(rs.getString("nom"));
                r.setId_rest(rs.getLong("id_rest"));
                
                LR.add(r);
                System.out.println("Liste des noms remplie !!!!!");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
      return LR;  
    }

    @Override
    public reclamation chercherReclamation(long id) {
 
      String select="SELECT * FROM `reclamation` WHERE num_reclamation='"+id+"'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); //mod==>
            while(rs.next()){
                reclamation u = new reclamation();
                u.setNum_reclamation(rs.getLong(1));
                u.setDestinataire(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setStatut_reclamation(rs.getString(4));
                u.setDate_reclamation(rs.getDate(5));
                u.setId_type_reclamation(rs.getInt(6));
                u.setId_utilisateur(rs.getInt(7));
                
                return u;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return null;
    }
     @Override
    public List<utilisateur> chercherNomFournisseur(long id) {
       List<utilisateur> LUF = new ArrayList<>();
         
        String select="SELECT nom,prenom FROM utilisateur WHERE id_role=3 and id_rest='"+id+"'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); 
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setNom(rs.getString("nom"));
                     u.setPrenom(rs.getString("prenom"));
               
                
                LUF.add(u);
   
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return LUF;
    }
       public List<utilisateur> chercherNomEmploye(long id) {
       List<utilisateur> LUE = new ArrayList<>();
        String select="SELECT nom,prenom FROM utilisateur WHERE id_role=2 and id_rest='"+id+"'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); 
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setNom(rs.getString("nom"));
                     u.setPrenom(rs.getString("prenom"));
               
                
                LUE.add(u);
   
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return LUE;
}
       //metier1
       public List<reclamation> rechercherParDate(int id_rest_current,String recherche) {
        ObservableList<reclamation>recList=FXCollections.observableArrayList();
         String select="SELECT * FROM reclamation WHERE id_utilisateur="+id_rest_current+" AND date_reclamation = '" +recherche+"'";
           System.out.println(select);
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            reclamation u = new reclamation();
            while(rs.next()){
                
                u.setNum_reclamation(rs.getLong(1));
                u.setDestinataire(rs.getString(2));
                u.setDescription(rs.getString(3));
                u.setStatut_reclamation(rs.getString(4));
                u.setDate_reclamation(rs.getDate(5));
                u.setId_type_reclamation(rs.getInt(6));
                u.setId_utilisateur(rs.getInt(7));
                
                recList.add(u);
               }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return recList;
    }
       
       
        public void ConfirmerReclamation(reclamation rec) throws SQLException {
         String req = "UPDATE `reclamation` SET `statut_reclamation`=? WHERE num_reclamation = '" +rec.getNum_reclamation()+ "'";
        try {
             prst= con.prepareStatement(req);
             prst.setString(1, "Traité");

            prst.executeUpdate(); 
            System.out.println("Reclamation modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
