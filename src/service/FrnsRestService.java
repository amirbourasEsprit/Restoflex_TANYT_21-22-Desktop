/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.facture;
import entities.fournisseur_restaurant;
import interfaces.I_FrnsRest;
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
 * @author Nesrine
 */
public class FrnsRestService implements I_FrnsRest<fournisseur_restaurant>{
    
     private Connection conn = MyConnection.getInstance().getCnx();
     private PreparedStatement ps;
     private Statement ste;

    @Override
    public void ajouterFrnsRest(fournisseur_restaurant fr) {
         String req = "INSERT INTO `fournisseur_restaurant` (`id_rest`,`id_fournisseur`) VALUE (?,?)";
        try{
        ps= conn.prepareStatement(req);
        
        ps.setLong(1, fr.getId_rest());
        ps.setLong(2, fr.getId_fournisseur());
        
        ps.executeUpdate();
        System.out.println("Ajout avec succès!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierFrnsRest(long id_fournisseur_restaurant, fournisseur_restaurant fr) {
        String req = "UPDATE `fournisseur_restaurant` SET `id_rest`=?,`id_fournisseur`=? WHERE id_fournisseur_restaurant = '" +id_fournisseur_restaurant+ "'";
        try {
             ps= conn.prepareStatement(req);
             
             ps.setLong(1, fr.getId_rest());
             ps.setLong(2, fr.getId_fournisseur());

             ps.executeUpdate();
            System.out.println("Modification avec succès!");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerFrnsRest(long id_fournisseur_restaurant) {
         String req= "DELETE from `fournisseur_restaurant` where id_fournisseur_restaurant='"+id_fournisseur_restaurant+"'";
        try {
            ps=conn.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("Suppression effectuée!!");
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<fournisseur_restaurant> afficherFrnsRest() {
        List<fournisseur_restaurant> ListFR;
      ListFR=new ArrayList<>();
      
      String req="SELECT * FROM `fournisseur_restaurant`";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                fournisseur_restaurant fr = new fournisseur_restaurant();
                fr.setId_rest(rs.getLong(1));
                fr.setId_fournisseur(rs.getLong(2));
                
                ListFR.add(fr);
                System.out.println("Liste remplie!!");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return ListFR;   
    }
    

}

    
    
    
    

