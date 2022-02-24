/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.restaurant;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nesrine
 */
public interface I_RestaurantService<T> {
    
        public void ajouterRestaurant(T entity) throws SQLException;;
        public void modifierRestaurant (long id,T entity)throws SQLException;
        public void supprimerRestaurant (long id) throws SQLException;
        public List<T> afficherRestaurant();
        
}
