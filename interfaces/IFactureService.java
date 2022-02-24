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
 * @author Nesrine
 */
public interface IFactureService<T> {
    
        public void ajouterFacture(T t);
        public void modifierFacture (long num_facture,T t);
        public void supprimerFacture (long num_facture);
        public List<T> afficherFacture();
    
    
}
