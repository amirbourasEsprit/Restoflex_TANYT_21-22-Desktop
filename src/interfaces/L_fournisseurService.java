/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entities.fournisseur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author abyrm
 */
public interface L_fournisseurService <T> {
           void ajouterFournisseur (T entity) throws SQLException ; 
    void modifierFournisseur(int id_fournisseur , T t) throws SQLException ; 
     void supprimerFournisseur (int id_fournisseur) throws SQLException;
        List<T> afficherFournisseur();
                        public fournisseur rechercheFournisseur (int id_fournisseur);
                       //  public boolean chercherFournisseurByEmail(String s);

}
