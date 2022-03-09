/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Thinkpad
 */
public interface I_commande <T> {
      void ajouterCommande(T entity) throws SQLException;;
        void modifierCommande (T entity, int id)throws SQLException;
        void supprimerCommande (int id) throws SQLException;
        List<T> afficher();
        
    
}
