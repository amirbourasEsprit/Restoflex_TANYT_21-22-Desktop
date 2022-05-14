/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import entities.fournisseur;
import interfaces.L_StockService;
import entities.stock;
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
 * @author abyrm
 */
public class StockService implements L_StockService  <stock>{
  private Connection con;
  private Statement ste;
  private PreparedStatement prst;
    public StockService() {
        con = MyConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterStock(stock st) throws SQLException {
 String req="INSERT INTO `stock`(`nom_stock`,`prix_unitaire`, `quantite`,`id_fournisseur`) VALUE (?,?,?,?)";
try{
        prst= con.prepareStatement(req);
        
        prst.setString(1, st.getNom_stock());
        prst.setFloat(2, st.getPrix_unitaire());
        prst.setFloat(3, st.getQuantite());
        prst.setInt(4, (int) st.getId_fournisseur());
      
        prst.executeUpdate();
        System.out.println("stock créer avec succée!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }      
    }

    @Override
    public void modifierStock(int id_stock, stock st) throws SQLException {
   String req =  "UPDATE `stock` SET `nom_stock`=?,`prix_unitaire`=?,`quantite`=? WHERE id_stock = '" +id_stock+ "'";
        try {
             prst= con.prepareStatement(req);
    prst.setString(1, st.getNom_stock());
        prst.setFloat(2, st.getPrix_unitaire());
        prst.setFloat(3, st.getQuantite());
      
        //prst.setInt(4, (int) st.getId_fournisseur());

            prst.executeUpdate();
            System.out.println("stock modifie avec succée!");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerStock(int id_stock) throws SQLException {
    String delete= "DELETE FROM stock where id_stock= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setInt(1,id_stock);
            prst.executeUpdate();
            System.out.println("suppression stock  avec succées");
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }   
    }

    @Override
    public List<stock> afficherStock(int id_fournisseur) {
         List<stock>stList;
      stList=new ArrayList<>();
      String select="SELECT * FROM stock s where s.id_fournisseur ="+id_fournisseur;
        try {
            ste= con.createStatement();
            
            ResultSet rs = ste.executeQuery(select);
            System.out.println(rs);
            while(rs.next()){
                stock st = new stock();
                st.setId_stock(rs.getInt(1));
                st.setNom_stock(rs.getString(2));
              st.setPrix_unitaire(rs.getFloat(3));
              st.setQuantite(rs.getFloat(4));
              st.setId_fournisseur(rs.getInt(5));
                stList.add(st);
                System.out.println("affichage succées");
            } 

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return stList;
    }

    @Override
    public stock rechercheStock(int id_stock) {

  String req="SELECT * FROM `stock` where id_stock='"+id_stock+"'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                stock st = new stock();
                st.setId_stock(rs.getInt(1));
                st.setNom_stock(rs.getString(2));
              st.setPrix_unitaire(rs.getFloat(3));
              st.setQuantite(rs.getFloat(4));
              st.setId_fournisseur(rs.getInt(5));
              
            return st; 
        }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
            return null;
    }
    
   
    public List<stock> rechercheStock2(int id_fournisseur , String search) {
        ObservableList<stock> stk=FXCollections.observableArrayList();
  String req="SELECT * FROM `stock` where id_fournisseur= "+id_fournisseur+" AND nom_stock LIKE  '%"+search+"%'";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                stock st = new stock();
                st.setId_stock(rs.getInt(1));
                st.setNom_stock(rs.getString(2));
              st.setPrix_unitaire(rs.getFloat(3));
              st.setQuantite(rs.getFloat(4));
              st.setId_fournisseur(rs.getInt(5));
           
            stk.add(st);
        }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
            return stk;
    }
    //update stock s set quantite=quantite+? where nom_stock= 'Escalope' AND s.id_fournisseur= 1
     public void modifierQuantiteStock(Float c,int id,String nomprod) {
         
        String req = "update stock set quantite=quantite-? where id_fournisseur= ? and nom_stock= ?";

        try {
            prst = con.prepareStatement(req);
            prst.setFloat(1,c);
            prst.setLong(2, id);
            prst.setString(3, nomprod);
          
            prst.executeUpdate();
            System.out.println("Stock modifier");
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

    }
    
 
}
