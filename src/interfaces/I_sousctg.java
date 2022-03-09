/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.SQLException;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Thinkpad
 */
public interface I_sousctg<T> {
        void ajouterSousctg   (T entity) throws SQLException;;
        void modifierSousctg (T entity, int id)throws SQLException;
        void supprimerSousctg (int id) throws SQLException;
        List<T> afficher();
    
}
