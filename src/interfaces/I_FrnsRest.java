/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author Nesrine
 */
public interface I_FrnsRest<T>{
    
        public void ajouterFrnsRest(T t);
        public void modifierFrnsRest (long id_fournisseur_restaurant,T t);
        public void supprimerFrnsRest (long id_fournisseur_restaurant);
        public List<T> afficherFrnsRest();
    
}
