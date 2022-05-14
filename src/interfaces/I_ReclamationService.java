/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.fournisseur;
import entities.reclamation;
import entities.restaurant;
import entities.utilisateur;
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
        public reclamation chercherReclamation(long id);
        List<reclamation> afficherReclamation();
        List<reclamation> afficherReclamationParId(int id);
        public utilisateur chercherPrenomGerant(long idRest);        
        public List<restaurant> chercherRestaurant(int id,String cin );
        public List <utilisateur> chercherNomFournisseur(long id);
        public List<utilisateur> chercherNomEmploye (long id); 
        public List<reclamation> rechercherParDate(int id_rest_current,String recherche);

     
    
}
