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
public interface I_TypeCongeService<T> {
  void ajouterTypeConge(T entity) throws SQLException;;
        void modifierTypeConge (T entity)throws SQLException;
        void supprimerTypeConge (int id) throws SQLException;
        List<T> afficherTypeConge();  
}
