/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author abyrm
 */



import interfaces.L_fournisseurService;
import entities.fournisseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyConnection;

public class FournisseurService implements L_fournisseurService<fournisseur> {
  private Connection con;
  private Statement ste;
  private PreparedStatement prst;
    public FournisseurService() {
        con = MyConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterFournisseur(fournisseur fr) throws SQLException {
        String req="INSERT INTO `fournisseur`(`nom_fournisseur`,`matricule_fiscale`, `domaine_fournisseur`,`num_tel_fournisseur`,`email_fournisseur`,`adresse_fournisseur`,`logo`) VALUE (?,?,?,?,?,?,?)";
try{
        prst= con.prepareStatement(req);
        
        prst.setString(1, fr. getNom_fournisseur());
        prst.setString(2, fr. getMatricule_fiscale());
        prst.setString(3, fr.getDomaine_fournisseur());
        prst.setString(4,fr.getNum_tel_fournisseur());
        prst.setString(5, fr.getEmail_fournisseur());
        prst.setString(6, fr.getAdresse_fournisseur());
        prst.setString(7, fr.getLogo());
        prst.executeUpdate();
        System.out.println("fournisseur créer avec succée!");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }      
    }

    @Override
    public void modifierFournisseur(int id_fournisseur ,fournisseur fr) throws SQLException {
               String req = "UPDATE `fournisseur` SET "
                +"`nom_fournisseur`=?,`matricule_fiscale`=?,`domaine_fournisseur`=?,`num_tel_fournisseur`=?,`email_fournisseur`=?,`adresse_fournisseur`=?,`logo`=?"
                + "WHERE id_fournisseur = '" +id_fournisseur+ "'";
        try {
             prst= con.prepareStatement(req);
         prst.setString(1, fr. getNom_fournisseur());
        prst.setString(2, fr. getMatricule_fiscale());
        prst.setString(3, fr.getDomaine_fournisseur());
        prst.setString(4,fr.getNum_tel_fournisseur());
        prst.setString(5, fr.getEmail_fournisseur());
        prst.setString(6, fr.getAdresse_fournisseur());
        prst.setString(7, fr.getLogo());

            prst.executeUpdate();
            System.out.println("fournisseur modifie avec succée!");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerFournisseur(int id_fournisseur) throws SQLException {

  String delete= "DELETE FROM fournisseur where id_fournisseur= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setInt(1,id_fournisseur);
            prst.executeUpdate();
            System.out.println("suppression fournisseur  avec succées");
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }   
    }

    @Override
    public List<fournisseur> afficherFournisseur() {
      List<fournisseur>fourList;
      fourList=new ArrayList<>();
      String select="SELECT * FROM `fournisseur`";
        try {
            ste= con.createStatement();
            
            ResultSet rs = ste.executeQuery(select);
            System.out.println(rs);
            while(rs.next()){
                fournisseur f = new fournisseur();
                f.setId_fournisseur(rs.getInt(1));
                f.setNom_fournisseur(rs.getString(2));
                f.setMatricule_fiscale(rs.getString(3));
                f.setDomaine_fournisseur(rs.getString(4));
                f.setNum_tel_fournisseur(rs.getString(5));
                f.setEmail_fournisseur(rs.getString(6));
                f.setAdresse_fournisseur(rs.getString(7));
                f.setLogo(rs.getString(8));

                fourList.add(f);
                System.out.println("affichage succées");
            } 

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    return fourList;
    }
    
}
