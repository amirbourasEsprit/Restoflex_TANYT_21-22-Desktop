/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.reclamation;
import entities.type_reclamation;
import interfaces.I_type_reclamationService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyConnection;

public class type_reclamationService implements I_type_reclamationService<type_reclamation>{
      private Connection con;
  private Statement ste;
  private PreparedStatement prst;
    public type_reclamationService() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterTypeReclamation(type_reclamation typerec) throws SQLException {
          String req = "INSERT INTO `type_reclamation` (`nom_type_reclamation`) VALUE (?)";
        try{
        prst= con.prepareStatement(req);       
        prst.setString(1,typerec.getNom_type_reclamation());
        prst.executeUpdate();
        System.out.println("Ajout réclamation avec succés !");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
    }

    

    @Override
    public void modifierTypeReclamation(long id_type_reclamation, type_reclamation typerec) throws SQLException {
         String req = "UPDATE `type_reclamation` SET `nom_type_reclamation`=? WHERE id_type_reclamation = '" +id_type_reclamation+ "'";
        try {
             prst= con.prepareStatement(req);
             prst.setString(1, typerec.getNom_type_reclamation());
            prst.executeUpdate();
            System.out.println("type de Reclamation modifier avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerTypeReclamation(long id_type_reclamation) throws SQLException {
        String delete= "DELETE FROM type_reclamation where id_type_reclamation= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setLong(1,id_type_reclamation);
            prst.executeUpdate();
            System.out.println("suppression de type reclamation avec succée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public List<type_reclamation> afficherTypeReclamation() {
          List<type_reclamation>typerecList;
      typerecList=new ArrayList<>();
      String select="SELECT * FROM `type_reclamation`";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                type_reclamation u = new type_reclamation();
                u.setId_type_reclamation(rs.getInt(1));
                u.setNom_type_reclamation(rs.getString(2));
            
                
                typerecList.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return typerecList;
    }

     @Override
    public List<type_reclamation> chercherTypeRec() {
        ReclamationService RS= new ReclamationService();
        List<type_reclamation> ls = new ArrayList<>();
        
        String select="SELECT nom_type_reclamation FROM `type_reclamation`";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select); //mod==>
            while(rs.next()){
                type_reclamation u = new type_reclamation();
                u.setNom_type_reclamation(rs.getString("nom_type_reclamation"));
               
                
                ls.add(u);
                System.out.println("affichage avec succée");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   return ls;
        
    }


    
    
    
}
