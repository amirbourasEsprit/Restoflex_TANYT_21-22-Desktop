/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Commande;
import entities.facture;
import entities.fournisseur;
import entities.produit_restaurant;
import entities.restaurant;
import entities.stock;
import interfaces.IFactureService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
         String req = "INSERT INTO `facture` (`date_facture`,`total`,`statut`,`id_fournisseur`,`id_rest`) VALUE (?,?,?,?,?)";
        try{
        ps= conn.prepareStatement(req);
        
        ps.setDate(1, f.getDate_facture());
        ps.setFloat(2, f.getTotal());
        ps.setString(3, "non payée" );
        ps.setLong(4, f.getId_fournisseur());
        ps.setLong(5, f.getId_rest());
        ps.executeUpdate();
        System.out.println("Facture créée avec succès!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierFacture(long num_facture,facture f) {
    
          String req = "UPDATE `facture` SET `date_facture`=?, `total`=?,`statut`=?,`id_fournisseur`=?,`id_rest`=? WHERE num_facture = '" +num_facture+ "'";
        try {
             ps= conn.prepareStatement(req);
             ps.setDate(1, f.getDate_facture());
             ps.setFloat(2, f.getTotal());
             ps.setString(3, f.getStatut() );
             ps.setLong(4, f.getId_fournisseur());
             ps.setLong(5, f.getId_rest());

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
      List<facture> ListFac = new ArrayList<>();
      
      
      String req="SELECT * FROM `facture`";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                facture f = new facture();
                f.setNum_facture(rs.getLong(1));
                f.setDate_facture(rs.getDate(2));
                f.setTotal(rs.getFloat(3));
                f.setStatut(rs.getString(4));
                f.setId_fournisseur(rs.getLong(5));
                f.setId_rest(rs.getLong(6));
                
                ListFac.add(f);
                System.out.println("Liste remplie 1!!");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
      
    return ListFac;   
    }

    @Override
    public facture rechercherFacture(long num_facture) {
        
        String req="SELECT * FROM `facture` where num_facture='"+num_facture+"'";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                facture f = new facture();
                f.setNum_facture(rs.getLong(1));
                f.setDate_facture(rs.getDate(2));
                f.setTotal(rs.getFloat(3));
                f.setStatut(rs.getString(4));
            f.setId_fournisseur(rs.getLong(5));
                f.setId_rest(rs.getLong(6));
               return f; 
        }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
            return null;
    }

    @Override
    public restaurant rechercherRestaurant(long num_facture) {
        
       String req1="SELECT r.nom,r.adresse  from restaurant r INNER JOIN facture f ON r.id_rest=f.id_rest where num_facture='"+num_facture+"'";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req1);
            //System.out.println(rs);
            while(rs.next()){ 
                restaurant rr = new restaurant();
                rr.setNom(rs.getString("nom"));
                rr.setAdresse(rs.getString("adresse"));
                
                return rr;
               
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return null;  
    }

    @Override
    public fournisseur rechercherFournisseur(long num_facture) {
        String req2="SELECT fr.nom_fournisseur,fr.adresse_fournisseur from fournisseur fr INNER JOIN facture f ON fr.id_fournisseur=f.id_fournisseur where num_facture='"+num_facture+"'";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req2);
            System.out.println(rs);
            while(rs.next()){ 
                fournisseur fr = new fournisseur();
                fr.setNom_fournisseur(rs.getString("nom_fournisseur"));
                fr.setAdresse_fournisseur(rs.getString("adresse_fournisseur"));
                 System.out.println(fr.toString());
                return fr;
               
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
      return null;  
    }

    //curr_user.get(id_rest)
    @Override
    public List<facture> afficherFactureRest(long idRest) {
        List<facture> ListFR = new ArrayList<>();
    
      String req="SELECT f.num_facture, f.date_facture, f.total, f.statut, f.id_fournisseur, f.id_rest FROM facture f INNER JOIN restaurant r on r.id_rest=f.id_rest WHERE f.id_rest='"+idRest+"'";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                facture f = new facture();
                f.setNum_facture(rs.getLong("num_facture"));
                f.setDate_facture(rs.getDate("date_facture"));
                f.setTotal(rs.getFloat("total"));
                f.setStatut(rs.getString("statut"));
                f.setId_fournisseur(rs.getLong("id_fournisseur"));
                f.setId_rest(rs.getLong("id_rest"));
       
                ListFR.add(f);
                System.out.println("Liste remplie ffrr!!");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
     
    return ListFR;   
        
    }

    @Override
    public Float calculTotal(long id_cmd) {
        String req="SELECT  c.quantite,s.prix_unitaire from commande c , stock s, produit_restaurant p WHERE c.id_fournisseur=s.id_fournisseur "
                + "AND c.id_produit=p.id_pdtrest AND p.nom_pdt=s.nom_stock AND id_cmd='"+id_cmd+"'";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Commande c = new Commande();
                c.setQuantite(rs.getFloat("quantite"));
                stock s = new stock();
                s.setPrix_unitaire(rs.getFloat("prix_unitaire"));
                
                float q = c.getQuantite();
                float p = s.getPrix_unitaire();
                float total = q*p;
                System.out.println("total = "+total);
                return total;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
       
        return null;
    }

    @Override
    public int nbFacturePayee(long idR) {
      String req="SELECT COUNT(*) FROM facture WHERE statut='payée' AND id_rest='"+idR+"'";
      
      int nb =0;
        
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }

    @Override
    public int nbFactureNPayee(long idR) {
         String req="SELECT COUNT(*) FROM facture WHERE statut='non payée' AND id_rest='"+idR+"'";
      
      int nb =0;
        
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
/////////////////////
    @Override
    public List<Commande> afficherCommande(long idRest, long idFr) {
         List<Commande> lc = new ArrayList<>();
        
        String req="SELECT c.id_cmd,c.date_cmd,c.date_livraison,c.quantite,p.nom_pdt FROM commande c INNER join produit_restaurant p ON c.id_produit=p.id_pdtrest INNER join utilisateur u "
                + "on c.id_utilisateur=u.id_utilisateur where u.id_rest='"+idRest+"' AND c.id_fournisseur='"+idFr+"' AND c.statut='Confirmer' ";
        
         try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Commande c = new Commande();
                produit_restaurant p = new produit_restaurant();
                c.setId_cmd(rs.getLong("id_cmd"));
                c.setDate_cmd(rs.getDate("date_cmd"));
                c.setQuantite(rs.getFloat("quantite"));
                c.setDate_livraison(rs.getDate("date_livraison"));
               // c.setId_produit(rs.getLong("id_produit"));
                p.setNom_pdt(rs.getString("nom_pdt"));
                c.produit=p;
                lc.add(c);
                System.out.println("Liste des commandes remlie!!");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
         
    return lc;
    }

   
}
