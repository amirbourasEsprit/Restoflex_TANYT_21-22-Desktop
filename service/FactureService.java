/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.facture;
import interfaces.IFactureService;
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
public class FactureService implements IFactureService<facture>{
    
   private Connection conn = MyConnection.getInstance().getCnx();
   private PreparedStatement ps;
   private Statement ste;

    @Override
    public void ajouterFacture(facture f) {
         String req = "INSERT INTO `facture` (`total`,`statut`,`id_fournisseur`,`id_rest`) VALUE (?,?,?,?)";
        try{
        ps= conn.prepareStatement(req);
        
        ps.setFloat(1, f.getTotal());
        ps.setString(2, f.getStatut() );
        ps.setLong(3, f.getId_fournisseur());
        ps.setLong(4, f.getId_rest());
        ps.executeUpdate();
        System.out.println("Facture créée avec succès!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierFacture(long num_facture,facture f) {
         /* try {   
       
  String req = "UPDATE `facture`  SET `total`= '"+f.getTotal()+"',`statut`= '"+f.getStatut()+"',`id_fournisseur`= '"+f.getId_fournisseur()+"',`id_rest`='" + f.getId_rest() + "' WHERE num_facture ='" +f.getNum_facture()+ "';";

   PreparedStatement ps = conn.prepareStatement(req);
   ps.executeUpdate();
   System.out.println("La facture : "+f.getNum_facture()+" is updated");
  }
  catch(SQLException ex){
    System.out.println("Update failed "+ex);
  }    */
         
          String req = "UPDATE `facture` SET `total`=?,`statut`=?,`id_fournisseur`=?,`id_rest`=? WHERE num_facture = '" +num_facture+ "'";
        try {
             ps= conn.prepareStatement(req);
             ps.setFloat(1, f.getTotal());
             ps.setString(2, f.getStatut());
             ps.setLong(3, f.getId_fournisseur());
             ps.setLong(4, f.getId_rest());

             ps.executeUpdate();
            System.out.println("Facture modifiée avec succès!");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public void supprimerFacture(long num_facture) {
        String req= "DELETE from `facture` where num_facture='"+num_facture+"'";
        try {
            ps=conn.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("Facture deleted!!");
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<facture> afficherFacture() {
      List<facture> ListFac;
      ListFac=new ArrayList<>();
      
      String req="SELECT * FROM `facture`";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                facture f = new facture();
                f.setNum_facture(rs.getLong(1));
                f.setTotal(rs.getFloat(2));
                f.setStatut(rs.getString(3));
                f.setId_fournisseur(rs.getLong(4));
                f.setId_rest(rs.getLong(5));
                
                ListFac.add(f);
                System.out.println("Liste remplie!!");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return ListFac;   
    }
    
}
