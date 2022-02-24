/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface I_ReclamationService<T>{ 
    void ajouterReclamation(reclamation rec) throws SQLException;;
        void modifierReclamation (long num_reclamation, reclamation rec)throws SQLException;
        void supprimerReclamation (long num_reclamation) throws SQLException;
        List<reclamation> afficherReclamation();
        
    
}
