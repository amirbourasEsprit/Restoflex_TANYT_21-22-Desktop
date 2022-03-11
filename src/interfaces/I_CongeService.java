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
 * @author Yosr Belaam
 */
public interface I_CongeService <T>  {
      void ajouterConge(T entity) throws SQLException;;
        void modifierConge (T entity)throws SQLException;
        void supprimerConge (int id) throws SQLException;
        List<T> afficherConge(int id);
        
}
