/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.I_commande;
import entities.Commande;
import entities.fournisseur;
import entities.produit_restaurant;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyConnection;

/**
 *
 * @auth0or Thinkpad
 */
public class CommandeCRUD implements I_commande <Commande> {
    private final Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement ste;
    public CommandeCRUD(){
        conn =  MyConnection.getInstance().getCnx();
    }
    
    //hethy cbn
    @Override
    public void ajouterCommande(Commande cmd) throws SQLException {
        try {
              String req = "INSERT INTO Commande (statut,date_cmd,date_livraison,quantite,id_utilisateur,id_produit,id_fournisseur) values (?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(req);
            pst.setString(1, cmd.getStatut());
            pst.setDate(2, cmd.getDate_cmd());
            pst.setDate(3, cmd.getDate_livraison());
            pst.setFloat(4, cmd.getQuantite());
            pst.setLong(5, cmd.getId_utilisateur());
            pst.setLong(6, cmd.getId_produit());
            pst.setLong(7, cmd.getId_four());
                        
            
            pst.executeUpdate(); 

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());}        
        
    }
     public void ajouterCommande2(Commande C){
         
     }
     @Override
       public void supprimerCommande(int id) {
       

        try {
             String req = "delete  from commande where id_cmd=? ";
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

    }
        @Override
        public void modifierCommande(Commande c,int id) {
        String req = "update commande set statut=?, date_livraison=?, id_fournisseur=? where id_cmd= '"+id+"'";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getStatut());
            pst.setDate(2, c.getDate_livraison());
            pst.setInt(3, (int) c.getId_four());
                    
           
           // pst.setLong(4,id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

    }
        @Override
         public List<Commande> afficher() {
        String req = "select * from commande";

        List<Commande> list=new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs= ste.executeQuery(req);
           while(rs.next()){
               list.add(new Commande(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getLong(6)));
           }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
public ObservableList<fournisseur> getfournisseur() {
       ObservableList<fournisseur>categorieList;
      categorieList=FXCollections.observableArrayList();
      String select="SELECT  id_fournisseur,nom_fournisseur from fournisseur";
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                fournisseur u = new fournisseur();
                u.setId_fournisseur(rs.getInt(1));
                u.setNom_fournisseur(rs.getString(2));
                
                
                
                categorieList.add(u);
                System.out.println("affichage succées");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return categorieList;
    }
   public ObservableList<Commande> getCommandes(String ch,int p,int current_resto_user) {
       ObservableList<Commande>categorieList;
      categorieList=FXCollections.observableArrayList();
      String select="SELECT c.id_cmd,c.statut,c.date_cmd,c.date_livraison,c.quantite,u.cin,p.nom_pdt,f.nom_fournisseur from commande c inner join utilisateur u on "
              + "c.id_utilisateur=u.id_utilisateur inner join produit_restaurant p on c.id_produit=p.id_pdtrest inner join fournisseur f on c.id_fournisseur=f.id_fournisseur where"
              + " c.quantite >'"+p+"'AND u.id_rest='"+current_resto_user+"'";
       System.out.println(select);
      if(!ch.equals(""))
        select +=" and (u.cin like '"+ch+"%' or p.nom_pdt like '"+ch+"%' or f.nom_fournisseur like '"+ch+"%') "; 
        try {
            ste= conn.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                Commande u = new Commande();
                u.setId_cmd(rs.getInt(1));
                u.setStatut(rs.getString(2));
                u.setDate_cmd(rs.getDate(3));
                u.setDate_livraison(rs.getDate(4));
                u.setQuantite(rs.getFloat(5));
                u.setNomUtil(rs.getString(6));
                u.setNomProd(rs.getString(7));
                u.setNomFour(rs.getString(8));
                
                categorieList.add(u);
                System.out.println("affichage succées");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return categorieList;
    }
 public void modifierProd(Float c,long id) {
        String req = "update produit_restaurant set quantite_pdt=quantite_pdt+? where id_pdtrest=? ";

        try {
            pst = conn.prepareStatement(req);
            pst.setLong(2, id);
           
            pst.setFloat(1,c);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

    }
      public int getMax() {
        String req = "select max(quantite) from commande";
        int a=-1;
        try {
            ste = conn.createStatement();
           rs= ste.executeQuery(req);
           while(rs.next()){
              a= rs.getInt(1);
           }
           return a;
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
            return -1;
        }
    }  
}