/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.role;
import interfaces.I_Role;
import java.sql.*;
import java.util.*;
import util.MyConnection;

/**
 *
 * @author boura
 */
public class RoleService implements I_Role<role> {

      private final Connection con;
      private Statement ste;
      private PreparedStatement prst;
     //pour initialiser la connexion
        public RoleService (){
          con = MyConnection.getInstance().getCnx();
      }
   
    @Override
    public void ajouter(role r) throws SQLException {
        String insert = "INSERT INTO `role`(`nom_role`)VALUE(?)";
        try {
            prst=con.prepareStatement(insert);
            prst.setString(1, r.getNom_role());
            prst.executeUpdate();
            System.out.println("Ajout de role avec succes!");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(long id, role r) throws SQLException {
        String update="UPDATE `role` SET `nom_role`=? WHERE id_role='"+id+"'";
        try {
            prst=con.prepareStatement(update);
            prst.setString(1, r.getNom_role());
            prst.executeUpdate();
            System.out.println("Mise à jour de role avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(long id) throws SQLException {
        String delete="DELETE FROM role WHERE id_role= ?";
        try {
            prst=con.prepareStatement(delete);
            prst.setLong(1, id);
            prst.executeUpdate();
            System.out.println("Suppression role avec succés!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<role> afficher() {
        List<role> role_list=new ArrayList<>();
        String select="SELECT * FROM `role`";
        try {
            ste= con.createStatement();
            ResultSet rs=ste.executeQuery(select);
            while(rs.next()){
            role u=new role();
            u.setId_role(rs.getLong(1));
            u.setNom_role(rs.getString(2));
            role_list.add(u);
               System.out.println("affichage avec succés!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return role_list;
    }
    
}
