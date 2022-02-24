/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.reclamation;
import entities.type_reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface I_type_reclamationService<T> {
        void ajouterTypeReclamation(T entity) throws SQLException;
        void modifierTypeReclamation (long id_type_reclamation, type_reclamation typerec)throws SQLException;
        void supprimerTypeReclamation (long id) throws SQLException;
        List<type_reclamation> afficherTypeReclamation();
}
