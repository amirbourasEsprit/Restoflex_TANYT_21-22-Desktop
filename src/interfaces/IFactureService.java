/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.facture;
import entities.fournisseur;
import entities.restaurant;
import entities.Commande;
import entities.produit_restaurant;
import java.util.List;

/**
 *
 * @author Nesrine
 */
public interface IFactureService<T> {
        
        public void ajouterFacture(T t);
        public void modifierFacture (long num_facture,T t);
        public void supprimerFacture (long num_facture);
        public List<facture> afficherFacture();
        ///////
        public facture rechercherFacture (long num_facture);
        public restaurant rechercherRestaurant (long num_facture);
        public fournisseur rechercherFournisseur (long num_facture);
        public List<facture> afficherFactureRest(long idRest);
        public Float calculTotal(long id_cmd);
        public int nbFacturePayee(long idR);
        public int nbFactureNPayee(long idR);
        public List<Commande> afficherCommande(long idRest, long idFr);
    
        
}
