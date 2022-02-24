/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.reclamation;
import entities.restaurant;
import interfaces.I_ReclamationService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.MyConnection;

/**
 *
 * @author Asus
 */
//executeUpdate(req):c'est pour ajouter,supp et mod
public class ReclamationService implements I_ReclamationService<reclamation>{
     private Connection con;
  private Statement ste;
  private PreparedStatement prst; //executer les requetes sql
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
                
                recList.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return recList;
    }

   
    

    
}
