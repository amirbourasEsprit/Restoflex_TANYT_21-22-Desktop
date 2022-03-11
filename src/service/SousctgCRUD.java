/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.I_sousctg;
import entities.categorie;
import entities.sous_ctg;
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
 * @author Thinkpad
 */
public class SousctgCRUD implements I_sousctg<sous_ctg> {
    private final Connection con;
  private Statement ste;
  private PreparedStatement prst;
    public SousctgCRUD() {
                con = MyConnection.getInstance().getCnx();

    }

    @Override
    public void ajouterSousctg(sous_ctg entity) throws SQLException {
        String req = "INSERT INTO sous_ctg (nom_sctg,id_categorie) VALUE (?,?)";
        try{
        prst= con.prepareStatement(req);
        
        prst.setString(1, entity.getNom_sctg());
        prst.setLong(2, entity.getId_categorie());
        prst.executeUpdate();
        System.out.println("Sous Categorie crée avec succée!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierSousctg(sous_ctg entity, int id) throws SQLException {
        String req = "UPDATE sous_ctg SET "
                +"`nom_sctg`=? WHERE id_sctg = '" +id+ "'";
        try {
             prst= con.prepareStatement(req);
             prst.setString(1, entity.getNom_sctg());
           
            prst.executeUpdate();
            System.out.println(" sous Categorie modifiée avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerSousctg(int id) throws SQLException {
       String delete= "DELETE FROM sous_ctg where id_sctg= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setLong(1,id);
            prst.executeUpdate();
            System.out.println("suppression sous categorie avec succées");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<sous_ctg> afficher() {
        List<sous_ctg>categorieList;
      categorieList=new ArrayList<>();
      String select="SELECT * FROM sous_ctg";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(select);
            while(rs.next()){
                sous_ctg u = new sous_ctg();
                u.setId_sctg(rs.getInt(1));
                u.setNom_sctg(rs.getString(2));
                u.setId_categorie(rs.getInt(3));
                
                categorieList.add(u);
                System.out.println("affichage succées");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return categorieList;
    }

    
}
